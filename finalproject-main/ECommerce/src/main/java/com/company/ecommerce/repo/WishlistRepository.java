package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Query(value = "SELECT w FROM Wishlist  w WHERE w.customer.id = ?1 AND w.perProduct.id = ?2")
    Wishlist findWishlistItem(Long customerId, Long perProductId);


    @Query(value = "SELECT w FROM Wishlist  w WHERE w.customer.id = ?1")
    List<Wishlist> findWishlistItemForCustomer(Long customerId);

    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.customer.id = ?1 AND w.perProduct.id = ?2")
    void deleteFromWishlist(Long customerId, Long perProductId);
}
