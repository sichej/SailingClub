package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

public class RaceParticipation implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private int idRace;
	private String idMember;
	private Integer idBoat;
	
	public RaceParticipation(int idRace, String idMember, int idBoat) {
		this.idRace = idRace;
		this.idMember = idMember;
		this.idBoat = idBoat;
	}
	
	public RaceParticipation(int idRace, String idMember) {
		this.idRace = idRace;
		this.idMember = idMember;
	}

	public int getId() { return idRace; }
	public String getName() { return idMember; }
	public int getIdBoat() { return idBoat; }

	@Override
	public String[] getAttributes() {
		return new String[]{"id_race", "id_member", "id_boat"};
	}

	@Override
	public String getInstanceName() {
		return "race_participation";
	}

	@Override
	public String[] getValues() {
		return new String[]{ Integer.toString(this.idRace),  "'" + this.idMember + "'", Integer.toString(this.idBoat)};
	}
	
	@Override
	public String getPk() {
		return "id_race";
	}

	@Override
	public String getPkValue() {
		return this.idRace + " AND id_member = '" + this.idMember +"'";
	}
}
