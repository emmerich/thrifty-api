package thrifty.api.model;

public class GoldValue {
	
	private int value;

    public GoldValue(int value) {
        this.value = value;
    }

	public int value() {
		return value;
	}

	public void value(int value) {
		this.value = value;
	}
	

}
