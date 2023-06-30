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
@Table(name = "bill")
public class Bill {
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
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @PrePersist
    public void prePersist() {
        this.delete = true;
        this.invoiceCreationDate = LocalDateTime.now();
    }
}