import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class P2609 {

    /**
    * [문제]
    * 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에는 두 개의 자연수가 주어진다.
    *  이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxCommonDivisor = 1;
        for (int i = Math.min(nums[0], nums[1]); i > 0; i--) {
            if (nums[0] % i == 0 && nums[1] % i == 0) {
                maxCommonDivisor = i;
                break;
            }
        }
        System.out.println(maxCommonDivisor);
        System.out.println(maxCommonDivisor * (nums[0] / maxCommonDivisor) * (nums[1] / maxCommonDivisor));
    }
}