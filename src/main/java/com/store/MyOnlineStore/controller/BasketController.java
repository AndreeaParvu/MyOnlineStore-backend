package com.store.MyOnlineStore.controller;
import com.store.MyOnlineStore.domain.entities.Basket;
import com.store.MyOnlineStore.models.ItemRequest;
import com.store.MyOnlineStore.service.BasketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("commerce/api/public/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public ResponseEntity<Basket> getBasket(@CookieValue(value = "buyerId", required = false) String buyerId) {
        if (buyerId == null || buyerId.trim().isEmpty()) {
            buyerId = UUID.randomUUID().toString();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie",
                    "buyerId="+ buyerId +";Max-Age=604800; Path=/; Secure; HttpOnly");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(basketService.findOrCreateBasket(buyerId));
    }

    @PostMapping
    private ResponseEntity<Basket> addItemToBasket(
            @CookieValue(value = "buyerId", required = false) String buyerId,
            @RequestBody ItemRequest itemRequest) throws URISyntaxException {
        if (buyerId == null || buyerId.trim().isEmpty()) {
            buyerId = UUID.randomUUID().toString();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie",
                    "buyerId="+ buyerId +";Max-Age=604800; Path=/; Secure; HttpOnly");

        Basket basket = basketService.addItem((buyerId),
                    itemRequest.getProductId(),
                    itemRequest.getQuantity());

        return ResponseEntity
                .created(new URI("commerce/api/basket")) //StatusCode(201 - Resource Created)
                .headers(headers)
                .body(basket);
    }

    @DeleteMapping("/{productId}/{quantity}")
    private ResponseEntity<Basket> removeItemFromBasket(
            @CookieValue("buyerId") String buyerId,
            @PathVariable("productId") long productId,
            @PathVariable("quantity") long quantity) {
        return ResponseEntity.ok(basketService.removeItem(buyerId, productId, quantity));
    }
}
