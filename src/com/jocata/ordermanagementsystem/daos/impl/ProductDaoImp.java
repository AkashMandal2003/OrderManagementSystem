package com.jocata.ordermanagementsystem.daos.impl;

import com.jocata.ordermanagementsystem.daos.ProductDao;
import com.jocata.ordermanagementsystem.db.InMemoryProduct;
import com.jocata.ordermanagementsystem.entities.ProductDetails;

import java.util.List;

public class ProductDaoImp implements ProductDao {

    @Override
    public ProductDetails saveProduct(ProductDetails productDetails) {
        InMemoryProduct.persist(productDetails);
        return productDetails;
    }

    @Override
    public ProductDetails getProduct(Integer productId) {
        return InMemoryProduct.read(productId);
    }

    @Override
    public List<ProductDetails> getAllProducts() {
        return InMemoryProduct.getAllProducts();
    }

    @Override
    public ProductDetails updateProduct(ProductDetails productDetails) {
        InMemoryProduct.update(productDetails);
        return productDetails;
    }

    @Override
    public void deleteProduct(Integer productId) {
        InMemoryProduct.delete(productId);
    }
}
