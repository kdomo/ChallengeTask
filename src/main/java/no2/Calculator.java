package no2;

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
