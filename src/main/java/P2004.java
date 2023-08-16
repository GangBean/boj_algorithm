import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class P2004 {
    static BigDecimal[] factorial;

    /**
    * [문제]
    * $n \choose m$의 끝자리 $0$의 개수를 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 정수 $n$, $m$ ($0 \le m \le n \le 2,000,000,000$, $n \ne 0$)이 들어온다.
    * 

    * 
    * [출력]
    * 첫째 줄에 $n \choose m$의 끝자리 $0$의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] N = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        factorial = new BigDecimal[N[0] + 1];
        Arrays.fill(factorial, BigDecimal.ZERO);
        System.out.println(countZero(factorial(N[0])
                .divide(factorial(N[1]), 0, RoundingMode.DOWN)
                .divide(factorial(N[0]-N[1]), 0, RoundingMode.DOWN)));
    }

    private static int countZero(BigDecimal number) {
        int ret = 0;
        while (number.compareTo(new BigDecimal(5)) > 0) {
            number = number.divide(BigDecimal.TEN);
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