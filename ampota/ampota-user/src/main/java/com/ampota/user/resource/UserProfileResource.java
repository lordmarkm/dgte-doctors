package com.ampota.user.resource;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.service.UserProfileService;
import com.google.common.collect.Maps;

import freemarker.template.TemplateException;
import xyz.quadx.xpay.shared.email.sender.Mail;
import xyz.quadx.xpay.shared.email.sender.MailSender;
import xyz.quadx.xpay.shared.firebase.FirebaseUserDetails;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileResource {

    @Autowired
    private UserProfileService service;

    @Autowired
    @Qualifier("xpayMailSender")
    private MailSender mailSender;

    @GetMapping
    public ResponseEntity<UserProfileInfo> getUserProfile(Principal principal, @RequestParam(required = false) String fbLink) {
        return service.findByUsernameInfo(principal.getName(), fbLink)
                .map(u -> ResponseEntity.status(OK).body(u))
                .orElse(ResponseEntity.status(NOT_FOUND).body(null));
    }

    @GetMapping("/find-by-username")
    public ResponseEntity<UserProfileInfo> findByUsername(Principal principal, @RequestParam String username) {
        return service.findByUsernameInfo(username)
                .map(u -> ResponseEntity.status(OK).body(u))
                .orElse(ResponseEntity.status(NOT_FOUND).body(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileInfo> findOneInfo(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOneInfo(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileInfo> register(@AuthenticationPrincipal FirebaseUserDetails principal,
            @Valid @RequestBody UserProfileInfo userProfile) {
        String username = principal.getUsername();
        Optional<UserProfileInfo> existingUser = service.findByUsernameInfo(username);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(CONFLICT).body(existingUser.get());
        } else {
            userProfile.setUsername(username);
            userProfile.setDisplayName(principal.getDisplayName());
            userProfile.setPhotoUrl(principal.getPhotoUrl());
            UserProfileInfo savedUser = service.saveInfo(userProfile);
            sendConfirmationEmail(savedUser);
            return ResponseEntity.status(ACCEPTED).body(savedUser);
        }
    }

    private void sendConfirmationEmail(UserProfileInfo newUser) {
        Mail mail = new Mail();
        mail.setFrom("piton.rpa@gmail.com");

        //Obviously, this assumes that usernames will always be emails
        mail.setTo(newUser.getUsername());
        mail.setSubject("Welcome to MtG Tambayan");

        Map<String, Object> model = Maps.newHashMap();
        model.put("name", "Memorynotfound.com");
        model.put("location", "Belgium");
        model.put("signature", "https://memorynotfound.com");
        mail.setModel(model);
        try {
            mailSender.sendSimpleMessage(mail);
        } catch (MessagingException | IOException | TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
