package paf.day21.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21.SQLQueries;
import paf.day21.model.Customer;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        
        // customers = template.query(
        //                 SQLQueries.SQL_GET_ALLCUSTOMERS,
        //                 (result, int) -> {
        //                     Customer cust = new Customer(); 
        //                     cust.setId(result.getInt("id"));
        //                     cust.setFullname(result.getString("fullname"));
        //                     cust.setEmail(result.getString("email"));
        //                 }
        //             );

        // Auto-mapping mtd (ensure model attribute have same name as DB)
        customers = template.query(SQLQueries.SQL_GET_ALLCUSTOMERS, BeanPropertyRowMapper.newInstance(Customer.class));

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

        return customers;
    }

    public Customer getCustomerByID(int id) {
        return template.queryForObject(SQLQueries.SQL_GET_CUSTOMER, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }
}