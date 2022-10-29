package com.store.MyOnlineStore.service;

import com.store.MyOnlineStore.domain.entities.Basket;
import com.store.MyOnlineStore.domain.entities.BasketItem;
import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.domain.repository.BasketItemRepository;
import com.store.MyOnlineStore.domain.repository.BasketRepository;
import com.store.MyOnlineStore.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    private final BasketItemRepository basketItemRepository;


    public BasketService(BasketRepository basketRepository,
                         ProductRepository productRepository,
                         BasketItemRepository basketItemRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.basketItemRepository = basketItemRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Basket> findByBuyerId (String buyerId){
        return basketRepository.findBasketByBuyerId(buyerId);
    }

    @Transactional
    public Basket addItem(String buyerId, long productId, long quantity){
        //get product
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("No product found with id: " + productId);
        }

        //get || create basket
        Basket basket = findOrCreateBasket(buyerId);

        // workaround for some weird hibernate bug
        Set<BasketItem> basketItems = new HashSet<>(basket.getItems());

        //add item
        if (basketItems
                .stream()
                .noneMatch(basketItem -> //lambda CONSUMATOR = functie care are un singur input si niciun output
                        basketItem.getProduct().getId() == (productId))) {
            BasketItem basketItem = new BasketItem(optionalProduct.get(), basket, quantity);
            basketItems.add(basketItem);

        } else {
            basketItems
                    .stream()
                    .filter(basketItem -> basketItem.getProduct().getId() == (productId))
                    .findFirst()
                    .ifPresent(basketItem -> basketItem.setQuantity(basketItem.getQuantity() + quantity));
        }

        //save changes
        basket.setItems(basketItems);
        basketRepository.save(basket);
        return basket;
    }

    @Transactional
    public Basket removeItem (String buyerId, long productId, long quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()) {
            throw  new RuntimeException("No product found with id " + productId);
        }

        //get basket
        Basket basket = findOrCreateBasket(buyerId);

        Set<BasketItem> itemsToUpdate = basket.getItems();
        Set<BasketItem> itemsToDelete = new HashSet<>();

        //remove item or reduce quantity
        itemsToUpdate
                .stream()
                .filter(basketItem -> basketItem.getProduct().getId() == (productId))
                .findFirst()
                .ifPresent(basketItem -> {
                    basketItem.setQuantity(basketItem.getQuantity() - quantity);
                    if (basketItem.getQuantity() == 0){
                        itemsToUpdate.remove(basketItem);
                        itemsToDelete.add(basketItem);
                    }
                });

        //save changes
        basketItemRepository.deleteAll(itemsToDelete);
        basketItemRepository.saveAll(itemsToUpdate);
        return basket;
    }

    @Transactional(readOnly = true)
    public Basket findOrCreateBasket(String buyerId){
        Optional<Basket> optionalBasket = basketRepository.findBasketByBuyerId(buyerId);
        return optionalBasket.orElseGet(() -> basketRepository.save(new Basket(buyerId, Collections.emptySet())));
    }
}
