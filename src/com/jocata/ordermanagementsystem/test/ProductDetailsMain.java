package com.jocata.ordermanagementsystem.test;

import com.jocata.ordermanagementsystem.controllers.ProductController;
import com.jocata.ordermanagementsystem.forms.ProductForm;

import java.util.List;

public class ProductDetailsMain {
    public static void main(String[] args) {

        ProductController productController = new ProductController();
        ProductForm newProduct = new ProductForm();
        newProduct.setProductName("Product d");
        newProduct.setProductPrice("700.50");
        newProduct.setProductInStock("25");

        ProductForm savedProduct = productController.saveProduct(newProduct);
        System.out.println("Saved Product: " + savedProduct.getProductId() + ", " + savedProduct.getProductName());

//        ProductForm productForm = productController.getProduct(454789);
//        System.out.println("Retrieved Product: " + productForm.getProductId() + ", " + productForm.getProductName());
//
//        productForm.setProductName("Updated Product A");
//        productForm.setProductPrice("120.00");
//        productForm.setProductInStock("60");
//        ProductForm updatedProduct = productController.updateProduct(productForm);
//        System.out.println("Updated Product: " + updatedProduct.getProductId() + ", " + updatedProduct.getProductName());

        List<ProductForm> allProducts = productController.getAllProducts();
        System.out.println("List of all products:");
        for (ProductForm product : allProducts) {
            System.out.println("ID: " + product.getProductId() + ", Name: " + product.getProductName() +
                    ", Price: " + product.getProductPrice() + ", In Stock: " + product.getProductInStock());
        }
//
//        productController.deleteProduct(101);
    }
}
