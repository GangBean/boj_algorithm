import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class P15654 {

    /**
    * [문제]
    * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    *  N개의 자연수는 모두 다른 수이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 N과 M이 주어진다.
    *  (1 ≤ M ≤ N ≤ 8)둘째 줄에 N개의 수가 주어진다.
    *  입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
    * 

    * 
    * [출력]
    * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
    *  중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
    * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] i = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = i[1];
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        for (int idx = 0; idx < arr.length; idx++) {
            printPermutation(arr, idx, idx, M, new ArrayList<>());
        }
    }

    private static void printPermutation(int[] arr, int s, int i, int m, ArrayList<Integer> nums) {
        if (nums.size() == m) {
            StringJoiner joiner = new StringJoiner(" ");
            nums.stream().map(String::valueOf).forEach(joiner::add);
            System.out.println(joiner);
            return;
        }
        int idx = i;
        while (idx != s) {
            nums.add(arr[idx]);
            printPermutation(arr, s, idx, m, nums);
            nums.remove(nums.size() - 1);
            printPermutation(arr, s, idx, m, nums);
            idx = (idx + 1) % arr.length;
        }
    }
}