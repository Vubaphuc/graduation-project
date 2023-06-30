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
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // tên  vendor
    @Column(name = "name")
    private String name;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "is_delete")
    private boolean delete;
    // nhân viên kho
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_employee_id")
    private User warehouseEmployee;


    @PrePersist
    public void prePersist() {
        this.delete = true;
        this.createDate = LocalDateTime.now();
    }
}