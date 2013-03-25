package thrifty.api.model.patch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentpatch")
public class PatchNumber {
	
	@Id
	private String patch;

}
