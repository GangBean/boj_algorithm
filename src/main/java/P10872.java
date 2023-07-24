import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10872 {

    static int[] factorial = new int[12 + 1];

    /**
    * [문제]
    * 0보다 크거나 같은 정수 N이 주어진다.
    *  이때, N!을 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 정수 N(0 ≤ N ≤ 12)이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 N!을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        System.out.println(factorial(N));
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        if (factorial[n] != 0) return factorial[n];

        return n * factorial(n-1);
    }
}