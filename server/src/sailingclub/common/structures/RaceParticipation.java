package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * RaceParticipation class
 */
public class RaceParticipation implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private int idRace;
	private String idMember;
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

	public int getId() { return idRace; }
	public String getName() { return idMember; }
	public int getIdBoat() { return idBoat; }

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
	 * Get printable String with value of the class elements
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
