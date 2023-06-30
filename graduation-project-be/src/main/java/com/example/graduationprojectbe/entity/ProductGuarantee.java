package com.example.graduationprojectbe.entity;

import com.example.graduationprojectbe.constants.Status;
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
@Table(name = "product_guarantee")
public class ProductGuarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "product_id")
    private Integer productId;
    // tên model
    @Column(name = "name_model")
    private String nameModel;
    // công ty sản xuất
    @Column(name = "phone_company")
    private String phoneCompany;
    // số ime
    @Column(name = "ime")
    private String ime;
    // tên lỗi
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "cause")
    private String cause;
    // trạng thái sản phẩm status = false: đang pending chờ sửa, status = true: đã sửa xong;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "price")
    private double price;
    // isRepair = true => hàng bảo hành
    @Column(name = "repair")
    private boolean repair;
    // ngày hàng nhận vào cửa hàng
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    // ngày sản phẩm chuyển cho người sửa chữa
    @Column(name = "transfer_date")
    private LocalDateTime transferDate;
    // vị trí sửa => tên linh kiện ()
    @Column(name = "location")
    private String location;
    @Column(name = "note")
    private String note;
    // ngày sửa chữa hoàn thành;
    @Column(name = "output_date")
    private LocalDateTime outputDate;
    @Column(name = "create_guarantee_date")
    private LocalDateTime createGuaranteeDate;
    // ngày trả sản phẩm cho khách => ngày hoàn thành quá trình sửa chữa product
    @Column(name = "finish_date")
    private LocalDateTime finishDate;
    @Column(name = "is_delete")
    private boolean delete;
    // nhân viên nhận sản phẩm. có thể là nhân viên lễ tân hoặc bảo hành
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warranty_id")
    private User warranty;
    // nhân viên sửa chữa (kỹ sư)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engineer_id")
    private User engineer;
    // thông tin khách hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    // loại linh kiện sửa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "components_id")
    private Components components;
    // người trả sản phẩm
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_payer_id")
    private User productPayer;


    @PrePersist
    public void prePersist() {
        this.inputDate = LocalDateTime.now();
        this.repair = true;
        this.delete = true;
        this.status= Status.WAITING_FOR_REPAIR;
    }
}
