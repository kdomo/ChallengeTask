package no1;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		JSONArray jsonArray = getJsonArray();
		List<Bread> breads = parsing(jsonArray);
		print(breads);
	}

	private static JSONArray getJsonArray() throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		Reader reader = new FileReader("src/main/resources/board.json");
		JSONArray jsonArray = (JSONArray)parser.parse(reader);
		return jsonArray;
	}

	private static List<Bread> parsing(JSONArray jsonArray) {
		List<Bread> breads = new ArrayList<>();
		for (Object obj : jsonArray) {
			JSONObject bread = (JSONObject) obj;
			String breadType = String.valueOf(bread.get("breadType"));

			List<Recipe> recipes = new ArrayList<>();
			JSONObject recipe = (JSONObject) bread.get("recipe");
			if (recipe != null) {
				for (Object ingredientKey : recipe.keySet()) {
					String ingredient = (String) ingredientKey;
					Long amount = (Long) recipe.get(ingredient);

					recipes.add(Recipe.of(ingredient, amount));
				}
			}

			breads.add(Bread.of(breadType, recipes));
		}
		return breads;
	}

	private static void print(List<Bread> breads) {
		for (Bread bread : breads) {
			System.out.println("breadType: " + bread.getBreadType());
			System.out.println("recipe");
			for (Recipe recipe : bread.getRecipe()) {
				System.out.println(recipe.getIngredient() + ": " + recipe.getAmount());
			}
			System.out.println();
		}
	}
}
