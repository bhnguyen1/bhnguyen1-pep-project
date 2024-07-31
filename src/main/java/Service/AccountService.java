package Service;

import Model.Account;
import DAO.AccountDAO;

/*
 Purpose of this class is to contain business logic between web layer and DAO layer 

 what logic is needed
    register a new user with password
    cases:
    * can't be a blank username
    * can't be a password with less than a length of 4 characters
    * can't use a username that already exists 
    * success
    -------------------------------------------------------------------
    login into account with password
    cases: 
    * invalid username
    * invalid password
    * success
*/

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() { //constructor
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) { //constructor for when AccountDAO is provided
        this.accountDAO = accountDAO;
    }

    //also check the logic of this
    public Account loginIntoAccount(String username, String password) {
        Account account = accountDAO.getAccountWithPassword(username, password);
        if (account != null) {
            return account;
        } else {
            return null;
        }
    }
    
    // ask if the logic here is okay
    public Account addNewAccount(Account account) { 
        if(accountDAO.checkAccount(account.getUsername())) {
            System.out.println("Username already exists!");
            return null;
        }
        if(account.getUsername().isEmpty() || account.getPassword().length() < 4) {
            System.out.println("Invalid username or password!");
            return null; 
        } 
        return accountDAO.insertAccount(account);
    }
}
