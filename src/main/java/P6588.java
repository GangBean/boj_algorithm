import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P6588 {

    public static final int MAX_NUM = 1_000_000;
    static boolean[] isPrime = new boolean[MAX_NUM + 1];
    static List<Integer> prime = new ArrayList<>();

    /**
    * [문제]
    * 1742년, 독일의 아마추어 수학가 크리스티안 골드바흐는 레온하르트 오일러에게 다음과 같은 추측을 제안하는 편지를 보냈다.
    * 예를 들어 8은 3 + 5로 나타낼 수 있고, 3과 5는 모두 홀수인 소수이다.
    *  또, 20 = 3 + 17 = 7 + 13, 42 = 5 + 37 = 11 + 31 = 13 + 29 = 19 + 23 이다.
    * 이 추측은 아직도 해결되지 않은 문제이다.
    * 백만 이하의 모든 짝수에 대해서, 이 추측을 검증하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다.
    *  테스트 케이스의 개수는 100,000개를 넘지 않는다.
    * 각 테스트 케이스는 짝수 정수 n 하나로 이루어져 있다.
    *  (6 ≤ n ≤ 1000000)입력의 마지막 줄에는 0이 하나 주어진다.
    * 

    * 
    * [출력]
    * 각 테스트 케이스에 대해서, n = a + b 형태로 출력한다.
    *  이때, a와 b는 홀수 소수이다.
    *  숫자와 연산자는 공백 하나로 구분되어져 있다.
    *  만약, n을 만들 수 있는 방법이 여러 가지라면, b-a가 가장 큰 것을 출력한다.
    *  또, 두 홀수 소수의 합으로 n을 나타낼 수 없는 경우에는 "Goldbach's conjecture is wrong.
    * "을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i <= MAX_NUM; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i <= MAX_NUM; j++) {
                    isPrime[j * i] = false;
                }
            }
        }
        for (int i = 1; i <= MAX_NUM; i++) {
            if (isPrime[i]) prime.add(i);
        }
        while (true) {
            int N = Integer.parseInt(reader.readLine());
            if (N == 0) break;
            System.out.println(toPrimeSum(N));
        }
    }

    private static String toPrimeSum(int n) {
        int idx = 0;
        while (idx < prime.size()) {
            if (isPrime[prime.get(idx)] && isPrime[n - prime.get(idx)]) {
                break;
            }
            idx++;
        }
        return n + " = " + prime.get(idx) + " + " + (n - prime.get(idx));
    }
}