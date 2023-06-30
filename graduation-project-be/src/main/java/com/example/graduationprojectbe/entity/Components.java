package com.example.graduationprojectbe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "components")
public class Components {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // tên các linh kiện điện thoại
    @Column(name = "name", unique = true)
    private String name;
    // thời hạn bảo hành => tính theo tháng
    @Column(name = "warranty_period")
    private Integer warrantyPeriod;

    @Column(name = "create_date")
    private LocalDateTime createDate;


    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "is_delete")
    private boolean delete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_employee_id")
    private User warehouseEmployee;

    @PrePersist
    public void prePersist() {
        this.delete = true;
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}