package coffee_dao;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

	public class AccountDAO {

	    private EntityManagerFactory emf;

	    public AccountDAO(EntityManagerFactory emf) {
	        this.emf = emf;
	    }

	    public void createAccount(Account account) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            em.persist(account);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }
	    
	    public Account getAccountByUsernameAndPassword(String username, String password) {
	        EntityManager em = emf.createEntityManager();
	        
	        try {
	            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password", Account.class)
	                    .setParameter("username", username)
	                    .setParameter("password", password);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null; 
	        } finally {
	            em.close();
	        }
	    }

	    public Account getAccountById(int i) {
	        EntityManager em = emf.createEntityManager();
	        Account account = em.find(Account.class, i);
	        em.close();
	        return account;
	    }

	    public List<Account> getAllAccounts() {
	        EntityManager em = emf.createEntityManager();
	        List<Account> accounts = em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
	        em.close();
	        return accounts;
	    }

	    public void updateAccount(Account account) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            em.merge(account);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

	    public void deleteAccount(int i) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            Account account = em.find(Account.class, i);
	            if (account != null) {
	                em.remove(account);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }
	}

