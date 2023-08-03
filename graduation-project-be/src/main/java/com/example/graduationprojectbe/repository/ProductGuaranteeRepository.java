package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.projection.ProductGuaranteeProjection;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.dto.projection.VisitorSearchInfo;
import com.example.graduationprojectbe.entity.ProductGuarantee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductGuaranteeRepository extends JpaRepository<ProductGuarantee, Integer> {


    @Query("select pg from ProductGuarantee pg join pg.customer c where (:ime is null or pg.ime like %:ime% ) and (:phoneNumber is null or c.phoneNumber like %:phoneNumber% ) ")
    List<VisitorSearchInfo> searchGuaranteeByGuaranteeCode(@Param("ime") String ime,@Param("phoneNumber") String phoneNumber);




    // #################################################################################
    //########################## Nhân Viên Bảo Hành #########################################


    // danh sách sản phẩm bảo hành chờ sửa
    @Query("select pg from ProductGuarantee pg where pg.ime like %?1% and pg.status = ?2 and pg.delete and pg.repair = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeWaitingForRepairAll(Pageable pageable, String term, Status status);

    // lấy sản phẩm bảo hành theo id
    @Query("select pg from ProductGuarantee pg where pg.id = ?1 and pg.delete = true and pg.repair = true ")
    Optional<ProductGuaranteeProjection> findProductGuaranteeByID(Integer id);


    // danh sách sản phẩm bảo hành đã sửa xong
    @Query("select pg from ProductGuarantee pg where pg.ime like %?1% and pg.status = ?2 and pg.delete = true and pg.repair = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeRepairedAll(Pageable pageable, String term, Status status);


    // lấy danh sách sản phẩm chờ trả khách
    @Query("select pg from ProductGuarantee pg where pg.ime like %?1% and pg.status = ?2 and pg.delete = true and pg.repair = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeWaitingForReturnAll(Pageable pageable, String term, Status status);

    @Query("select pg from ProductGuarantee pg where pg.ime like %?1% and pg.status = ?2 and pg.delete = true and pg.repair = true order by pg.inputDate asc ")
    Page<ProductGuaranteeProjection> findProductUnderRepairAll(Pageable pageable, String term, Status status);

    @Query("select pg from ProductGuarantee pg left join ReceiptGuarantee rg on rg.productGuarantee.id = pg.id where rg.productGuarantee.id is null and pg.ime like %?1% ")
    Page<ProductGuaranteeProjection> findProductNoCreateReceiptAll(Pageable pageable, String term);

    // #################################################################################
    //########################## Nhân Viên sửa chữa #########################################


    // danh sách sản phẩm bảo hành theo user
    @Query("select pg from ProductGuarantee pg where pg.ime like %?1% and pg.engineer.id = ?2 and pg.status = ?3 and pg.delete = true and pg.repair = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeByUserAll(Pageable pageable, String term, Integer id, Status status);

    @Query("select pg from ProductGuarantee pg where pg.id = ?1 and pg.engineer.id = ?2 and pg.status = ?3 and pg.delete = true and pg.repair = true ")
    Optional<ProductGuarantee> findProductGuaranteeById_Engineer(Integer productId, Integer userId, Status status);

    @Query("select pg from ProductGuarantee pg where pg.id = ?1 and pg.engineer.id = ?2 and pg.delete = true and pg.repair = true ")
    Optional<ProductGuaranteeProjection> findById_Engineer(Integer productId, Integer userId);


    // #################################################################################
    //########################## ADMIN #########################################

    @Query("select pg from ProductGuarantee pg where pg.status = ?1 and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeAllByStartDateAndEndDateIsNull_ADMIN(Pageable pageable, Status status);

    @Query("select pg from ProductGuarantee pg where pg.status = :status and (pg.inputDate between :startDate and :endDate ) and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeWaitingForRepairAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select pg from ProductGuarantee pg where pg.status = :status and (pg.inputDate between :startDate and :endDate ) and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeUnderRepairAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select pg from ProductGuarantee pg where pg.status = :status and (pg.inputDate between :startDate and :endDate ) and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeRepairedAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select pg from ProductGuarantee pg where pg.status = :status and (pg.inputDate between :startDate and :endDate ) and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeWaitingForReturnAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select pg from ProductGuarantee pg where pg.status = :status and (pg.inputDate between :startDate and :endDate ) and pg.delete = true ")
    Page<ProductGuaranteeProjection> findProductGuaranteeDeliveredAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);



}