package paf.day21.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String job_title;
    private String department;
    private Date employement_date;
    private float salary;

    public Employee() {}
    public Employee(int id, String first_name, String last_name, String email, String job_title, String department,
            Date employement_date, float salary) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.job_title = job_title;
        this.department = department;
        this.employement_date = employement_date;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
                + ", job_title=" + job_title + ", department=" + department + ", employement_date=" + employement_date
                + ", salary=" + salary + "]";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJob_title() {
        return job_title;
    }
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Date getEmployement_date() {
        return employement_date;
    }
    public void setEmployement_date(Date employement_date) {
        this.employement_date = employement_date;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
}


