package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.dto.dto.*;
import com.example.graduationprojectbe.dto.projection.CustomerSearchInfo;
import com.example.graduationprojectbe.dto.projection.ProductGuaranteeProjection;
import com.example.graduationprojectbe.dto.projection.ProductInfo;
import com.example.graduationprojectbe.dto.projection.ProductProjection;
import com.example.graduationprojectbe.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByIme(String ime);

    // #################################################################################
    //########################## Khách Hàng #########################################
    @Query("select p from Product p join p.customer c where (:ime is null or p.ime like %:ime% ) and (:phoneNumber is null or c.phoneNumber like %:phoneNumber% ) ")
    List<CustomerSearchInfo> searchHistoryProductByImeProductOrPhoneNumber(@Param("ime") String ime,@Param("phoneNumber") String phoneNumber);


    // #################################################################################
    //########################## Nhân Viên lễ tân #########################################
    @Query("select p from Product p where p.id = ?1 and p.repair = false and p.delete = true ")
    Page<ProductInfo> getListProductByCustomerId(Pageable pageable, Integer id);

    // tìm kiếm sản phẩm có status = false theo từng khách hàng - 2
    @Query("select new com.example.graduationprojectbe.dto.dto.CustomerDto" +
            "(c.id, c.fullName, c.phoneNumber, c.email, c.address, count(p)) " +
            "from Customer c " +
            "left join Product p on p.customer.id = c.id " +
            "where (c.phoneNumber like %?1% or c.email like %?1% or c.fullName like %?1% ) and c.delete = true " +
            "group by c.id ")
    Page<CustomerDto> searchProductByCustomer(Pageable pageable, String term);

    // lấy ra danh sách sản phẩm sửa chữa ok chờ trả khách có phân trang
    @Query("select p from Product p where p.ime like %?1% and p.status = ?2 and p.delete = true and p.repair = false order by p.outputDate asc ")
    Page<ProductProjection> findProductWaitingReturnCustomerAll(Pageable pageable, String term, Status status);

    // lấy ra danh sách sản phẩm vừa sửa chữa ok xong có phân trang
    @Query("select p from Product p where p.ime like %?1% and p.status = ?2 and p.delete = true and p.repair = false order by p.outputDate asc ")
    Page<ProductProjection> findProductRepairedAll(Pageable pageable, String term, Status status);
    // lấy ra danh sách sản phẩm chờ chuyển cho người sửa chữa có phân trang
    @Query("select p from Product p where p.ime like %?1% and p.status = ?2 and p.delete = true and p.repair = false order by p.inputDate asc ")
    Page<ProductProjection> findProductWaitingForRepairAll(Pageable pageable, String term, Status status);

    // lấy sản phẩm theo id
    @Query("select p from Product p where p.id = ?1 and p.delete = true and p.repair = false ")
    Optional<ProductProjection> findProductByID(Integer id);
    // danh sách sản phẩm đang được sửa chauwx
    @Query("select p from Product p where p.ime like %?1% and p.status = ?2 and p.delete = true and p.repair = false order by p.inputDate asc ")
    Page<ProductProjection> findProductUnderRepairAll(Pageable pageable, String term, Status status);

    // danh sách sản phẩm đang pending trong cửa hàng
    @Query("select p from Product p where p.ime like %?1% and (p.status = ?2 or p.status = ?3) and p.delete = true and p.repair = false ")
    Page<ProductProjection> findProductPendingAll(Pageable pageable, String term, Status status, Status status1);




    // #################################################################################
    //########################## Nhân Viên sửa chữa #########################################


    // lấy danh sách sản phẩm đang sửa theo tên user
    @Query("select p from Product p where p.ime like %?1% and p.engineer.id = ?2 and p.status = ?3 and p.delete = true and p.repair = false ")
    Page<ProductProjection> getListProductNewByUser(Pageable pageable, String term, Integer id, Status status);

    // lấy ra sản phẩm theo id
    @Query("select p from Product p where p.id = ?1 and p.engineer.id = ?2 and p.delete = true and p.repair = false ")
    Optional<ProductProjection> getProductNewById(Integer productId, Integer userId);


    @Query("select p from Product p where p.id = ?1 and p.engineer.id = ?2 and p.status = ?3 and p.delete = true and p.repair = false ")
    Optional<Product> findProductById_Engineer(Integer productId, Integer userId, Status status);




    // #################################################################################
    //########################## Nhân Viên Bảo Hành #########################################


    // danh sách sản phẩm đã tra khách hàng
    @Query("select p from Product p where p.ime like %?1% and p.status = ?2 and p.delete = true  and p.repair = false ")
    Page<ProductProjection> findProductDeliveredAll(Pageable pageable, String term, Status status);

    // lấy sản phẩm new đã trả khách hàng theo id
    @Query("select p from Product p where p.id = ?1 and p.status = ?2 and p.delete = true and p.repair = false ")
    Optional<ProductProjection> findProductDeliveredByID(Integer id, Status status);



    @Query("select new com.example.graduationprojectbe.dto.dto.ProductSummaryDto(" +
            "sum (case when (p.status != :Delivered ) then 1 else 0 end )," +
            "sum (case when function('DATE', p.inputDate) = current_date then 1 else 0 end )," +
            "sum (case when p.status = :Delivered and function('DATE', p.finishDate) = current_date then 1 else 0 end ) " +
            ") " +
            "from Product p where p.delete = true ")
    ProductSummaryDto findStatisticsTotalProductToday(@Param("Delivered") Status Delivered);

    @Query("select p from Product p left join Receipt r on r.product.id = p.id where r.product.id is null and p.ime like %?1% ")
    Page<ProductProjection> findProductNoCreateReceiptAll(Pageable pageable, String term);


    // #################################################################################
    //########################## ADMIN #########################################
    @Query("select count(p) from Product p " +
            "join p.engineer u " +
            "join u.roles rl " +
            "where u.employeeCode = :employeeCode and rl.name = 'NHANVIENSUACHUA' and function('DATE', p.outputDate) = :today and p.delete = true ")
    long countTotalProductOKByEmployeeCode(@Param("employeeCode") String employeeCode,@Param("today") LocalDate today);

    @Query("select count(p) from Product p " +
            "join p.engineer u " +
            "join u.roles rl " +
            "where u.employeeCode = ?1 and rl.name = 'NHANVIENSUACHUA' and p.status = ?2 and p.delete = true ")
    long countTotalProductPendingByEmployeeCode(String employeeCode, Status underRepair);

    @Query("select count(p) from Product p " +
            "join p.engineer u " +
            "join u.roles rl " +
            "where u.employeeCode = :employeeCode and rl.name = 'NHANVIENSUACHUA' and function('DATE', p.outputDate) = :previousDate and p.delete = true ")
    long countTotalProductOKYesterdayByEmployeeCode(@Param("employeeCode") String employeeCode,@Param("previousDate") LocalDate previousDate);

    @Query("select count(p) from Product p " +
            "join p.engineer u " +
            "join u.roles rl " +
            "where u.employeeCode = :employeeCode " +
            "and rl.name = 'NHANVIENSUACHUA' " +
            "and p.status = :underRepair and p.delete = true ")
    long countTotalProductPendingYesterdayByEmployeeCode(@Param("employeeCode") String employeeCode,@Param("underRepair") Status underRepair);


    @Query("select p from Product p where p.status = ?1 and function('DATE', p.outputDate) = CURRENT_DATE and p.delete = true ")
    Page<ProductProjection> findProductOKAll_ADMIN(Pageable pageable, Status repaired);

    @Query("select p from Product p where p.status = ?1 ")
    Page<ProductProjection> findProductPendingAll_ADMIN(Pageable pageable, Status underRepair);


    @Query("select p from Product p where p.status = :status and (p.inputDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductWaitingForRepairAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select p from Product p where p.status = :status and (p.transferDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductUnderRepairAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select p from Product p where p.status = :status and (p.outputDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductRepairedAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select p from Product p where p.status = :status and (p.createGuaranteeDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductWaitingForReturnAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select p from Product p where p.status = :status and (p.finishDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductDeliveredAll_ADMIN(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") Status status);

    @Query("select p from Product p where p.status = ?1 and p.delete = true ")
    Page<ProductProjection> findProductAllByStartDateAndEndDateIsNull_ADMIN(Pageable pageable, Status status);

    @Query("select p from Product p where p.delete = true ")
    Page<ProductProjection> findProductAll(Pageable pageable);

    @Query("select p from Product p where ( p.inputDate between :startDate and :endDate ) and p.delete = true ")
    Page<ProductProjection> findProductAllByStartDateAndEndDate(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);

    @Query("select new com.example.graduationprojectbe.dto.dto.ProductNameModelLimitDto(p.nameModel, count (p)) " +
            "from Product p group by p.nameModel order by count (p) desc ")
    List<ProductNameModelLimitDto> findProductByNameModelLimit();

    @Query("select new com.example.graduationprojectbe.dto.dto.ProductTotalOkAndPendingDto " +
            "(sum (case when p.status = ?1 then 1 else 0 end )," +
            "sum (case when p.status <> ?1 then 1 else 0 end )) " +
            "from Product p ")
    ProductTotalOkAndPendingDto findTotalProductOkAndPending(Status status);

    @Query("select new com.example.graduationprojectbe.dto.dto.TotalProductByEngineerDto" +
            "(u.employeeCode, u.employeeName, count (p.id)) " +
            "from Product p " +
            "join p.engineer u " +
            "where p.status = ?1 " +
            "group by u.employeeCode, u.employeeName order by count (p.id)")
    List<TotalProductByEngineerDto> findTotalProductByEngineer(Status status);

    @Query("select p from Product p where p.status = ?1 and p.delete = true ")
    Page<ProductProjection> findProductWaitingForReturnAll(Pageable pageable, Status status);


    // #################################################################################
    //########################## Search #########################################

    @Query("select p from Product p where p.ime like %?1% ")
    Page<ProductProjection> searchHistoryProductByTerm(Pageable pageable, String term);

    @Query("select p from Product p where p.inputDate between :startDate and :endDate ")
    Page<ProductProjection> searchHistoryProductByStartDateAndEndDate(Pageable pageable, @Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);

    @Query("select p from Product p where (p.inputDate between :startDate and :endDate) and p.ime like %:term% ")
    Page<ProductProjection> searchHistoryProduct(Pageable pageable,@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("term") String term);
}