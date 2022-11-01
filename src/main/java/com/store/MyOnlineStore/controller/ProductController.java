package com.store.MyOnlineStore.controller;

import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.models.ProductFilterOptions;
import com.store.MyOnlineStore.models.ProductsFilter;
import com.store.MyOnlineStore.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return searchedProductOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping(value = "/products")
//    public ResponseEntity<List<Product>> getAllProducts(){
//        List<Product> productList = productService.findAll();
//        return ResponseEntity.ok(productList);
//    }

    @PostMapping (value = "/products")
    public ResponseEntity<Page<Product>> findAllFiltered(
            @PageableDefault(size=6, sort="name", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody ProductsFilter productsFilter
            ){
        Page<Product> searchedProducts = productService.findAllFiltered(pageable, productsFilter);

        return ResponseEntity.ok(searchedProducts);
    }

    @GetMapping("/products/filters")
    public ResponseEntity<ProductFilterOptions> findFilterOptions() {
        return ResponseEntity.ok(productService.findFilterOptions());
    }
}
