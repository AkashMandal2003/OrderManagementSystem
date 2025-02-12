package com.jocata.ordermanagementsystem.entities;

import java.io.Serializable;
import java.util.Objects;

public class ProductDetails implements Serializable {

    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer productInStock;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(Integer productInStock) {
        this.productInStock = productInStock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(1);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass() !=obj.getClass()) return false;
        ProductDetails productDetails= (ProductDetails) obj;
        return productId.equals(productDetails.getProductId());
    }
}
