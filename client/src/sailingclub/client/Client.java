package sailingclub.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sailingclub.client.gui.controllers.LoginGuiController;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.structures.EmptyPayload;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
			Socket socket = new Socket("localhost", 12345);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/fxml/LoginGui.fxml"));
			Parent gui = loader.load();
			LoginGuiController controller = loader.getController();
			controller.setStreams(out, in);
			Scene scene = new Scene(gui);
			scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
			// primaryStage.getIcons().add(new Image("eshop/gui/css/appico.png"));

			primaryStage.setOnCloseRequest(event -> {
				try {
					out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
					socket.close();
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
