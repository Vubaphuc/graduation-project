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
@Table(name = "bill_guarantee")
public class BillGuarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // ngày tạo hóa đơn
    @Column(name = "invoice_creation_date")
    private LocalDateTime invoiceCreationDate;
    @Column(name = "is_delete")
    private boolean delete;

    // người tạo hóa đơn => là người trả sản phẩm cho khách
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_creator_id")
    private User invoiceCreator;

    // thông tin sản phẩm => bao gồm tiền và thông tin khách hàng theo sản phẩm
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_guarantee_id")
    private ProductGuarantee productGuarantee;





    @PrePersist
    public void prePersist() {
        this.delete = true;
        this.invoiceCreationDate = LocalDateTime.now();
    }
}