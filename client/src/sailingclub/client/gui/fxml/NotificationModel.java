package sailingclub.client.gui.fxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sailingclub.common.structures.Notification;

public class NotificationModel {
	private SimpleStringProperty text;
	private SimpleStringProperty dateTime;
	private Button btnAction;
	
	public NotificationModel(Notification n, Button btnAction) {
		this.text = new SimpleStringProperty(n.getText());
		this.dateTime = new SimpleStringProperty(n.getDateTime().toString().replace('T', ' '));
		this.btnAction = btnAction;
	}

	public String getText() {
		return text.get();
	}

	public void setText(String text) {
		this.text.set(text);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime) {
		this.dateTime.set(dateTime);
	}

	public Button getBtnAction() {
		return btnAction;
	}

	public void setBtnAction(Button btnAction) {
		this.btnAction = btnAction;
	}
}
