package com.example.graduationprojectbe.dto.dto;

public class ProductSummaryDto {
    private long totalProductPending;
    private long totalProductInput;
    private long totalProducts;
    private long totalProductsOk;

    public ProductSummaryDto(long totalProductPending, long totalProductInput, long totalProducts, long totalProductsOk) {
        this.totalProductPending = totalProductPending;
        this.totalProductInput = totalProductInput;
        this.totalProducts = totalProducts;
        this.totalProductsOk = totalProductsOk;
    }

    public long getTotalProductPending() {
        return totalProductPending;
    }

    public void setTotalProductPending(long totalProductPending) {
        this.totalProductPending = totalProductPending;
    }

    public long getTotalProductInput() {
        return totalProductInput;
    }

    public void setTotalProductInput(long totalProductInput) {
        this.totalProductInput = totalProductInput;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalProductsOk() {
        return totalProductsOk;
    }

    public void setTotalProductsOk(long totalProductsOk) {
        this.totalProductsOk = totalProductsOk;
    }
}
