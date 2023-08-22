import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class P4948 {

    /**
    * [문제]
    * 베르트랑 공준은 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
    * 이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
    * 예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다.
    *  (11, 13, 17, 19) 또, 14보다 크고, 28보다 작거나 같은 소수는 3개가 있다.
    *  (17,19, 23)자연수 n이 주어졌을 때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
    *  각 케이스는 n을 포함하는 한 줄로 이루어져 있다.
    * 입력의 마지막에는 0이 주어진다.
    * 

    * 
    * [출력]
    * 각 테스트 케이스에 대해서, n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력한다.
    * 

    */
    static final int MAX_VALUE = 2 * 123_456;
    static boolean[] isPrime = new boolean[MAX_VALUE + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner stringJoiner = new StringJoiner("\n");
        Arrays.fill(isPrime, true);
        findPrimeNumbers();
        while(true) {
            int N = Integer.parseInt(reader.readLine());
            if (N == 0) break;
            stringJoiner.add(String.valueOf(countOfPrimeNumber(N)));
        }
        System.out.print(stringJoiner);
    }

    private static void findPrimeNumbers() {
        isPrime[1] = false;
        for (int i = 2; i <= MAX_VALUE; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= MAX_VALUE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    private static int countOfPrimeNumber(int n) {
        int ret = 0;
        int to = 2 * n;
        for (int i = n + 1; i <= to; i++) {
            if (isPrime[i]) ret++;
        }
        return ret;
    }
}