package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

public class RaceParticipation implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idRace;
	private String idMember;
	
	public RaceParticipation(Integer idRace, String idMember) {
		this.idRace = idRace;
		this.idMember = idMember;
	}

	public Integer getId() { return idRace; }
	public String getName() { return idMember; }

	@Override
	public String[] getAttributes() {
		return new String[]{"id_race", "id_member"};
	}

	@Override
	public String getInstanceName() {
		return "race_participation";
	}

	@Override
	public String[] getValues() {
		return new String[]{ "'" + this.idRace.toString() + "'",  "'" + this.idMember + "'"};
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
