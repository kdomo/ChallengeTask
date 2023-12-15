## 문제1
```java
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
```
```java
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
```
```java
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
```
![문제1_출력결과](https://github.com/kdomo/ChallengeTask/blob/master/src/main/java/no1/%EC%B6%9C%EB%A0%A5%EA%B2%B0%EA%B3%BC.png?raw=true)

## 문제2
```java
public class Main {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		int result = calculator.add(4).add(5).subtract(3).out();
		System.out.println(result);
	}
}
```
```java
public class Calculator {
	private int value = 0;

	public Calculator() {
	}

	public Calculator add(int addValue) {
		value += addValue;
		return this;
	}

	public Calculator subtract(int subValue) {
		value -= subValue;
		return this;
	}

	public Calculator multiply(int mulValue) {
		value *= mulValue;
		return this;
	}

	public Calculator divide(int divValue) {
		value /= divValue;
		return this;
	}

	public int out() {
		return value;
	}
}
```
![문제2_출력결과](https://github.com/kdomo/ChallengeTask/blob/master/src/main/java/no2/%EC%B6%9C%EB%A0%A5%EA%B2%B0%EA%B3%BC.png?raw=true)

## 문제3
```java
public class Main {
	public static void main(String[] args) {
		int result = factorial(4);
		System.out.println(result);
	}

	private static int factorial(int i) {
		if (i == 0) {
			return 1;
		}
		return i * factorial(i - 1);
	}
}
```

## 문제4
```java
public class Main {
	public static void main(String[] args) {
		/** 재귀로 표현하면 간결하고 쉽게 작성할 수 있지만,
		 *  수가 커졌을 경우 스택의 복귀 주소가 계속해서 쌓이다가 스택 오버플로우가 발생한다.
		 *  메모제이션 방식을 사용하면, 숫자가 늘어나도 배열의 사이즈를 크게 바꿔주면 되므로
		 *  스택 오버플로우의 문제를 걱정할 필요가 없다.
		**/

		int n = 1000000;
		BigInteger[] fact = new BigInteger[n + 1];
		fact[0] = BigInteger.ONE; fact[1] = BigInteger.ONE;

		for (int i = 2; i <= n; i++) {
			fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
		}

		System.out.println(fact[1000000]);
	}
}
```

## 문제5
```java
public class Main {
	public static void main(String[] args) {
		/** 0은 땅, 1은 물
		 *  1. 좌, 우, 상, 하 모두가 자신보다 같거나 크면 1증가
		 *  ex) 좌, 우, 상, 하 모두 1이고, 중심의 값이 1이면 중심 값을 2로 변경
		 *  2.위 과정 반복하고 조건이 맞지 않는 경우 반복 종료
		 *  3.연못의 크기가 증가하더라도 대응 할 수 있어야 함
		**/

		int[][] pond = new int[][] {
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0},
				{0,0,0,1,1,1,0,0,0,0},
				{0,1,1,1,1,1,1,0,0,0},
				{0,1,1,1,1,1,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,0},
				{0,0,1,1,1,1,1,1,0,0},
				{0,0,0,1,1,1,1,0,0,0},
				{0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}
		};

		int[][] solution = solution(pond);
		for(int[] row : solution) {
			for(int i : row) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private static int[][] solution(int[][] pond) {
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};

		int row = pond.length;
		int col = pond[0].length;

		while (true) {
			boolean isChange = false;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (nx < 0 || ny < 0 || nx >= row || ny >= col) {
							break;
						}

						if (pond[nx][ny] != 0 && pond[nx][ny] >= pond[i][j]) {
							cnt++;
						}
					}

					if (cnt > 3) {
						pond[i][j] = pond[i][j] + 1;
						isChange = true;
					}
				}
			}


			if (!isChange) {
				break;
			}
		}

		return pond;
	}
}
```
![문제5_출력결과](https://github.com/kdomo/ChallengeTask/blob/master/src/main/java/no5/%EC%B6%9C%EB%A0%A5%EA%B2%B0%EA%B3%BC.png?raw=true)
