package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Bucket;
import com.company.ecommerce.service.BucketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bucket",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class BucketController {

    private final BucketServiceImpl bucketService;

    public BucketController(BucketServiceImpl bucketService) {
        this.bucketService = bucketService;
    }


    @PostMapping(value = "/add-to-bucket/{customer}/{perProduct}/{size}/{count}", consumes = "application/json; charset=utf-8")
    public ResponseEntity<?> addToBucket(@PathVariable Long customer, @PathVariable Long perProduct,
                                         @PathVariable Long size, @PathVariable int count, @RequestBody Bucket bucket) {
        bucketService.addToBucket(customer, perProduct, size, count);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/{customerId}")
    public ResponseEntity<?> getBucket(@PathVariable Long customerId){
        List<Bucket> bucket = bucketService.getBucket(customerId);
        return new ResponseEntity<>(bucket, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-product-from-bucket/{customerId}/{perProductId}/{sizeId}")
    public ResponseEntity<?> deleteProductFromBucket(@PathVariable Long customerId, @PathVariable Long perProductId, @PathVariable Long sizeId){
        bucketService.deleteProductFromBucket(customerId, perProductId, sizeId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/increment-product-number/{customer}/{perProduct}/{size}", consumes = "application/json; charset=utf-8")
    public ResponseEntity<?> incrementProductNumber(@PathVariable Long customer, @PathVariable Long perProduct,
                                         @PathVariable Long size, @RequestBody Bucket bucket) {
        bucketService.incrementProductNumber(customer, perProduct, size);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/decrement-product-number/{customer}/{perProduct}/{size}", consumes = "application/json; charset=utf-8")
    public ResponseEntity<?> decrementProductNumber(@PathVariable Long customer, @PathVariable Long perProduct,
                                         @PathVariable Long size, @RequestBody Bucket bucket) {

        bucketService.decrementProductNumber(customer, perProduct, size);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/purchase/{customerId}")
    public ResponseEntity<?> purchaseProductsInBucket(@PathVariable Long customerId){
        bucketService.purchaseProductsInBucket(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
