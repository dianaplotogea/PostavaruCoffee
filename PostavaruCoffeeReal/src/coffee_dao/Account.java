package coffee_dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;
	
	@OneToMany(mappedBy="account")
	private List<CoffeeOrder> coffeeOrders;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CoffeeOrder> getCoffeeOrders() {
		return this.coffeeOrders;
	}

	public void setCoffeeOrders(List<CoffeeOrder> coffeeOrders) {
		this.coffeeOrders = coffeeOrders;
	}

	public CoffeeOrder addCoffeeOrder(CoffeeOrder coffeeOrder) {
		getCoffeeOrders().add(coffeeOrder);
		coffeeOrder.setAccount(this);

		return coffeeOrder;
	}

	public CoffeeOrder removeCoffeeOrder(CoffeeOrder coffeeOrder) {
		getCoffeeOrders().remove(coffeeOrder);
		coffeeOrder.setAccount(null);

		return coffeeOrder;
	}

}