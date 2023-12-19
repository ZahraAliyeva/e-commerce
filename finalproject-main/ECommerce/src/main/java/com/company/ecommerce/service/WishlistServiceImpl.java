package com.company.ecommerce.service;

import com.company.ecommerce.entity.Customer;
import com.company.ecommerce.entity.PerProduct;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.Wishlist;
import com.company.ecommerce.repo.CustomerRepository;
import com.company.ecommerce.repo.PerProductRepository;
import com.company.ecommerce.repo.WishlistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistServiceImpl {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    PerProductRepository perProductRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addToWishlist(Long customerId, Long perProductId){
        if(wishlistRepository.findWishlistItem(customerId, perProductId) == null) {
            Wishlist wishlist = Wishlist.builder()
                    .customer(customerRepository.findById(customerId).get())
                    .perProduct(perProductRepository.findById(perProductId).get())
                    .build();
            em.merge(wishlist);
        }
        else{
            wishlistRepository.deleteFromWishlist(customerId, perProductId);
        }
    }


    public List<Wishlist> getProducts() {
        List<Wishlist> products = wishlistRepository.findAll();
        return products;
    }

    public List<Wishlist> getProductsByCustomerId(Long customerId) {
        List<Wishlist> wishlist = wishlistRepository.findWishlistItemForCustomer(customerId);
        return wishlist;
    }

}
