package thrifty.api.model;

import javax.persistence.*;

@MappedSuperclass
public class PersistedEntity implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Version
    private Integer version;

	public Integer id() {
		return id;
	}

    public Integer version() {
        return version;
    }
}
