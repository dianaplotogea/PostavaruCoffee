package coffee_dao;
	
	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.EntityTransaction;
	import java.util.List;

	public class OrderDAO {


	    private EntityManagerFactory emf;

	    public OrderDAO(EntityManagerFactory emf) {
	        this.emf = emf;
	    }

	    public void createOrder(CoffeeOrder order) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            em.persist(order);
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

	    public CoffeeOrder getOrderById(int id) {
	        EntityManager em = emf.createEntityManager();
	        CoffeeOrder order = em.find(CoffeeOrder.class, id);
	        em.close();
	        return order;
	    }

	    public List<CoffeeOrder> getAllOrders() {
	        EntityManager em = emf.createEntityManager();
	        List<CoffeeOrder> orders = em.createQuery("SELECT o FROM CoffeeOrder o", CoffeeOrder.class).getResultList();
	        em.close();
	        return orders;
	    }

	    public void updateOrder(CoffeeOrder order) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            em.merge(order);
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

	    public void deleteOrder(int id) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();
	            CoffeeOrder order = em.find(CoffeeOrder.class, id);
	            if (order != null) {
	                em.remove(order);
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


