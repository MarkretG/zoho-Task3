package bankingInfo;
import accountInfo.Account;
import customerInfo.Customer;
import java.util.ArrayList;
import java.util.Scanner;
public class BankingManagementSystem {
    private Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

    }
    public  ArrayList<Customer> getCustomersInfo ()
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
            System.out.println("enter mail id");
            String mail = scanner.nextLine();
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
    public ArrayList<Account> getAccountsInfo () {
        ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("How many number of rows");
        int accountRows = scanner.nextInt();
        for (int i = 0; i < accountRows; i++) {
            System.out.println("enter customer_id");
            long customer_id = scanner.nextLong();
            System.out.println("enter account number");
            long account_no = scanner.nextLong();
            System.out.println("enter balance");
            float balance = scanner.nextInt();
            Account account = new Account();
            account.setCustomer_id(customer_id);
            account.setAccount_id(account_no);
            account.setBalance(balance);
            accounts.add(account);
        }
        return accounts;
    }
}
