import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P2108 {

    /**
    * [문제]
    * 수를 처리하는 것은 통계학에서 상당히 중요한 일이다.
    *  통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다.
    *  단, N은 홀수라고 가정하자.
    * N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
    *  단, N은 홀수이다.
    *  그 다음 N개의 줄에는 정수들이 주어진다.
    *  입력되는 정수의 절댓값은 4,000을 넘지 않는다.
    * 

    * 
    * [출력]
    * 첫째 줄에는 산술평균을 출력한다.
    *  소수점 이하 첫째 자리에서 반올림한 값을 출력한다.
    * 둘째 줄에는 중앙값을 출력한다.
    * 셋째 줄에는 최빈값을 출력한다.
    *  여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.
    * 넷째 줄에는 범위를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int maxCount = 0;
        List<Integer> nums = new ArrayList<>();
        long sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> frequent = new ArrayList<>();
        while (N-- > 0) {
            int num = Integer.parseInt(reader.readLine());
            int count = map.getOrDefault(num, 0) + 1;
            if (count == maxCount) {
                frequent.add(num);
            }
            if (count > maxCount) {
                maxCount = count;
                frequent = new ArrayList<>();
                frequent.add(num);
            }
            map.put(num, count);
            sum += num;
            nums.add(num);
        }
        Collections.sort(frequent);
        System.out.println(Math.round(sum / (double) nums.size()));
        Collections.sort(nums);
        System.out.println(nums.get(nums.size() / 2));
        if (frequent.size() > 1) {
            System.out.println(frequent.get(1));
        }
        if (frequent.size() == 1) {
            System.out.println(frequent.get(0));
        }
        System.out.println(nums.get(nums.size() - 1) - nums.get(0));
    }
}