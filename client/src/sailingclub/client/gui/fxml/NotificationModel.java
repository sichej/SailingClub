package sailingclub.client.gui.fxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sailingclub.common.structures.Notification;

/**
 * is the class that rappresent the model
 * for a row of a table that contains notifications
 */
public class NotificationModel {
	private SimpleStringProperty text;
	private SimpleStringProperty dateTime;
	private Button btnAction;
	
	/**
	 * constructor
	 * @param n the notification
	 * @param btnAction the button that allow to do an action
	 */
	public NotificationModel(Notification n, Button btnAction) {
		this.text = new SimpleStringProperty(n.getText());
		this.dateTime = new SimpleStringProperty(n.getDateTime().toString().replace('T', ' '));
		this.btnAction = btnAction;
	}

	/**
	 * returns the notification text
	 * @return the notification text
	 */
	public String getText() {
		return text.get();
	}

	/**
	 * sets the notification text
	 * @param text the notification text
	 */
	public void setText(String text) {
		this.text.set(text);
	}

	/**
	 * returns the datetime of the notification
	 * @return the datetime of the notification
	 */
	public String getDateTime() {
		return dateTime.get();
	}

	/**
	 * sets the datetime of the notification
	 * @param dateTime the datetime of the notification
	 */
	public void setDateTime(String dateTime) {
		this.dateTime.set(dateTime);
	}

	/**
	 * returns the button related to the action
	 * @return the button related to the action
	 */
	public Button getBtnAction() {
		return btnAction;
	}

	/**
	 * returns the button related to the action
	 * @param btnAction the button related to the action
	 */
	public void setBtnAction(Button btnAction) {
		this.btnAction = btnAction;
	}
}
