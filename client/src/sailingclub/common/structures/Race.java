package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

public class Race implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate date;
	private double price;
	private String name;
	
	public Race(LocalDate date, double price, String name) {
		this.date = date;
		this.price = price;
		this.name = name;
	}
	
	public Race(int id, LocalDate date, double price, String name) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.name = name;
	}

	public Integer getId() { return id; }
	public LocalDate getDate() { return date; }
	public double getPrice() { return price; }
	public String getName() { return name; }

	@Override
	public String[] getAttributes() {
		return new String[]{"date", "price", "name"};
	}

	@Override
	public String getInstanceName() {
		return "race";
	}

	@Override
	public String[] getValues() {
		return new String[]{ "'" + this.date.toString() + "'", Double.toString(this.price), "'" + this.name + "'"};
	}
	
	@Override
	public String getPk() {
		return "id";
	}

	@Override
	public String getPkValue() {
		return Integer.toString(this.getId());
	}
}
