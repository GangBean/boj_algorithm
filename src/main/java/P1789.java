import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1789 {

    /**
    * [문제]
    * 서로 다른 N개의 자연수의 합이 S라고 한다.
    *  S를 알 때, 자연수 N의 최댓값은 얼마일까?

    * 
    * [입력]
    * 첫째 줄에 자연수 S(1 ≤ S ≤ 4,294,967,295)가 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 자연수 N의 최댓값을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(reader.readLine());
        long n = (long) (Math.sqrt(2 * S)) - 1;
        while (true) {
            long num = n * (n + 1);
            if (num > 2 * S) break;
            n++;
        }
        System.out.print(n - 1);
    }
}