package sailingclub.common;

/**
 * this interface allow any object that implements it to 
 * be parsed and inserted in the database.
 */
public interface Insertable{
	/**
	 * returns the attributes of the object
	 * @return the attributes of the object
	 */
	public String[] getAttributes();
	
	/**
	 * returns the name of the relation in the db
	 * @return the name of the relation in the db
	 */
	public String getInstanceName();
	
	/**
	 * returns the attributes values
	 * @return the attributes values
	 */
	public String[] getValues();
	
	/**
	 * returns the id of the object
	 * @return the id of the object
	 */
	public String getPk();
}
