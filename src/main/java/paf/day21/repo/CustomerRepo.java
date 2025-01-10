package paf.day21.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21.SQLQueries;
import paf.day21.model.Customer;
import paf.day21.model.exception.ResourceNotFoundException;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        
        // customers = template.query(
        //                 SQLQueries.SQL_GET_ALLCUSTOMERS,
        //                 (result, rowNum) -> {
        //                     Customer cust = new Customer(); 
        //                     cust.setId(result.getInt("id"));
        //                     cust.setFullname(result.getString("fullname"));
        //                     cust.setEmail(result.getString("email"));
        //                 }
        //             );

        // Auto-mapping mtd (ensure model attribute have same name as DB)
        customers = template.query(SQLQueries.SQL_GET_ALLCUSTOMERS, BeanPropertyRowMapper.newInstance(Customer.class));

        if(customers.isEmpty()) 
            throw new ResourceNotFoundException("No records in Customer table");

        return customers;
    }
    
    public List<Customer> getCustomers(int limit, int offset) {
        List<Customer> customers = new ArrayList<>();

        SqlRowSet sqlRowSet = template.queryForRowSet(SQLQueries.SQL_GET_CUSTOMERS_LIMITOFFSET, limit, offset);
        
        while (sqlRowSet.next()) {
            Customer cust = new Customer(sqlRowSet.getInt("id"),
                                sqlRowSet.getString("fullname"),
                                sqlRowSet.getString("email"));
            customers.add(cust);
        }
        
        if(customers.isEmpty()) 
            throw new ResourceNotFoundException("No records in Customer table");

        return customers;
    }

    public Customer getCustomerByID(int id) {
        try {
            Customer cust = template.queryForObject(SQLQueries.SQL_GET_CUSTOMER, BeanPropertyRowMapper.newInstance(Customer.class), id);

            return cust;
        } catch (DataAccessException ex) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found");
        }
    }

    public boolean deleteCustomerByID(int id) {
        int customerDeleted = template.update(SQLQueries.SQL_DEL_CUSTOMER, id);

        return customerDeleted > 0; 
    }
    
    public boolean updateCustomerByID(Customer customer) {
        int customerUpdated = template.update(SQLQueries.SQL_UPDATE_CUSTOMER, customer.getFullname(), customer.getEmail(), customer.getId());

        return customerUpdated > 0; 
    }    

    public boolean insertCustomer(Customer customer) {
        int customerInserted = template.update(SQLQueries.SQL_INSERT_CUSTOMER, customer.getFullname(), customer.getEmail());

        return customerInserted > 0; 
    }
}