package sailingclub.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import sailingclub.client.gui.controllers.LoginGuiController;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.structures.EmptyPayload;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.fxml.*;

/**
 * only contains the client main
 * 
 * @author Andrea Bertogalli and Edoardo Sichelli
 */
public class Client extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Socket socket = null;
			Scanner scan = new Scanner(System.in);
			do {
				try {
					System.out.print("\nChecking connection to the server...!");
					socket = new Socket("localhost", 12345);
				}catch(Exception e) {
					ButtonType btn = new ButtonType("Retry");
					Alert alert = new Alert(AlertType.ERROR,"Check your connection and retry if the problem persist, probably the server is down!", btn, ButtonType.CANCEL);
					alert.setHeaderText("Unable to reach the server");
					alert.setTitle("Connection error");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == btn) {
					    System.out.println("\nRetrying...");
					}else {
						System.out.print("Exit");
						System.exit(0);
					}
				}
			}while(socket == null);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/fxml/LoginGui.fxml"));
			Parent gui = loader.load();
			LoginGuiController controller = loader.getController();
			controller.setStreams(out, in);
			Scene scene = new Scene(gui);
			scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
			primaryStage.getIcons().add(new Image("sailingclub/client/gui/images/appico.png"));

			final Socket fSock = socket;
			primaryStage.setOnCloseRequest(event -> {
				try {
					out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
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
	 * it launch the app
	 * 
	 * @param args cmd args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
