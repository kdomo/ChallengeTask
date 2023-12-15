package no5;

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
