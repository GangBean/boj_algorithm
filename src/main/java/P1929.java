import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class P1929 {

    /**
    * [문제]
    * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다.
    *  (1 ≤ M ≤ N ≤ 1,000,000) M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.
    * 

    * 
    * [출력]
    * 한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] isPrime = new boolean[nums[1] + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i <= nums[1]; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= nums[1]; j++) {
                    isPrime[j * i] = false;
                }
            }
        }
        for (int i = nums[0]; i <= nums[1]; i++) {
            if (isPrime[i]) {
                System.out.println(i);
            }
        }
    }
}