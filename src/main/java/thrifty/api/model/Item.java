package thrifty.api.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import thrifty.api.model.effect.Effect;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Table(name = "item")
public class Item extends PersistedEntity {

    @Transient
	private Set<Availability> availability;

    @Transient
	private Tier tier;

    @Transient
	private Set<Effect> effects;

    @Transient
	private List<Item> components;

    @Transient
	private GoldValue cost;

    @Transient
	private GoldValue sellValue;

    @Transient
	private int itemCode;

    @Column
    private String name;

	public Set<Availability> availability() {
		return availability;
	}

	public void availability(Set<Availability> availability) {
		this.availability = availability;
	}

	public Tier tier() {
		return tier;
	}

	public void tier(Tier tier) {
		this.tier = tier;
	}

	public Set<Effect> effects() {
		return effects;
	}

	public void effects(Set<Effect> effects) {
		this.effects = effects;
	}

	public List<Item> components() {
		return components;
	}

	public void components(List<Item> components) {
		this.components = components;
	}

	public GoldValue cost() {
		return cost;
	}

	public void cost(GoldValue cost) {
		this.cost = cost;
	}

	public GoldValue sellValue() {
		return sellValue;
	}

	public void sellValue(GoldValue sellValue) {
		this.sellValue = sellValue;
	}

	public int itemCode() {
		return itemCode;
	}

	public void itemCode(int itemCode) {
		this.itemCode = itemCode;
	}

    @JsonProperty(value = "name")
    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }
}
