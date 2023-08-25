import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1074 {

    /**
    * [문제]
    * 한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다.
    *  예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
    * N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
    * 다음 예는 22 × 22 크기의 배열을 방문한 순서이다.
    * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
    * 다음은 N=3일 때의 예이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 정수 N, r, c가 주어진다.
    * 

    * 
    * [출력]
    * r행 c열을 몇 번째로 방문했는지 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(count(nums[0], nums[1], nums[2], 0, 0));
    }

    private static int count(int N, int r, int c, int x, int y) {
        if (N == 0) {
            return 0;
        }
        int halfLength = (int) Math.pow(2, N-1);
        int partialCount = halfLength * halfLength;
        if (y <= r && r < y + halfLength) {
            if (x <= c && c < x + halfLength) {
                return count(N - 1, r, c, x, y);
            }
            return count(N - 1, r, c, x + halfLength, y) + partialCount;
        }
        if (x <= c && c < x + halfLength) {
            return count(N - 1, r, c, x, y + halfLength) + (2 * partialCount);
        }
        return count(N - 1, r, c, x + halfLength, y + halfLength) + (3 * partialCount);
    }
}