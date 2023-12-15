package no1;

public class Recipe {
	private String ingredient;
	private Long amount;

	public Recipe() {
	}

	private Recipe(String ingredient, Long amount) {
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public static Recipe of(String ingredient, Long amount) {
		return new Recipe(ingredient, amount);
	}

	public String getIngredient() {
		return ingredient;
	}

	public Long getAmount() {
		return amount;
	}
}
