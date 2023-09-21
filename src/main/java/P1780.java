import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class P1780 {

    /**
    * [문제]
    * N×N크기의 행렬로 표현되는 종이가 있다.
    *  종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다.
    *  우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.
    * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 N(1 ≤ N ≤ 37, N은 3k 꼴)이 주어진다.
    *  다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] cards = new int[N][];
        for (int i = 0; i < N; i++) {
            cards[i] = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int[] counts = countOfCards(cards, 0, 0, N);
        StringJoiner stringJoiner = new StringJoiner("\n");
        Arrays.stream(counts)
            .mapToObj(String::valueOf)
            .forEach(stringJoiner::add);

        System.out.print(stringJoiner);
    }

    private static int[] countOfCards(int[][] cards, int x, int y, int n) {
        if (isSame(cards, x, y, n)) {
            if (cards[x][y] == -1) {
                return new int[]{1, 0, 0};
            }
            if (cards[x][y] == 0) {
                return new int[]{0, 1, 0};
            }
            if (cards[x][y] == 1) {
                return new int[]{0, 0, 1};
            }
        }

        int[] ret = new int[]{0, 0, 0};
        int len = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                add(ret, countOfCards(cards, x + i * len, y + j * len, len));
            }
        }

        return ret;
    }

    private static void add(int[] a, int[] b) {
        for (int i = 0; i < 3; i++) {
            a[i] += b[i];
        }
    }

    private static boolean isSame(int[][] cards, int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cards[x][y] != cards[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}