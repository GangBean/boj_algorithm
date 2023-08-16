import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1003 {

    /**
    * [문제]
    * 다음 소스는 N번째 피보나치 수를 구하는 C++ 함수이다.
    * fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
    * 1은 2번 출력되고, 0은 1번 출력된다.
    *  N이 주어졌을 때, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
    * 각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다.
    *  N은 40보다 작거나 같은 자연수 또는 0이다.
    * 

    * 
    * [출력]
    * 각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int T = Integer.parseInt(s);
        int[][] count = new int[41][2];
        count[0] = new int[]{1, 0};
        count[1] = new int[]{0, 1};

        for (int i = 2; i <= 40; i++) {
            count[i] = new int[]{count[i-2][0] + count[i-1][0], count[i-2][1] + count[i-1][1]};
        }

        while (T-- > 0) {
            int N = Integer.parseInt(reader.readLine());
            System.out.println(count[N][0] + " " + count[N][1]);
        }
    }
}