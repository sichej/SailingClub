package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;

public class Race implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate date;
	private double price;
	
	public Race(LocalDate date, double price) {
		this.date = date;
		this.price = price;
	}

	public Integer getId() { return id; }
	public LocalDate getDate() { return date; }
	public double getPrice() { return price; }

	@Override
	public String[] getAttributes() {
		return new String[]{"date", "price"};
	}

	@Override
	public String getInstanceName() {
		return "race";
	}

	@Override
	public String[] getValues() {
		return new String[]{ "'" + this.date.toString() + "'", Double.toString(this.price)};
	}
	
	@Override
	public String getPk() {
		return "id";
	}
}
