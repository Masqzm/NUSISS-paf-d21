package paf.day21.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf.day21.SQLQueries;
import paf.day21.model.Employee;
import paf.day21.model.exception.ResourceNotFoundException;

@Repository
public class EmployeeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees = template.query(SQLQueries.SQL_GET_ALLEMPLOYEES, BeanPropertyRowMapper.newInstance(Employee.class));

        if (employees.isEmpty()) 
            throw new ResourceNotFoundException("No record in Employee table");

        return employees;
    }

    // public List<Employee> getEmployees(int limit, int offset) {
    //     List<Employee> Employees = new ArrayList<>();

    //     SqlRowSet sqlRowSet = template.queryForRowSet(sql.sql_getEmployees_LimitOffset, limit, offset);

    //     while (sqlRowSet.next()) {
    //         Employee Employee = new Employee();
    //         Employee.setId(sqlRowSet.getInt("id"));;
    //         Employee.setFullname(sqlRowSet.getString("fullname"));
    //         Employee.setEmail(sqlRowSet.getString("email"));
    //         Employees.add(Employee);
    //     }

    //     if (Employees.isEmpty()) {
    //         throw new ResourceNotFoundException("No record in Employee table");
    //     }

    //     return Employees;
    // }

    public Employee getEmployeeById(int id) {

        Employee c = null;
        try {
            c = template.queryForObject(SQLQueries.SQL_GET_EMPLOYEE, BeanPropertyRowMapper.newInstance(Employee.class), id);
        } catch (DataAccessException ex) {
            throw new ResourceNotFoundException("Employee with ID " + id + " not found");
        }

        return c;
    }

    public Boolean deleteEmployeeById(int id) {
        int employeeDeleted = template.update(SQLQueries.SQL_DEL_EMPLOYEE, id);

        return employeeDeleted > 0;
    }

    public Boolean updateEmployeeById(Employee employee) {
        int employeeUpdated = template.update(SQLQueries.SQL_UPDATE_EMPLOYEE, 
                            employee.getFirst_name(), 
                            employee.getEmail(), 
                            employee.getJob_title(), 
                            employee.getDepartment(), 
                            employee.getEmployement_date(), 
                            employee.getSalary());
                                            
        return employeeUpdated > 0;
    }

    public Boolean insertNewEmployee(Employee employee) {
        int employeeCreated = template.update(SQLQueries.SQL_INSERT_EMPLOYEE, 
                            employee.getFirst_name(), 
                            employee.getLast_name(), 
                            employee.getEmail(), 
                            employee.getJob_title(), 
                            employee.getDepartment(), 
                            employee.getEmployement_date(), 
                            employee.getSalary(), true);

        return employeeCreated > 0;
    }
}
