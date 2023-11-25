package coffee_dao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MainController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button b_login;
	@FXML
	private Button b_delete;
	@FXML
	private Button b_signup;
	@FXML
	private Button b_update;
	@FXML
	private Button b_move;
	@FXML
	private PasswordField pass_password;
	@FXML
	private PasswordField pass_password1;
	@FXML
	private PasswordField pass_password_up;
	@FXML
	private TextField text_username;
	@FXML
	private TextField text_username1;
	@FXML
	private TextField text_username_up;

	private AccountDAO accountDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	private Account loggedInAccount;
	private OrderDAO orderDAO;

	private AuthenticationService authService;

	public MainController() {

		EntityManagerFactory accountEmf = Persistence.createEntityManagerFactory("PCof");
		accountDAO = new AccountDAO(accountEmf);

		EntityManagerFactory orderEmf = Persistence.createEntityManagerFactory("PCof"); // Use the appropriate
																						// persistence unit name
		orderDAO = new OrderDAO(orderEmf);
	}

	@FXML
	public void handleLogin() {
		String username = text_username.getText().trim();
		String password = pass_password.getText().trim();

		System.out.println("Username from text field: " + username);
		System.out.println("Password from text field: " + password);

		AuthenticationService authService = new AuthenticationService(accountDAO);

		loggedInAccount = authService.authenticate(username, password);

		if (loggedInAccount != null) {
			openNextPage();
		} else {

			showAlert("Login Failed", "Invalid username or password.");
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void openNextPage() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_Order.fxml"));
			Parent root = loader.load();

			OrderController orderController = loader.getController();

			orderController.setOrderDAO(orderDAO);
			orderController.setLoggedInAccount(loggedInAccount);

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setTitle("Next Page");
			stage.setScene(scene);

			stage.show();

			Stage loginStage = (Stage) text_username.getScene().getWindow();
			loginStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSignUp() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_2.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setTitle("Next Page");
			stage.setScene(scene);

			stage.show();

			Stage loginStage = (Stage) text_username.getScene().getWindow();
			loginStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openUpdate() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_Update.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setTitle("Next Page");
			stage.setScene(scene);

			stage.show();

			Stage loginStage = (Stage) text_username.getScene().getWindow();
			loginStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount() {
		String username = text_username.getText().trim();
		String password = pass_password.getText().trim();

		System.out.println("Username from text field: " + username);
		System.out.println("Password from text field: " + password);

		AuthenticationService authService = new AuthenticationService(accountDAO);

		loggedInAccount = authService.authenticate(username, password);

		if (loggedInAccount != null) {

			accountDAO.deleteAccount(loggedInAccount.getId());
			showAlert("Account deleted", "Usename and password have been deleted");
		} else {

			showAlert("Login Failed", "Invalid username or password.");
		}

	}

	public void updateAccount() {
		String username = text_username1.getText().trim();
		String password = pass_password1.getText().trim();

		System.out.println("Username from text field: " + username);
		System.out.println("Password from text field: " + password);

		AuthenticationService authService = new AuthenticationService(accountDAO);

		loggedInAccount = authService.authenticate(username, password);

		if (loggedInAccount != null) {

			openNextPage();
			String username_up = text_username_up.getText().trim();
			String password_up = pass_password_up.getText().trim();

			System.out.println("Username from text field: " + username_up);
			System.out.println("Password from text field: " + password_up);
			loggedInAccount.setUsername(username_up);
			loggedInAccount.setPassword(password_up);

			accountDAO.updateAccount(loggedInAccount);
			showAlert("Account Updated", "Account information has been updated.");
		} else {

			showAlert("Update Failed", "Invalid username or password.");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
