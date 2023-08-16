import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class P2751 {

    /**
    * [문제]
    * N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
    *  둘째 줄부터 N개의 줄에는 수가 주어진다.
    *  이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다.
    *  수는 중복되지 않는다.
    * 

    * 
    * [출력]
    * 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        List<Integer> list = new ArrayList<>();
        while (N-- > 0) {
            list.add(Integer.parseInt(reader.readLine()));
        }
        StringJoiner ret = new StringJoiner("\n");
        list.stream()
                .sorted()
                .map(String::valueOf)
                .forEach(ret::add);
        System.out.println(ret);
    }
}