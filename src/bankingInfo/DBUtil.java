package bankingInfo;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.*;
import java.util.ArrayList;
public class DBUtil {
    private  static Connection connection=null;
    public static Connection getConnection() {
        if (connection != null)
        {
            return connection;
        }
            try {
                String url = "jdbc:mysql://localhost/info";
                String userName = "root";
                String password = "Root@123";
                // load the Driver Class
                Class.forName("com.mysql.cj.jdbc.Driver");
                // create the connection now
                connection = DriverManager.getConnection(url, userName,password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return connection;
    }
    public static void insertRowsInAccountTable(ArrayList<Account> account) throws SQLException {
        Connection con = getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into account_info(customer_id,account_id,balance) values(?,?,?)")) {
            for (Account accounts : account) {
                preparedStatement.setLong(1, accounts.getCustomer_id());
                preparedStatement.setLong(2, accounts.getAccount_id());
                preparedStatement.setDouble(3, accounts.getBalance());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    //insert rows in customer table in db
    public static void insertRowsInCustomerTable(ArrayList<Customer> customers) throws SQLException {
        Connection con =getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into customer_info(name,age,phone) values(?,?,?,?)")) {
            for (Customer customer : customers) {
                preparedStatement.setLong(1, customer.getCustomer_id());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.setLong(5, customer.getPhone());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
    //customer table all rows  are stored in customer hashmap
    public static ArrayList<Customer> getCustomersListFromCustomerTable() throws SQLException {
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection = getConnection();
        try ( Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select customer_id,name from customer_info")) {
            while (resultSet.next()) {
                Customer customer=new Customer();
                customer.setCustomer_id(resultSet.getLong(1));
                customer.setName(resultSet.getString(2));
                customers.add(customer);
            }
        }
        return customers;
    }
    public static ArrayList<Account> getAccountsListFromAccountTable() throws SQLException {
         ArrayList<Account> accounts=new ArrayList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from  account_info")) {
            while (resultSet.next()) {
                Account account=new Account();
                account.setCustomer_id(resultSet.getLong(1));
                account.setAccount_id(resultSet.getLong(2));
                account.setBalance(resultSet.getDouble(3));
                accounts.add(account);
            }
        }
        return  accounts;
    }

    public void closeConnection(){
        if (connection!=null)
        {
            try {

                connection.close();
            }
            catch (SQLException e)
            {
            }
        }
    }
}
