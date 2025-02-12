package com.jocata.ordermanagementsystem.db;

import com.jocata.ordermanagementsystem.entities.CustomerDetails;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InMemoryCustomer {

    private static final Logger logger = Logger.getLogger(InMemoryCustomer.class.getName());
    private static final String FILE_NAME = "customers.csv";
    private static final String DELIMITER = ",";

    private static final Map<Integer, CustomerDetails> loginMap = new ConcurrentHashMap<>();

    static {
        loadDataFromFile();
    }

    public static void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            logger.info("No previous data found. Starting with an empty map.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length >= 5) {
                    CustomerDetails customer = new CustomerDetails();
                    customer.setCustomerId(Integer.parseInt(parts[0]));
                    customer.setCustomerName(parts[1]);
                    customer.setEmail(parts[2]);
                    customer.setPassword(parts[3]);
                    customer.setAddress(parts[4]);

                    loginMap.put(customer.getCustomerId(), customer);
                }
            }
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error loading customer data from CSV", e);
        }
    }

    public static void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (CustomerDetails customer : loginMap.values()) {
                writer.println(customer.getCustomerId() + DELIMITER +
                        customer.getCustomerName() + DELIMITER +
                        customer.getEmail() + DELIMITER +
                        customer.getPassword() + DELIMITER +
                        customer.getAddress());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving customer data to CSV", e);
        }
    }

    public static void persist(CustomerDetails user) {
        loginMap.put(user.getCustomerId(), user);
        saveDataToFile();
    }

    public static CustomerDetails read(Integer customerId) {
        return loginMap.get(customerId);
    }

    public static void update(CustomerDetails user) {
        if (loginMap.containsKey(user.getCustomerId())) {
            loginMap.put(user.getCustomerId(), user);
            saveDataToFile();
        } else {
            logger.info("Entry with ID " + user.getCustomerId() + " not found.");
        }
    }

    public static void delete(Integer customerId) {
        if (loginMap.remove(customerId) != null) {
            saveDataToFile();
        } else {
            logger.info("Not Found");
        }
    }
}
