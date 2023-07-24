import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class P1978 {

    /**
    * [문제]
    * 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫 줄에 수의 개수 N이 주어진다.
    *  N은 100이하이다.
    *  다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
    * 

    * 
    * [출력]
    * 주어진 수들 중 소수의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cnt = N;
        for (int n : nums) {
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    cnt--;
                    break;
                }
            }
            if (n == 1) {
                cnt--;
            }
        }
        System.out.println(cnt);
    }
}