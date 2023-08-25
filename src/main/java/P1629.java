import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1629 {

    /**
    * [문제]
    * 자연수 A를 B번 곱한 수를 알고 싶다.
    *  단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다.
    *  A, B, C는 모두 2,147,483,647 이하의 자연수이다.
    * 

    * 
    * [출력]
    * 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int A = nums[0];
        int B = nums[1];
        int C = nums[2];

        System.out.println(pow(A, B, C));
    }

    private static long pow(long a, int b, int c) {
        if (b == 1) {
            return a % c;
        }

        long tmp = pow(a, b / 2, c);

        if (b % 2 == 1) {
            return (((tmp * tmp) % c) * a) % c;
        }
        return (tmp * tmp) % c;
    }
}