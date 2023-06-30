package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.dto.VendorCountDto;
import com.example.graduationprojectbe.dto.projection.VendorInfo;
import com.example.graduationprojectbe.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    Optional<Vendor> findByName(String name);

    //#####################################################################################################################
    //############################ Nhân Viên Kho ############################################################################
    @Query("select new com.example.graduationprojectbe.dto.dto.VendorCountDto(vd.id, vd.name, count(m.id)) " +
            "from Vendor vd " +
            "left join Material m on m.vendor.id = vd.id " +
            "where vd.delete = true " +
            "group by vd.id, vd.name ")
    Page<VendorCountDto> getListVendorAll(Pageable pageable);

    @Query("select new com.example.graduationprojectbe.dto.dto.VendorCountDto(vd.id, vd.name, count(m.id)) " +
            "from Vendor vd " +
            "left join Material m on m.vendor.id = vd.id " +
            "where vd.delete = true " +
            "group by vd.id, vd.name ")
    List<VendorCountDto> getVendorByMaterial();

    // lấy ra vendor theo ID - 11
    @Query("select vd from Vendor vd where vd.id = ?1 and vd.delete = true ")
    Optional<VendorInfo> getVendorById(Integer id);

    // lấy ra vendor theo name - 11
    @Query("select vd from Vendor vd where vd.id = ?1 and vd.delete = true ")
    Optional<VendorInfo> getVendorByName(String name);
}