package coffee_dao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SignUpController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private Button signUpButton;

	@FXML
	private Button cancelButton;

	private AccountDAO accountDAO;

	public SignUpController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PCof");
		accountDAO = new AccountDAO(emf);
	}

	@FXML
	public void handleSignUp() {
		String username = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		String confirmPassword = confirmPasswordField.getText().trim();

		if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
			showAlert("Error", "Please fill in all fields.");
			return;
		}

		if (!password.equals(confirmPassword)) {
			showAlert("Error", "Passwords do not match.");
			return;
		}

		Account newAccount = new Account();
		newAccount.setUsername(username);
		newAccount.setPassword(password);

		accountDAO.createAccount(newAccount);

		showAlert("Success", "Account created successfully!");

	}

	@FXML
	public void handleCancel() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_LogIn.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setTitle("Next Page");
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
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