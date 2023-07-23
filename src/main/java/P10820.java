import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class P10820 {

    /**
    * [문제]
    * 문자열 N개가 주어진다.
    *  이때, 문자열에 포함되어 있는 소문자, 대문자, 숫자, 공백의 개수를 구하는 프로그램을 작성하시오.
    * 각 문자열은 알파벳 소문자, 대문자, 숫자, 공백으로만 이루어져 있다.
    * 

    * 
    * [입력]
    * 첫째 줄부터 N번째 줄까지 문자열이 주어진다.
    *  (1 ≤ N ≤ 100) 문자열의 길이는 100을 넘지 않는다.
    * 

    * 
    * [출력]
    * 첫째 줄부터 N번째 줄까지 각각의 문자열에 대해서 소문자, 대문자, 숫자, 공백의 개수를 공백으로 구분해 출력한다.
    * 

    */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        while (reader.hasNext()) {
            Map<Integer, Integer> map = new HashMap<>();
            String line = reader.nextLine();
            for (char c : line.toCharArray()) {
                if ('a' <= c && c <= 'z') {
                    map.put(0, map.getOrDefault(0, 0) + 1);
                    continue;
                }
                if ('A' <= c && c <= 'Z') {
                    map.put(1, map.getOrDefault(1, 0) + 1);
                    continue;
                }
                if ('0' <= c && c <= '9') {
                    map.put(2, map.getOrDefault(2, 0) + 1);
                    continue;
                }
                if (c == ' ') {
                    map.put(3, map.getOrDefault(3, 0) + 1);
                }
            }
            StringJoiner stringJoiner = new StringJoiner(" ");
            IntStream.range(0, 4)
                .mapToObj(map::get)
                .map(i -> (i == null)?0:i)
                .map(String::valueOf)
                .forEach(stringJoiner::add);
            System.out.println(stringJoiner);
        }
        reader.close();
    }
}