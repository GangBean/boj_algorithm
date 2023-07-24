import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;

public class P1676 {

    static BigDecimal[] factorial = new BigDecimal[500 + 1];

    /**
    * [문제]
    * N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 N이 주어진다.
    *  (0 ≤ N ≤ 500)

    * 
    * [출력]
    * 첫째 줄에 구한 0의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Arrays.fill(factorial, BigDecimal.ZERO);
        System.out.println(countZero(factorial(N)));
    }

    private static int countZero(BigDecimal number) {
        int ret = 0;
        char[] reverse = new StringBuilder(String.valueOf(number)).reverse().toString().toCharArray();
        for (char c : reverse) {
            if (c != '0') {
                return ret;
            }
            ret++;
        }
        return ret;
    }

    private static BigDecimal factorial(int n) {
        if (n == 0 || n == 1) return new BigDecimal(1);
        if (factorial[n].compareTo(BigDecimal.ZERO) != 0) return factorial[n];

        factorial[n] = factorial(n-1).multiply(new BigDecimal(n));
        return factorial[n];
    }
}