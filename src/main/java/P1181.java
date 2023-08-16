import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1181 {

    /**
    * [문제]
    * 알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
    * 단, 중복된 단어는 하나만 남기고 제거해야 한다.
    * 

    * 
    * [입력]
    * 첫째 줄에 단어의 개수 N이 주어진다.
    *  (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다.
    *  주어지는 문자열의 길이는 50을 넘지 않는다.
    * 

    * 
    * [출력]
    * 조건에 따라 정렬하여 단어들을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        Set<String> set = new HashSet<>();
        while (N-- > 0) {
            set.add(reader.readLine());
        }

        List<String> ret = new ArrayList<>(set);
        Collections.sort(ret, (a,b) -> {
            if (a.length() > b.length()) {
                return 1;
            }
            if (a.length() < b.length()) {
                return -1;
            }
            return a.compareTo(b);
        });

        StringJoiner joiner = new StringJoiner("\n");
        ret.stream().forEach(joiner::add);
        System.out.println(joiner);
    }
}