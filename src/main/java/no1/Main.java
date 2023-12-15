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
	}

	private static JSONArray getJsonArray() throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		Reader reader = new FileReader("src/main/resources/board.json");
		JSONArray jsonArray = (JSONArray)parser.parse(reader);
		return jsonArray;
	}
}
