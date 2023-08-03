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
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "pay_date")
    private LocalDateTime payDate;
    @Column(name = "status")
    private boolean status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_create_id")
    private User employeeCreate;

    @PrePersist
    public void prePersist() {
        this.status = true;
        this.payDate = createDate.plusDays(7);
    }
}