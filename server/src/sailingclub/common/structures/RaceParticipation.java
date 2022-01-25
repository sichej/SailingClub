package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;
import sailingclub.common.Insertable;

public class RaceParticipation implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idRace;
	private String idMember;
	
	public RaceParticipation(Integer idRace, String idMember) {
		this.idRace = idRace;
		this.idMember = idMember;
	}

	public Integer getIdRace() { return idRace; }
	public String getIdMember() { return idMember; }

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
}
