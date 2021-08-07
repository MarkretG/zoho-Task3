package bankingInfo;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class BankingManagementSystem {
    private static BankingManagementSystem singleInstance=null;
    public static synchronized BankingManagementSystem getInstance()
    {
        // To ensure only one instance is created
        if (singleInstance == null)
        {
            singleInstance= new BankingManagementSystem();
        }
        return singleInstance;
    }
    private Scanner scanner=new Scanner(System.in);
    public static void main(String[] args)throws SQLException {
        BankingSystem bankingSystem=new BankingSystem();
        System.out.println("1.New Customer\n2.New Account for existing customer\n3.get account info\n4.exit");
        while(true)
        {
            int choice=BankingManagementSystem.getInstance().scanner.nextInt();
            switch (choice)
            {
                case 1:
                    bankingSystem.handleNewCustomer();
                    break;
                case 2:
                    bankingSystem.initialiseAccounts();
                    bankingSystem.setAccountHashMap();
                    break;
                case 3:
                    System.out.println("enter customer id");
                    long customerId = BankingManagementSystem.getInstance().scanner.nextLong();
                    HashMap<Long, Account> account=bankingSystem.getAccountsInfoFromAccountsInfoHashMap(customerId);
                    System.out.println(account.toString());
                    break;
                case 4:
                    DBUtil.closeConnection();
                    System.exit(0);

            }

        }

    }
    public ArrayList<Customer> getCustomersInfo ()
    {
        ArrayList<Customer> customers=new ArrayList<>();
        System.out.println("How many number of rows");
        int customerRows =scanner.nextInt();
        for (int i = 0; i < customerRows; i++) {
            //System.out.println("Enter customer_id");
            //long customer_id = scanner.nextLong();
            System.out.println("enter name");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("enter age");
            int age = scanner.nextInt();
            System.out.println("enter phone Number");
            long phone = scanner.nextLong();
            Customer customer = new Customer();
            //customer.setCustomer_id(customer_id);
            customer.setName(name);
            customer.setAge(age);
            customer.setPhone(phone);
            customers.add(customer);
        }
        return customers;
    }
    //get input from user and add list of accounts in arraylist then return arraylist
    public  ArrayList<Account> getAccountsInfo () {
        ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("How many number of rows");
        int accountRows = scanner.nextInt();
        for (int i = 0; i < accountRows; i++) {
            System.out.println("enter customer_id");
            long customer_id = scanner.nextLong();
            System.out.println("enter account Id");
            long account_id = scanner.nextLong();
            System.out.println("enter balance");
            double balance = scanner.nextDouble();
            Account account = new Account();
            account.setCustomer_id(customer_id);
            account.setAccount_id(account_id);
            account.setBalance(balance);
            accounts.add(account);
        }
        return accounts;
    }
}
