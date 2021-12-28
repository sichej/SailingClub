package sailingclub.common.structures;
import java.time.LocalDate;

import sailingclub.common.Translatable;

public class Race implements Translatable{
	private Integer id;
	private LocalDate data;
	private Double price;
	
	public Race(int id, LocalDate data, Double price) {
		this.id = id;
		this.data = data;
		this.price = price;
	}
	
	@Override
	public String[] getSQLAttributes() {
		return new String[]{"id", "data", "price"};
	}

	@Override
	public String getSQLTableName() {
		return "race";
	}

	@Override
	public String[] getSQLValues() {
		return new String[]{this.id.toString(), this.data.toString(), this.price.toString()};
	}

	@Override
	public Class<?>[] getSQLTypes() {
		return new Class[]{this.id.getClass(), this.data.getClass(), this.price.getClass()};
	}

	@Override
	public Object getSQLPrimaryKeyValue() {
		return this.id;
	}

	@Override
	public String getSQLPrimaryKeyName() {
		return "id";
	}
}
