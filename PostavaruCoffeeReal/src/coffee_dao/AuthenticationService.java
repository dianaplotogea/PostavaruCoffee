package coffee_dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AuthenticationService {
	 private AccountDAO accountDAO;
	 
	 public AuthenticationService(AccountDAO accountDAO) {
	        this.accountDAO = accountDAO;
	    }

	    public Account authenticate(String username, String password) {
	        try {
	            Account account = accountDAO.getAccountByUsernameAndPassword(username, password);

	            if (account != null) {
	                return account;
	            } else {
	                System.out.println("Authentication failed: Invalid username or password.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Authentication failed: An error occurred.");
	        }

	        return null;
	    }
	    }

	

