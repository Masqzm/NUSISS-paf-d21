package paf.day21;

public class SQLQueries {
    public static final String SQL_GET_ALLROOMS = "SELECT * FROM room";
    public static final String SQL_GET_ALLCUSTOMERS = "SELECT * FROM customer";
    
    // ? - placeholder
    public static final String SQL_GET_CUSTOMERS_LIMITOFFSET = "SELECT * FROM customer LIMIT ? OFFSET ?";

    public static final String SQL_GET_CUSTOMER = "SELECT * FROM customer where id = ?";
}
