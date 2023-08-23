import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10026 {

    /**
    * [문제]
    * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다.
    *  따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
    * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다.
    *  그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
    *  또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
    *  (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)예를 들어, 그림이 아래와 같은 경우에적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다.
    *  (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다.
    *  (빨강-초록 2, 파랑 1)그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 N이 주어진다.
    *  (1 ≤ N ≤ 100)둘째 줄부터 N개 줄에는 그림이 주어진다.
    * 

    * 
    * [출력]
    * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
    * 

    */
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(reader.readLine());

        boolean[][] isVisited = new boolean[s][s];
        char[][] picture = new char[s][s];

        for (int i = 0; i < s; i++) {
            picture[i] = reader.readLine().toCharArray();
        }

        int cnt = 0;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (isVisited[i][j]) continue;
                coloring(picture, isVisited, i, j, picture[i][j]);
                cnt++;
            }
        }

        isVisited = new boolean[s][s];
        int cnt2 = 0;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (isVisited[i][j]) continue;
                coloring2(picture, isVisited, i, j, picture[i][j]);
                cnt2++;
            }
        }

        System.out.println(cnt + " " + cnt2);
    }

    private static void coloring(char[][] picture, boolean[][] isVisited, int x, int y, char c) {
        for (int[] dir : DIR) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (0 <= nx && nx < picture.length
                && 0 <= ny && ny < picture[0].length
                && !isVisited[nx][ny]
                && picture[nx][ny] == c) {
                isVisited[nx][ny] = true;
                coloring(picture, isVisited, nx, ny, c);
            }
        }
    }

    private static void coloring2(char[][] picture, boolean[][] isVisited, int x, int y, char c) {
        for (int[] dir : DIR) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (0 <= nx && nx < picture.length
                && 0 <= ny && ny < picture[0].length
                && !isVisited[nx][ny]
                && ((c == 'B' && picture[nx][ny] == 'B') || ((c == 'R' || c == 'G') && (picture[nx][ny] == 'R' || picture[nx][ny] == 'G')))
            ) {
                isVisited[nx][ny] = true;
                coloring2(picture, isVisited, nx, ny, c);
            }
        }
    }
}