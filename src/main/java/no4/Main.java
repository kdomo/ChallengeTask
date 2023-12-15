package no4;

import java.math.BigInteger;

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
