package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * This class represent a record of a participation to a race
 * of a boat owned by a user
 * @see Race
 * @see User
 * @see Boat
 */
public class RaceParticipation implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the id of the race*/
	private int idRace;
	/**the id of the member*/
	private String idMember;
	/**the id of the boat*/
	private Integer idBoat;
	
	/**
	 * RaceParticipation constructor
	 * @param idRace  race's id
	 * @param idMember  member's id
	 * @param idBoat  boat's id
	 */
	public RaceParticipation(int idRace, String idMember, int idBoat) {
		this.idRace = idRace;
		this.idMember = idMember;
		this.idBoat = idBoat;
	}
	
	/**
	 * RaceParticipation constructor
	 * @param idRace  race's id
	 * @param idMember  member's id
	 */
	public RaceParticipation(int idRace, String idMember) {
		this.idRace = idRace;
		this.idMember = idMember;
	}

	/**
	 * the race id
	 * @return the id of the race
	 */
	public int getId() { 
		return idRace; 
	}
	
	/**
	 * returns the member, who participate with the boat
	 * @return the member, who participate with the boat
	 */
	public String getMember() { 
		return idMember; 
	}
	
	/**
	 * returns the boat which participate id
	 * @return the boat which participate id
	 */
	public int getIdBoat() { 
		return idBoat; 
	}

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"id_race", "id_member", "id_boat"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "race_participation";
	}

	/**
	 * Get String[] with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{ Integer.toString(this.idRace),  "'" + this.idMember + "'", Integer.toString(this.idBoat)};
	}
	
	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "id_race";
	}

	/**
	 * Get the Primary Key value
	 * @return String  Primary Key value
	 */
	@Override
	public String getPkValue() {
		return this.idRace + " AND id_member = '" + this.idMember +"'";
	}
}
