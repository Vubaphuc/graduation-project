package com.example.graduationprojectbe.dto.dto;

public class ProductNameModelLimitDto {
    private String nameModel;
    private long sumProduct;

    public ProductNameModelLimitDto(String nameModel, long sumProduct) {
        this.nameModel = nameModel;
        this.sumProduct = sumProduct;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public long getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(long sumProduct) {
        this.sumProduct = sumProduct;
    }
}
