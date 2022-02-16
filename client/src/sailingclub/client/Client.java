package sailingclub.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import sailingclub.client.gui.controllers.LoginGuiController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.fxml.*;

/**
 * The main class allows to launch the gui
 * also checks if the connection to the server is available
 * initialize the sockets and the streams
 * 
 * @author Andrea Bertogalli and Edoardo Sichelli
 */
public class Client extends Application {
	
	@Override
	/**
	 * the javafx start method
	 * @param primaryStage is the main window
	 */
	public void start(Stage primaryStage) {
		try {
			Socket socket = null;
			String ip = "localhost";
			int port = 5555;
			
			if(getParameters().getRaw().size() == 2) {  //set the port from cmd arg
				ip = getParameters().getNamed().get("ip");
				port = Integer.parseInt(getParameters().getNamed().get("port"));
			}
			
			System.out.println(ip + " : " + port);
			
			do {  //srv connection checking / retry
				try {
					System.out.print("\nChecking connection to the server...!");
					socket = new Socket(ip, port);
				}catch(Exception e) {
					ButtonType btn = new ButtonType("Retry");
					Alert alert = new Alert(AlertType.ERROR,"Check your connection to (" + ip + ":" + port +") and retry if the problem persist, "
							    + "probably the server is down!\nUse custom ip and port caling the program with --ip=yourip --port=yourport", btn, ButtonType.CANCEL);
					alert.setHeaderText("Unable to reach the server");
					alert.setTitle("Connection error");
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == btn) {
					    System.out.println("\nRetrying...");
					}else {
						System.out.print("Exit");
						System.exit(0);
					}
				}
			}while(socket == null);
			
			System.out.println("Connected!");
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/fxml/LoginGui.fxml"));
			Parent gui = loader.load();
			LoginGuiController controller = loader.getController();
			RequestController requestController = new RequestController(out,in);
			controller.setRequestController(requestController);
			Scene scene = new Scene(gui);
			scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
			primaryStage.getIcons().add(new Image("sailingclub/client/gui/images/appico.png"));

			final Socket fSock = socket;
			primaryStage.setOnCloseRequest(event -> { //stage close handler
				try {
					requestController.closeConnection();
					fSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * the main method
	 * @param args cmd args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
