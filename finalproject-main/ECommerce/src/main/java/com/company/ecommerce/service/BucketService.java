package com.company.ecommerce.service;

import com.company.ecommerce.entity.Bucket;

import java.util.List;

public interface BucketService {
    void addToBucket(Long customerId, Long perProductId, Long sizeId, int count);
    List<Bucket> getBucket(Long customerId);

    void deleteProductFromBucket(Long customerId, Long perProductId, Long sizeId);
    void incrementProductNumber(Long customerId, Long perProductId, Long sizeId);
    void decrementProductNumber(Long customerId, Long perProductId, Long sizeId);
    void purchaseProductsInBucket(Long customerId);
}
