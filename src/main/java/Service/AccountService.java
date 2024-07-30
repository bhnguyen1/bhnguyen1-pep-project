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
    
}
