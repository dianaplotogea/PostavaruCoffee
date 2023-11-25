package coffee_dao;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderController {

	private AuthenticationService authService;
	private Account loggedInAccount;

	public void setAuthenticationService(AuthenticationService authService) {
		this.authService = authService;
	}

	public void setLoggedInAccount(Account loggedInAccount) {
		this.loggedInAccount = loggedInAccount;
	}

	@FXML
	private RadioButton coffeeRadioButton;

	@FXML
	private RadioButton coldBrewRadioButton;

	@FXML
	private RadioButton latteRadioButton;

	@FXML
	private RadioButton icedLatteRadioButton;

	@FXML
	private RadioButton icedTeaRadioButton;

	@FXML
	private RadioButton teaRadioButton;

	@FXML
	private RadioButton cappuccinoRadioButton;

	@FXML
	private RadioButton fredoRadioButton;

	@FXML
	private TextField tfDeliveryAddress;

	private OrderDAO orderDAO;

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderController() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PCof");
		orderDAO = new OrderDAO(emf);
	}

	@FXML
	public void handlePlaceOrder() {
		String drink = "";
		if (coffeeRadioButton.isSelected()) {
			drink = "Coffee";
		} else if (coldBrewRadioButton.isSelected()) {
			drink = "Coldbrew";
		} else if (icedTeaRadioButton.isSelected()) {
			drink = "IcedTea";
		} else if (latteRadioButton.isSelected()) {
			drink = "Latte";
		} else if (icedLatteRadioButton.isSelected()) {
			drink = "IcedLatte";
		} else if (fredoRadioButton.isSelected()) {
			drink = "Fredo";
		} else if (teaRadioButton.isSelected()) {
			drink = "Tea";
		} else if (cappuccinoRadioButton.isSelected()) {
			drink = "Cappuccino";
		} else {
			showAlert("Error", "Please select a drink.");
			return;
		}

		String deliveryAddress = tfDeliveryAddress.getText().trim();

		if (deliveryAddress.isEmpty()) {
			showAlert("Error", "Please enter a delivery address.");
			return;
		}

		CoffeeOrder order = new CoffeeOrder();
		order.setDrink(drink);
		order.setDeliveryAddress(deliveryAddress);
		order.setAccount(loggedInAccount);

		orderDAO.createOrder(order);

		showAlert("Order Placed", "Your order has been placed.");

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_Thanks.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			Stage stage = (Stage) coffeeRadioButton.getScene().getWindow();
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
