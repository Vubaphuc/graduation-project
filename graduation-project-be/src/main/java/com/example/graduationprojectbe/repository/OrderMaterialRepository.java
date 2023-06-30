package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.dto.OrderMaterialDto;
import com.example.graduationprojectbe.dto.projection.OrderMaterialProjection;
import com.example.graduationprojectbe.entity.OrderMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderMaterialRepository extends JpaRepository<OrderMaterial, Integer> {




    //#####################################################################################################################
    //############################ Nhân Viên Kho ############################################################################

    // danh sách order vật liệu có status = false - 1
    @Query("select od from OrderMaterial od where od.status = false and od.delete = true ")
    Page<OrderMaterialProjection> getListOrderMaterialStatusFalse(Pageable pageable);

    @Query("select od from OrderMaterial od where od.status = true and od.delete = true ")
    Page<OrderMaterialProjection> getListOrderMaterialStatusTrue(Pageable pageable, Integer id);

    @Query("select od from OrderMaterial od where od.id = ?1 and od.delete = true ")
    Optional<OrderMaterialProjection> getOrderMaterialById(Integer id);

    @Query("select od from OrderMaterial od where od.orderCode like %?1% and od.delete = true ")
    Page<OrderMaterialProjection> searchOrderMaterialByTerm(Pageable pageable, String term);


    //#####################################################################################################################
    //############################ Nhân Viên sửa chữa ############################################################################

    // lấy danh sách order đã phê duyệt
    @Query("select od from OrderMaterial od where od.orderer.id = ?1 and od.status = true and od.delete = true ")
    Page<OrderMaterialProjection> getListOrderMaterialByStatusTrue(Pageable pageable, Integer id);

    // lấy danh sách order chưa phê duyệt
    @Query("select od from OrderMaterial od where od.orderer.id = ?1 and od.status = false and od.delete = true ")
    Page<OrderMaterialProjection> getListOrderMaterialByStatusFalse(Pageable pageable, Integer id);



    //#####################################################################################################################
    //############################ ADMIN ############################################################################

    @Query("select od from OrderMaterial od where od.delete = true ")
    Page<OrderMaterialProjection> findOrderMaterialsAll(Pageable pageable);

    @Query("select od from OrderMaterial od where od.delete = true and ( od.createDate between ?1 and ?2 ) ")
    Page<OrderMaterialProjection> findOrderMaterialsAllByStartDateAndEndDate(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);

    @Query("select coalesce(sum(od.quantity * m.price), 0) " +
            "from OrderMaterial od " +
            "join od.material m " +
            "where month(od.approvalDate) = month(current_date) and year(od.approvalDate) = year(current_date) and od.status = true ")
    long totalPriceMaterialOrderThisMonth();

    @Query("select coalesce(sum(od.quantity * m.price), 0) " +
            "from OrderMaterial od " +
            "join od.material m " +
            "where od.status = true and function('DATE', od.approvalDate) = current_date ")
    long totalPriceMaterialOrderToday();

    @Query("select coalesce(sum(od.quantity), 0) " +
            "from OrderMaterial od " +
            "where od.status = true and function('DATE', od.approvalDate) = current_date ")
    long totalQuantityExportMaterialToday();

    @Query("select coalesce(sum(od.quantity), 0) " +
            "from OrderMaterial od " +
            "where od.status = true and month(od.approvalDate) = month(current_date) ")
    long totalQuantityExportMaterialThisMonth();

    // danh sách tổng số lượng export material theo từng mã vật liệu
    @Query("select new com.example.graduationprojectbe.dto.dto.OrderMaterialDto" +
            "(m.code, cp.name, coalesce(sum(od.quantity), 0)) from OrderMaterial od " +
            "join od.material m " +
            "join m.components cp " +
            "where od.status = true " +
            "group by m.code, cp.name ")
    Page<OrderMaterialDto> findListTotalQuantityExportMaterialByMaterialCode(Pageable pageable);


    @Query("select new com.example.graduationprojectbe.dto.dto.OrderMaterialDto" +
            "(m.code, cp.name, coalesce(sum(od.quantity), 0)) from OrderMaterial od " +
            "join od.material m " +
            "join m.components cp " +
            "where od.status = true " +
            "group by m.code, cp.name " +
            "order by coalesce(sum(od.quantity), 0) desc ")
    List<OrderMaterialDto> findTotalQuantityExportMaterialByMaterialCode();
}