package com.example.graduationprojectbe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "name_mode")
    private String nameModel;
    @Column(name = "import_quantity")
    private int importQuantity;
    @Column(name = "export_quantity")
    private int exportQuantity;
    @Column(name = "remaining_quantity")
    private int remainingQuantity;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "price")
    private double price;
    @Column(name = "is_delete")
    private boolean delete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "components_id")
    private Components components;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_employee_id")
    private User warehouseEmployee;

    public void setImportQuantity(int importQuantity) {
        if (importQuantity < 0 ) {
            this.importQuantity = 0;
        } else {
            this.importQuantity = importQuantity;
        }
        updateRemainingQuantity();
    }

    public void setExportQuantity(int exportQuantity) {
        if (exportQuantity < 0 ) {
            this.exportQuantity = 0;
        } else {
            this.exportQuantity = exportQuantity;
        }
        updateRemainingQuantity();
    }

    private void updateRemainingQuantity() {
        this.remainingQuantity = this.importQuantity - this.exportQuantity;
    }

    @PrePersist()
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.exportQuantity = 0;
        this.delete = true;
    }
    @PostPersist ()
    public void postPersist() {
        this.remainingQuantity = this.importQuantity;
    }

    @PostUpdate()
    public void postUpdate() {
        this.updateDate = LocalDateTime.now();
    }

}