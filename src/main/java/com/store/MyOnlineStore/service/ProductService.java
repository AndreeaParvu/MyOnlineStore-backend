package com.store.MyOnlineStore.service;

import com.store.MyOnlineStore.domain.entities.Brand;
import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.domain.entities.Type;
import com.store.MyOnlineStore.domain.repository.ProductRepository;
import com.store.MyOnlineStore.models.ProductFilterOptions;
import com.store.MyOnlineStore.models.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

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

    @Transactional(readOnly = true)
    public Page<Product> findAllFiltered(Pageable pageable, ProductsFilter productsFilter) {
        return productRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(3);
            searchByName(root, criteriaBuilder, productsFilter.getProductName()).ifPresent(predicates::add);
            hasTypes(root, productsFilter.getTypes()).ifPresent(predicates::add);
            hasBrands(root, productsFilter.getBrands()).ifPresent(predicates::add);

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }

    @Transactional(readOnly = true)
    public ProductFilterOptions findFilterOptions() {
        CompletableFuture<Set<Type>> typesFuture = CompletableFuture.supplyAsync(productRepository::findDistinctProductTypes);
        CompletableFuture<Set<Brand>> brandsFuture = CompletableFuture.supplyAsync(productRepository::findDistinctProductBrands);

        CompletableFuture.allOf(typesFuture, brandsFuture).join();

        return new ProductFilterOptions(typesFuture.join(), brandsFuture.join());
    }

    private Optional<Predicate> searchByName(Root<Product> root, CriteriaBuilder criteriaBuilder, String productName) {
        if (StringUtils.isEmpty(productName)){
            return Optional.empty();
        }
        return Optional.of(criteriaBuilder.like(root.get("name"), productName.trim().toLowerCase() + "%"));
    }

    private Optional<Predicate> hasTypes(Root<Product> root, Set<Type> types) {
        if (CollectionUtils.isEmpty(types)){
            return Optional.empty();
        }
        return Optional.of(root.get("type").in(types));
    }

    private Optional<Predicate> hasBrands(Root<Product> root, Set<Brand> brands){
        if (CollectionUtils.isEmpty(brands)){
            return Optional.empty();
        }
        return Optional.of(root.get("brand").in(brands));
    }
}
