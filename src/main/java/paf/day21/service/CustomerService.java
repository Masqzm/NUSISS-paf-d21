package paf.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day21.model.Customer;
import paf.day21.repo.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.getCustomers();
    }
    public List<Customer> getAllCustomers(int limit, int offset) {
        return customerRepo.getCustomers(limit, offset);
    }

    public Customer getCustomerByID(int id) {
        return customerRepo.getCustomerByID(id);
    }
}
