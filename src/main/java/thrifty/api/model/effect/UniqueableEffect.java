package thrifty.api.model.effect;

import thrifty.api.model.PersistedEntity;

import javax.persistence.MappedSuperclass;

public abstract class UniqueableEffect extends PersistedEntity implements Effect {
	
	private boolean unique;

	public boolean isUnique() {
		return unique;
	}

    public void unique(boolean unique) {
        this.unique = unique;
    }

}
