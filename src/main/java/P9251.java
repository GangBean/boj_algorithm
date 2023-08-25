import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P9251 {

    /**
    * [문제]
    * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
    * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
    * 

    * 
    * [입력]
    * 첫째 줄과 둘째 줄에 두 문자열이 주어진다.
    *  문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
    * 

    * 
    * [출력]
    * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.
    * 

    */
    static char[] S;
    static char[] L;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        S = reader.readLine().toCharArray();
        L = reader.readLine().toCharArray();

        dp = new int[S.length][L.length];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        System.out.println(lcs(S.length-1, L.length-1));
    }

    public static int lcs(int s, int l) {
        if (s < 0 || l < 0) return 0;

        if (dp[s][l] == -1) {
            if (S[s] == L[l]) {
                dp[s][l] = lcs(s-1, l-1) + 1;
                return dp[s][l];
            }
            dp[s][l] = Math.max(lcs(s-1, l), lcs(s, l-1));
        }

        return dp[s][l];
    }
}