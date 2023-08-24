import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P18870 {

    /**
    * [문제]
    * 수직선 위에 N개의 좌표 X1, X2, .
    * .
    * .
    * , XN이 있다.
    *  이 좌표에 좌표 압축을 적용하려고 한다.
    * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
    * X1, X2, .
    * .
    * .
    * , XN에 좌표 압축을 적용한 결과 X'1, X'2, .
    * .
    * .
    * , X'N를 출력해보자.
    * 

    * 
    * [입력]
    * 첫째 줄에 N이 주어진다.
    * 둘째 줄에는 공백 한 칸으로 구분된 X1, X2, .
    * .
    * .
    * , XN이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 X'1, X'2, .
    * .
    * .
    * , X'N을 공백 한 칸으로 구분해서 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Map<Integer, List<Integer>> numToIndex = new HashMap<>();
        int[] s = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < N; i++) {
            List<Integer> list = numToIndex.getOrDefault(s[i], new ArrayList<>());
            list.add(i);
            numToIndex.put(s[i], list);
        }

        List<Integer> nums = new ArrayList<>(numToIndex.keySet());
        Collections.sort(nums);

        int value = 0;
        int[] ret = new int[N];

        for (int num : nums) {
            for (int idx : numToIndex.get(num)) {
                ret[idx] = value;
            }
            value++;
        }
        StringJoiner joiner = new StringJoiner(" ");
        Arrays.stream(ret).mapToObj(String::valueOf).forEach(joiner::add);
        System.out.println(joiner);
    }
}