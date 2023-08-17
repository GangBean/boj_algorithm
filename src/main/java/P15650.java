import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class P15650 {

    /**
    * [문제]
    * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 자연수 N과 M이 주어진다.
    *  (1 ≤ M ≤ N ≤ 8)

    * 
    * [출력]
    * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
    *  중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
    * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();

        ascendingCombination(new ArrayList<>(), nums[0], nums[1]);
    }

    private static void ascendingCombination(List<Integer> list, int num, int count) {
        if (list.size() == count) {
            StringJoiner joiner = new StringJoiner(" ");
            list.stream()
                .map(String::valueOf)
                .forEach(joiner::add);
            System.out.println(joiner);
            return;
        }

        for (int i = 1; i <= num; i++) {
            if (list.size() >= 1 && list.get(list.size() - 1) >= i) {
                continue;
            }
            list.add(i);
            ascendingCombination(list, num, count);
            list.remove(list.size() - 1);
        }
    }
}