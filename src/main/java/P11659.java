import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class P11659 {

    /**
    * [문제]
    * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다.
    *  둘째 줄에는 N개의 수가 주어진다.
    *  수는 1,000보다 작거나 같은 자연수이다.
    *  셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
    * 

    * 
    * [출력]
    * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(reader.readLine().split(" ")[1]);
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            if (i > 1) {
                sums[i] += sums[i-1];
            }
            sums[i] += nums[i - 1];
        }
        StringJoiner joiner = new StringJoiner("\n");
        while (M-- > 0) {
            int[] fromTo = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            joiner.add(String.valueOf(sums[fromTo[1]] - sums[fromTo[0] - 1]));
        }
        System.out.println(joiner);
    }
}