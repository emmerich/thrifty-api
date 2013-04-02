package thrifty.api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import thrifty.api.model.effect.Effect;
import thrifty.api.model.effect.Statistic;
import thrifty.api.model.effect.UniqueableEffect;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "item")
public class Item extends PersistedEntity {

    @Transient
	private Set<Availability> availability;

    @Transient
	private Tier tier;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL)
	private Set<Statistic> statistics;

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

    public Item() {
        statistics = new HashSet<Statistic>();
        availability = new HashSet<Availability>();
        components = new ArrayList<Item>();
    }

    public void addAvailability(Availability availability) {
        this.availability.add(availability);
    }

    public void addStatistic(Set<Statistic> statistics) {
        this.statistics.addAll(statistics);
    }

	public Set<Availability> availability() {
		return availability;
	}

	public Tier tier() {
		return tier;
	}

	public void tier(Tier tier) {
		this.tier = tier;
	}

	public Set<Statistic> statistics() {
		return statistics;
	}

	public List<Item> components() {
		return components;
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
