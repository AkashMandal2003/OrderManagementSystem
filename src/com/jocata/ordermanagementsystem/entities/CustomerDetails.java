package com.jocata.ordermanagementsystem.entities;

import java.io.Serializable;
import java.util.Objects;

public class CustomerDetails implements Serializable {

    private Integer customerId;
    private String customerName;
    private String email;
    private String password;
    private String address;

    @Override
    public int hashCode() {
        return Objects.hash(1);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass() !=obj.getClass()) return false;
        CustomerDetails customerDetails= (CustomerDetails) obj;
        return customerId.equals(customerDetails.getCustomerId());
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
