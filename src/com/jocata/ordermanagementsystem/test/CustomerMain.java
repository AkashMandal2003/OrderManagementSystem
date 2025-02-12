package com.jocata.ordermanagementsystem.test;

import com.jocata.ordermanagementsystem.controllers.CustomerController;
import com.jocata.ordermanagementsystem.entities.CustomerDetails;
import com.jocata.ordermanagementsystem.forms.CustomerForm;

public class CustomerMain {
    public static void main(String[] args) {

        CustomerController customerController=new CustomerController();

//        CustomerForm customer1=new CustomerForm();
//        customer1.setCustomerName("Abc");
//        customer1.setEmail("abc@email.com");
//        customer1.setPassword("abc");
//        customer1.setAddress("Banjara hills");

//        CustomerForm customerForm = customerController.saveCustomer(customer1);

        CustomerForm customerForm = customerController.getCustomer(481063);

//        CustomerForm customer1=new CustomerForm();
//        customer1.setCustomerId("481063");
//        customer1.setCustomerName("Abcd");
//        customer1.setEmail("abcd@email.com");
//        customer1.setPassword("abcd");
//        customer1.setAddress("Banjara hills");
//
//        CustomerForm customerForm = customerController.updateCustomer(customer1);
//
//        String string = customerController.deleteCustomer(481063);
        System.out.println(customerForm.getCustomerId()+","+customerForm.getCustomerName());

    }
}
