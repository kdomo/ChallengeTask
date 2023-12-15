package no1;

import java.util.List;


public class Bread {
	private String breadType;
	private List<Recipe> recipe;

	private Bread() {
	}

	private Bread(String breadType, List<Recipe> recipe) {
		this.breadType = breadType;
		this.recipe = recipe;
	}

	public static Bread of(String breadType, List<Recipe> recipe) {
		return new Bread(breadType, recipe);
	}

	public String getBreadType() {
		return breadType;
	}

	public List<Recipe> getRecipe() {
		return recipe;
	}
}
