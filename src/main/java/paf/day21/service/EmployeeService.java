package paf.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day21.model.Employee;
import paf.day21.repo.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public Boolean deleteEmployeeById(int id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    public Boolean updateCustomerById(Employee employee) {
        return employeeRepository.updateEmployeeById(employee);
    }

    public Boolean insertEmployee(Employee employee) {
        return employeeRepository.insertNewEmployee(employee);
    }
}
