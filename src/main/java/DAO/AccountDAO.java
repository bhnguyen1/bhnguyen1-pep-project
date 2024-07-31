package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

/*
 Purpose of this DAO class is to mediate the transformation of data between the format of objects in Java to rows in a database. 

 Use table named: account
 account_id (primary key) : type int
 username (unique) : type varchar(255)
 password: type varchar(255)

 what information should i get or insert into this table
    register a new user with password (insert)
    -------------------------------------------------------------------
    check if account exists (select)
    -------------------------------------------------------------------
    login into account with password (select) 
*/

public class AccountDAO {
    /*
     * Checking the account exists by username 
    */
    public boolean checkAccount(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select count(*) from account where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //setString methods
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /* 
     * Inserting a new user
     * Based off the assumption that account_id is an automatically generated because it is set to auto_increment
     * Only need to worry about username and password
    */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "insert into account (username, password) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setString methods
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int gen_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(gen_account_id, account.getUsername(), account.getPassword());
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
     * If a person is logging in, then the goal of this method is to get the username and password that was inputted
     * Retrieve everything of the account based on username and password
    */
    public Account getAccountWithPassword(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //setString methods
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"), rs.getString("password"));
                return account;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
