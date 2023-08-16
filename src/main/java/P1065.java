import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1065 {

    /**
    * [문제]
    * 어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다.
    *  등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다.
    *  N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 1,000보다 작거나 같은 자연수 N이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);

        int ret = 0;
        for (int i = 1; i <= 9; i++) {
            if (i <= N) {
                ret++;
            }
            for (int j = 0; j <= 9; j++) {
                int num = (i * 10) + j;
                int difference = j - i;
                int next = j;
                while (0 <= next && next <= 9 && num <= N) {
                    // System.out.println("> " + num);
                    ret++;
                    next += difference;
                    num *= 10;
                    num += next;
                }
            }
        }

        System.out.println(ret);
    }
}