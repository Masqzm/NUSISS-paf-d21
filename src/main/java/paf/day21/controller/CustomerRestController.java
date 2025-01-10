package paf.day21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paf.day21.model.Customer;
import paf.day21.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    CustomerService customerSvc;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerSvc.getAllCustomers();

        return ResponseEntity.ok().body(customers);
    }
    @GetMapping("/q")
    public ResponseEntity<List<Customer>> getAllCustomersWLimitOffset(@RequestParam int limit, @RequestParam int offset) {
        List<Customer> customers = customerSvc.getAllCustomers(limit, offset);

        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable int id) {
        Customer cust = customerSvc.getCustomerByID(id);

        return ResponseEntity.ok().body(cust);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomerByID(@PathVariable int id) {
        boolean success = customerSvc.deleteCustomerByID(id);

        return ResponseEntity.ok().body(success);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCustomerByID(@PathVariable int id, @RequestBody Customer customer) {
        boolean success = false;

        // Retrieve to check customer exists
        if(customerSvc.getCustomerByID(id) != null)
            success = customerSvc.updateCustomerByID(customer);

        return ResponseEntity.ok().body(success);
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> insertCustomer(@RequestBody Customer customer) {
        boolean success = customerSvc.insertCustomer(customer);

        return ResponseEntity.ok().body(success);
    }
}
