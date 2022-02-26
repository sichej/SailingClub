package sailingclub.common;

/**
 * this interface allow any object that implements it to 
 * be parsed and Removed from the database.
 */
public interface Removable extends Insertable {
	/**
	 * returns the value of the primary key (id)
	 * @return the value of the primary key (id)
	 */
	public String getPkValue();
}
