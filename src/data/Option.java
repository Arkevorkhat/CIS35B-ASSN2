package data;

import java.io.Serializable;

public class Option implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private float cost;

	protected float getCost() { // getter
		return cost;
	}

	protected void setCost(float cost) { // setter
		this.cost = cost;
	}

	public Option() {
		this.cost = 0f;
	}

	public Option(float cost) {
		this.cost = cost;
	}

	public Option(String name, float cost) {
		this.title = name;
		this.cost = cost;
	}

	protected String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}
}
