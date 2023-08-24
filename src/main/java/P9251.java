import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String S = reader.readLine();
        String L = reader.readLine();
        if (S.length() > L.length()) {
            String tmp = S;
            S = L;
            L = tmp;
        }

        int ret = 0;
        StringBuilder candidate = new StringBuilder();
        Map<Character, Queue<Integer>> occurrence = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < L.length(); j++) {
                if (S.charAt(i) == L.charAt(j)) {
                    Queue<Integer> idxQueue = occurrence.getOrDefault(S.charAt(i), new LinkedList<>());
                    idxQueue.offer(j);
                    occurrence.put(S.charAt(i), idxQueue);
                }
            }
        }

        for (int i = 0; i < S.length(); i++) {
            HashMap<Character, Queue<Integer>> copy = new HashMap<>(occurrence);
            int last = -1;
            for (int j = i; j < S.length(); j++) {
                char c = S.charAt(j);
                if (!occurrence.containsKey(c)) continue;
                Queue<Integer> queue = copy.get(c);
                int current = -1;
                while (!queue.isEmpty() && current <= last) {
                    current = queue.poll();
                }
                if (current <= last) {
                    copy = new HashMap<>(occurrence);
                    ret = Math.max(ret, candidate.length());
                    candidate = new StringBuilder();
                    last = -1;
                    continue;
                }
                candidate.append(c);
                last = current;
            }
            ret = Math.max(ret, candidate.length());
            candidate = new StringBuilder();
        }

        System.out.println(ret);
    }
}