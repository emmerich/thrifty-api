package thrifty.api.model.patch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentpatch")
public class PatchUpdate implements Comparable<PatchUpdate> {
	
	@Id
	private String patch;
	
	@Column(name = "timestamp")
	private long timestamp;

	@Override
	public int compareTo(PatchUpdate arg0) {
		// Compare the version strings
		return 0;
	}

}
