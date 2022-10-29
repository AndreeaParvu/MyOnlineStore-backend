package com.store.MyOnlineStore.service;

import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true) //suprascriere pentru performanta -> toate functiile dintr-un Service sunt tranzactionale
    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }
    //Folosind clasa Optional evit NullPointerException;

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        List<Product> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productList.add(product);
        }
        return productList;
    }
    //GOOD PRACTICE: daca nu gaseste nimic, in loc de null, returneaza liste goale;
}
