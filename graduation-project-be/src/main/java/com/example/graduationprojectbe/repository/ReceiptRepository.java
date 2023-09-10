package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.ReceiptInfo;
import com.example.graduationprojectbe.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    @Query("select r from Receipt r where r.product.id = ?1")
    Optional<Receipt> findByProductId(Integer id);

    @Query("select r from Receipt r join r.product p where p.ime like %?1% ")
    Page<ReceiptInfo> findReceiptAll(Pageable pageable, String term);

    @Query("select r from Receipt r join r.product p where p.ime like %?1% and r.status = true ")
    Page<ReceiptInfo> findReceiptStatusTrueAll(Pageable pageable, String term);

    @Query("select r from Receipt r where r.id = ?1 ")
    Optional<ReceiptInfo> findReceiptById(Integer id);


}