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
@Table(name = "guarantee")
public class Guarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // mã số bảo hành
    @Column(name = "guarantee_code", unique = true)
    private String guaranteeCode;
    // ngày kích hoạt bảo hành
    @Column(name = "activation_date")
    private LocalDateTime activationDate;
    // ngày hết hạn bảo hành
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    // trạng thái bảo hành, status = true => còn hạn bảo hành, status = false => hết hạn bảo hành
    @Column(name = "status")
    private boolean status;
    @Column(name = "is_delete")
    private boolean delete;
    // sản phẩm được bảo hành
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // nhân viên kích hoạt
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_employee_id")
    private User activationEmployee;

    @PrePersist
    public void prePersist () {
        this.delete = true;
        // ngày kích hoạt = ngày tạo bảo hành
        this.activationDate = LocalDateTime.now();
        // mặc định khi mới tạo bảo hành là true => nếu là false thì hết hạn bảo hành
        this.status = true;
        // nhập thời gian hết hạn bảo hành => thời gian tạo bảo hành + số tháng được bảo hành
        this.expirationDate = activationDate.plusMonths(product.getComponents().getWarrantyPeriod());
        if (LocalDateTime.now().isEqual(activationDate)) {
            // nếu thời gian hiện tại = thời gian hết hạn bảo hành => chuyển status = false => hết hạn bảo hành
            this.status = false;
        }
    }

}