package bankingInfo;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class BankingSystem {
    private HashMap<Long,String> customerHashMap=new HashMap<>();
    private HashMap<Long,HashMap<Long,Account>> accountInfoHashMap=new HashMap<>();
   // private ArrayList<Customer> customer=new ArrayList<>();
   // private ArrayList<Account> account=new ArrayList<>();
    BankingManagementSystem bankingManagementSystem=new BankingManagementSystem();
    public void initialiseCustomer() throws SQLException
    {
        ArrayList<Customer> customers=bankingManagementSystem.getCustomersInfo();
        DBUtil.insertRowsInCustomerTable(customers);
        //Collections.copy(customer,customers);
    }
    public void initialiseAccounts() throws SQLException
    {
        ArrayList<Account> accounts=bankingManagementSystem.getAccountsInfo();
        DBUtil.insertRowsInAccountTable(accounts);
        //Collections.copy(account,accounts);
    }
    public  void setCustomerHashMap()throws SQLException
    {
        ArrayList<Customer> customers=DBUtil.getCustomersListFromCustomerTable();
        for (Customer customer:customers)
        {
            customerHashMap.put(customer.getCustomer_id(),customer.getName());
        }
        //customers.clear();
    }
    public void setAccountHashMap()throws SQLException
    {
        ArrayList<Account> accounts=DBUtil.getAccountsListFromAccountTable();
        for (Account account:accounts)
        {
            HashMap accountHashMap = accountInfoHashMap.get(account.getCustomer_id());
            if (accountHashMap == null) {
                accountHashMap = new HashMap<Long, Account>();
            }
            accountHashMap.put(account.getAccount_id(), account);
            accountInfoHashMap.put(account.getCustomer_id(), accountHashMap);
        }
        //accounts.clear();
    }
    public void handleNewCustomer() throws  SQLException
    {
        ArrayList<Customer> customers= bankingManagementSystem.getCustomersInfo();
        DBUtil.insertRowsInCustomerTable(customers);
        setCustomerHashMap();
        ArrayList<Account> accounts=bankingManagementSystem.getAccountsInfo();
        DBUtil.insertRowsInAccountTable(accounts);
        setAccountHashMap();
    }

    public HashMap<Long, Account> getAccountsInfoFromAccountsInfoHashMap(long id) {
        return accountInfoHashMap.get(id);
    }
}
