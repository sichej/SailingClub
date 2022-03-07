package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * Is an implementation of a race, wich is a competition
 * available for the members of the club
 * @see RaceParticipation
 */
public class Race implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the race id*/
	private Integer id;
	/**the race date*/
	private LocalDate date;
	/**the race price*/
	private double price;
	/**the race name*/
	private String name;
	
	/**
	 * Race constructor
	 * 
	 * @param date  day of the race
	 * @param price  price of the race
	 * @param name  name of the race
	 */
	public Race(LocalDate date, double price, String name) {
		this.date = date;
		this.price = price;
		this.name = name;
	}
	
	/**
	 * Race constructor
	 * 
	 * @param id  race id
	 * @param date  day of the race
	 * @param price  price of the race
	 * @param name  name of the race
	 */
	public Race(int id, LocalDate date, double price, String name) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.name = name;
	}
	
	/**
	 * Race constructor
	 * 
	 * @param id  race id
	 */
	public Race(int id) {
		this.id = id;
	}
	
	/**
	 * returns the race id
	 * @return the race id
	 */
	public Integer getId() { 
		return id; 
	}
	
	/**
	 * returns the race date
	 * @return the race date
	 */
	public LocalDate getDate() { 
		return date; 
	}
	
	/**
	 * returns the race price
	 * @return the race price
	 */
	public double getPrice() { 
		return price; 
	}
	
	/**
	 * returns the race name
	 * @return the race name
	 */
	public String getName() { 
		return name; 
	}

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"date", "price", "name"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "race";
	}

	/**
	 * Get String[] with value of the class elements
	 * @return String value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{ "'" + this.date.toString() + "'", Double.toString(this.price), "'" + this.name + "'"};
	}
	
	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "id";
	}

	/**
	 * Get the Primary Key value
	 * @return String  Primary Key value
	 */
	@Override
	public String getPkValue() {
		return Integer.toString(this.getId());
	}
}
