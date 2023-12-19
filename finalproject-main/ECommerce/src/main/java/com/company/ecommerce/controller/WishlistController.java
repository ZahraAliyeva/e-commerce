package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Wishlist;
import com.company.ecommerce.repo.CustomerRepository;
import com.company.ecommerce.service.WishlistServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/wishlist",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class WishlistController {

    private final WishlistServiceImpl wishlistService;
    private final CustomerRepository customerRepository;

    public WishlistController(WishlistServiceImpl wishlistService,
                              CustomerRepository customerRepository) {
        this.wishlistService = wishlistService;
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getProductsInWishList(){
        List<Wishlist> products = wishlistService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getProductsInWishListByCustomerId(@PathVariable Long customerId){
        List<Wishlist> products = wishlistService.getProductsByCustomerId(customerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/add-to-wishlist/{customerId}/{perProductId}")
    public ResponseEntity<?> addProductToWishlist(@PathVariable Long customerId,
                                                  @PathVariable Long perProductId,
                                                  @RequestBody Wishlist wishlist){
        wishlistService.addToWishlist(customerId, perProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
