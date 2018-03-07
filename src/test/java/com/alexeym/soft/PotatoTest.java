package com.alexeym.soft;

import com.alexeym.soft.ctrl.PotatoCtrl;
import com.alexeym.soft.model.PotatoBag;
import com.alexeym.soft.storage.SupplierStorage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PotatoTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private SupplierStorage supplierStorage;

    @Test
    public void testCreateGet() throws Exception {
        // list bags without error
        List<PotatoBag> bags = getBags();
        int size = bags.size();

        // add one bag - expect size + 1
        PotatoBag createdBag = webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new PotatoBag(null, randomNumber(1, 100), randomSupplier(), Instant.now(), randomNumber(1, 50))), PotatoBag.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(PotatoBag.class).returnResult().getResponseBody();
        // newly created bag should have id
        assertTrue(createdBag.getId() != null);
        bags = getBags();
        assertEquals(size + 1, bags.size());
    }

    @Test
    public void testGetBagsSize() throws Exception {
        // add some bags
        for (int i=0; i<10; i++) {
            webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(Mono.just(new PotatoBag(null, randomNumber(1, 100), randomSupplier(), Instant.now(), randomNumber(1, 50))), PotatoBag.class)
                    .exchange();
        }

        // get without size - should be 3
        List<PotatoBag> bags = getBags();
        assertEquals(bags.size(), 3);

        // get with size 0
        bags = getBags(0);
        assertEquals(0, bags.size());

        // get with size 5
        bags = getBags(5);
        assertEquals(5, bags.size());

        // get with very big size - should return less size - only existing bags
        bags = getBags(100500);
        assertTrue(bags.size() > 0 && bags.size() != 100500);
    }

    @Test
    public void testValidatePotatoBag() throws Exception {
        // cannot add bag with 0 potatoes
        webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new PotatoBag(null, 0, randomSupplier(), Instant.now(), randomNumber(1, 50))), PotatoBag.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(String.class);

        // cannot add bag with unknown supplier
        webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new PotatoBag(null, randomNumber(1, 100), "unknown_supplier", Instant.now(), randomNumber(1, 50))), PotatoBag.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(String.class);

        // cannot add bag with pack date 5 seconds in future
        webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new PotatoBag(null, randomNumber(1, 100), "unknown_supplier", Instant.now().plusSeconds(5), randomNumber(1, 50))), PotatoBag.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(String.class);

        // cannot add bag with 100500 price
        webClient.post().uri(PotatoCtrl.POTATO_BAG_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new PotatoBag(null, randomNumber(1, 100), "unknown_supplier", Instant.now(), 100500)), PotatoBag.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(String.class);
    }

    private List<PotatoBag> getBags() {
        return getBags(null);
    }

    @SuppressWarnings("unchecked")
    private List<PotatoBag> getBags(Integer size) {
        return (List<PotatoBag>) webClient.get().uri(uriBuilder -> uriBuilder
                    .path(PotatoCtrl.POTATO_BAG_URL)
                    .queryParam("size", size)
                    .build())
                .attribute("size", size)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(List.class)
                .returnResult().getResponseBody();
    }

    private String randomSupplier() {
        List<String> list = supplierStorage.list();
        return list.get((int) (Math.random() * list.size()));
    }

    private int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    @ComponentScan({"com.alexeym.soft.ctrl", "com.alexeym.soft.storage", "com.alexeym.soft.validator"})
    static class Config {
//        @Bean
//        @Primary
//        public WebClient loggedWebClient() {
//            new WebClient.Builder()
//                    .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//                        System.out.println("req: " + clientRequest.method() + " " + clientRequest.url());
//                        return Mono.just(clientRequest);
//                    }))
//                    .build();
//        }
    }
}
