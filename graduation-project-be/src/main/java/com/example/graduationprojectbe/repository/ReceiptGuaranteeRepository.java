package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.ReceiptGuaranteeInfo;
import com.example.graduationprojectbe.dto.projection.ReceiptInfo;
import com.example.graduationprojectbe.entity.Receipt;
import com.example.graduationprojectbe.entity.ReceiptGuarantee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceiptGuaranteeRepository extends JpaRepository<ReceiptGuarantee, Integer> {

    Optional<ReceiptGuarantee> findByProductGuarantee_Id(Integer id);

    @Query("select rg from ReceiptGuarantee rg join rg.productGuarantee pg where pg.ime like %?1% ")
    Page<ReceiptGuaranteeInfo> findReceiptAll(Pageable pageable, String term);

    @Query("select rg from ReceiptGuarantee rg join rg.productGuarantee pg where pg.ime like %?1% and rg.status = true ")
    Page<ReceiptGuaranteeInfo> findReceiptStatusTrueAll(Pageable pageable, String term);

    @Query("select rg from ReceiptGuarantee rg where rg.id = ?1 ")
    Optional<ReceiptGuaranteeInfo> findReceiptById(Integer id);
}