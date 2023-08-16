import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1193 {

    /**
    * [문제]
    * 무한히 큰 배열에 다음과 같이 분수들이 적혀있다.
    * 이와 같이 나열된 분수들을 1/1 → 1/2 → 2/1 → 3/1 → 2/2 → … 과 같은 지그재그 순서로 차례대로 1번, 2번, 3번, 4번, 5번, … 분수라고 하자.
    * X가 주어졌을 때, X번째 분수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 분수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int X = Integer.parseInt(s);
        int N = find(X);
        int remain = X - calc(N);
        if ((N + 1) % 2 == 1) {
            int a = N + 1;
            int b = 1;
            while (remain-- > 1) {
                a--;
                b++;
            }
            System.out.println(a + "/" + b);
            return;
        }

        int a = 1;
        int b = N + 1;
        while (remain-- > 1) {
            a++;
            b--;
        }
        System.out.println(a + "/" + b);
    }

    private static int find(int x) {
        int ret = 1;
        while (true) {
            int calc = calc(ret);
            if (calc >= x) {
                return ret - 1;
            }
            ret++;
        }
    }

    private static int calc(int n) {
        return n * (n + 1) / 2;
    }
}