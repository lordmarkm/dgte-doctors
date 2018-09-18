package com.ampota.user.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import xyz.quadx.xpay.shared.firebase.client.UserAuthorityClient;

@RestController
@RequestMapping("/api/user-authority")
public class UserAuthorityResource implements UserAuthorityClient {

    @Override
    public ResponseEntity<List<GrantedAuthority>> getUserAuthorities(String username) {
        List<GrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorities);
    }

}
