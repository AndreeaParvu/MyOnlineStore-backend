package com.store.MyOnlineStore.controller;

import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //nu returneaza resurse statice, doar date sub forma de Json;
@RequestMapping("commerce/api/public/catalog")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}") //rezultatul + un status de http
    public ResponseEntity<Product> findById(@PathVariable("id") long id){
        Optional<Product> searchedProductOpt = productService.findById(id); //wrapper peste rezultat care permite sun rezultat fara check-uri de null
        if (searchedProductOpt.isPresent()) {
            return ResponseEntity.ok(searchedProductOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }
}
