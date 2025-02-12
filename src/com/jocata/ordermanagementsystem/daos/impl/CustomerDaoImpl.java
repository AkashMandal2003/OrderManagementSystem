package com.jocata.ordermanagementsystem.daos.impl;

import com.jocata.ordermanagementsystem.daos.CustomerDao;
import com.jocata.ordermanagementsystem.db.InMemoryCustomer;
import com.jocata.ordermanagementsystem.entities.CustomerDetails;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public CustomerDetails saveCustomer(CustomerDetails customerDetails) {
        InMemoryCustomer.persist(customerDetails);
        return customerDetails;
    }

    @Override
    public CustomerDetails getCustomer(Integer customerId) {
        return InMemoryCustomer.read(customerId);
    }

    @Override
    public CustomerDetails updateCustomer(CustomerDetails customerDetails) {
        InMemoryCustomer.update(customerDetails);
        return customerDetails;
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        InMemoryCustomer.delete(customerId);
    }
}
