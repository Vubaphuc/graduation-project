package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.dto.VendorTotalMaterialDto;
import com.example.graduationprojectbe.dto.projection.MaterialInfo;
import com.example.graduationprojectbe.dto.projection.MaterialProjection;
import com.example.graduationprojectbe.dto.projection.OrderMaterialProjection;
import com.example.graduationprojectbe.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Optional<Material> findByCodeAndDeleteTrue(String code);

    //#####################################################################################################################
    //############################ Nhân Viên Kho ############################################################################
    @Query("select m from Material m where m.remainingQuantity > 0 and m.delete = true order by m.remainingQuantity asc ")
    Page<MaterialProjection> getListMaterialAll(Pageable pageable);

    @Query("select m from Material m where (m.code like %?1% or m.nameModel like %?1%) and m.delete = true ")
    Page<MaterialProjection> searchHistoryMaterial(Pageable pageable, String term);

    @Query("select new com.example.graduationprojectbe.dto.dto.VendorTotalMaterialDto" +
            "(vd.id, vd.name, count (m.id)) " +
            "from Material m " +
            "left join Vendor vd on vd.id = m.vendor.id " +
            "where m.delete = true " +
            "group by vd.id " +
            "having count (m.id) > 0 " +
            "order by count (m.id) desc ")
    Page<VendorTotalMaterialDto> getListVendorTotalMaterial(Pageable pageable);


    @Query("select m from Material m where m.vendor.id = ?1 and m.delete = true order by m.remainingQuantity desc ")
    Page<MaterialProjection> getListVendorById(Pageable pageable, int vendorId);

    @Query("select m from Material m where m.id = ?1 and m.delete = true ")
    Optional<MaterialProjection> getMaterialById(Integer id);




    //#####################################################################################################################
    //############################ Nhân Viên sửa chữa ############################################################################

    // danh sách vật liệu còn hàng
    @Query("select m from Material m where m.remainingQuantity > 0 and m.delete = true ")
    Page<MaterialProjection> getListMaterialByQuantity(Pageable pageable);


    //#####################################################################################################################
    //############################ ADMIN ############################################################################
    @Query("select coalesce(sum(m.importQuantity * m.price), 0) from Material m ")
    long totalPriceMaterialImportQuantity();

    @Query("select coalesce(sum(m.exportQuantity * m.price), 0) from Material m ")
    long totalPriceMaterialExportQuantity();

    @Query("select coalesce(sum(m.importQuantity), 0) from Material m ")
    long totalQuantityImportMaterial();

    @Query("select coalesce(sum(m.exportQuantity), 0) from Material m ")
    long totalQuantityExportMaterial();

    @Query("select m from Material m where m.code like %?1% ")
    Page<MaterialProjection> findMaterialsAll(Pageable pageable, String term);

    @Query("select m from Material m where ( m.createDate between :startDate and :endDate ) and m.code like %:term% ")
    Page<MaterialProjection> findMaterialsAllByStartDateAndEndDate(Pageable pageable, @Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("term") String term);

    @Query("select m from Material m order by m.remainingQuantity desc ")
    List<MaterialProjection> findMaterialRemainingQuantityLimit();
}