package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;

public class Race implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate data;
	private double price;
	
	public Race(int id, LocalDate data, double price) {
		this.id = id;
		this.data = data;
		this.price = price;
	}

	public Integer getId() { return id; }
	public LocalDate getData() { return data; }
	public double getPrice() { return price; }

	@Override
	public String[] getAttributes() {
		return new String[]{"id", "data", "price"};
	}

	@Override
	public String getInstanceName() {
		return "race";
	}

	@Override
	public String[] getValues() {
		return new String[]{this.id.toString(), this.data.toString(), Double.toString(this.price)};
	}
}
