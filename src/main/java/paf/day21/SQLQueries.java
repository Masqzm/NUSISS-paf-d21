package paf.day21;

public class SQLQueries {
    public static final String SQL_GET_ALLROOMS = "SELECT * FROM room";
    // ? - placeholder
    public static final String SQL_GET_ROOMS_LIMITOFFSET = "SELECT * FROM room LIMIT ? OFFSET ?";
    public static final String SQL_GET_ROOM = "SELECT * FROM room WHERE id = ?";
    
    public static final String SQL_DEL_ROOM = "DELETE FROM room WHERE id = ?";
    public static final String SQL_UPDATE_ROOM = "UPDATE room set room_type = ?, price = ? WHERE id = ?";
    public static final String SQL_INSERT_ROOM = "INSERT into room (room_type, price) values (?, ?)";



    public static final String SQL_GET_ALLCUSTOMERS = "SELECT * FROM customer";
    // ? - placeholder
    public static final String SQL_GET_CUSTOMERS_LIMITOFFSET = "SELECT * FROM customer LIMIT ? OFFSET ?";
    public static final String SQL_GET_CUSTOMER = "SELECT * FROM customer WHERE id = ?";

    public static final String SQL_DEL_CUSTOMER = "DELETE FROM customer WHERE id = ?";
    public static final String SQL_UPDATE_CUSTOMER = "UPDATE customer set fullname = ?, email = ? WHERE id = ?";
    public static final String SQL_INSERT_CUSTOMER = "INSERT into customer (fullname, email) values (?, ?)";



    public static final String SQL_INSERT_EMPLOYEE = "insert into employee (first_name, last_name, email, job_title, department, employment_date, salary, isActive) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_DEL_EMPLOYEE = "update employee set active = false where id = ?";
    public static final String SQL_UPDATE_EMPLOYEE = """
        update employee set 
        first_name = ?,
        email = ?,
        job_title = ?,
        department = ?,
        employment_date = ?, 
        salary = ?
        where id = ?
    """;

    public static final String SQL_GET_ALLEMPLOYEES = "select * from employee";
    public static final String SQL_GET_EMPLOYEE = "select * from employee where id = ?";
}
