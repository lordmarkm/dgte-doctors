package com.ampota.gateway.resource;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.CardClient;
import com.ampota.shared.dto.card.CardInfo;
import static com.ampota.shared.dto.card.Language.en;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/card")
public class CardGatewayResource {

    @Autowired
    private CardClient client;

    /**
     * English language cards come first
     * Then, sort by set recency
     * @param term
     * @param page
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<CardInfo>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        Page<CardInfo> cards = client.rqlSearch(term, page).getBody();
        List<CardInfo> sorted = cards.stream().sorted(new Comparator<CardInfo>() {
            @Override
            public int compare(CardInfo o1, CardInfo o2) {
                if (o1.getLang() == en && o2.getLang() != en) {
                    return -1;
                } else if (o2.getLang() == en && o1.getLang() != en) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
        PageImpl<CardInfo> p = new PageImpl<>(sorted, cards.getPageable(), cards.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @GetMapping("/unique-names")
    public ResponseEntity<Set<String>> getUniqueNames(@RequestParam String term) {
        return client.uniqueNames(term);
    }

}
