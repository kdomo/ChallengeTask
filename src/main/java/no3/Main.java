package no3;

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
