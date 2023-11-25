package coffee_dao;

	
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

	public class Main extends Application{
			
			@Override
		    public void start(Stage primaryStage) throws Exception {
		       
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_LogIn.fxml"));
		        Parent root = loader.load();

		  
		        primaryStage.setTitle("Postavaru Coffee");
		        primaryStage.setScene(new Scene(root));
		        primaryStage.show();
		        
		        
		
		     }
			
		    


		    public static void main(String[] args) {
		        launch(args);
		    }
		         } 
	  



