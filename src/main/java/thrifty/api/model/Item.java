package thrifty.api.model;

import java.util.Set;

import thrifty.api.model.effect.Effect;

public class Item extends PersistedEntity {
	
	private Set<Availability> availability;

	private Tier tier;
	private Set<Effect> effects;
	
	private Set<Item> components;
	
	private GoldValue cost;
	private GoldValue sellValue;
	
	private int itemCode;

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

	public Set<Item> components() {
		return components;
	}

	public void components(Set<Item> components) {
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
}
