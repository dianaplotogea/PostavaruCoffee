package coffee_dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CoffeeOrder database table.
 * 
 */
@Entity
@NamedQuery(name="CoffeeOrder.findAll", query="SELECT c FROM CoffeeOrder c")
public class CoffeeOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String deliveryAddress;

	private String drink;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public CoffeeOrder() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDrink() {
		return this.drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}