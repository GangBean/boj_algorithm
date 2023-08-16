import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P11650 {

    /**
    * [문제]
    * 2차원 평면 위의 점 N개가 주어진다.
    *  좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
    *  둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다.
    *  (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
    * 

    * 
    * [출력]
    * 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        List<Node> ret = new ArrayList<>();
        while (N-- > 0) {
            ret.add(new Node(Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray()));
        }
        ret.stream()
                .sorted()
                .forEach(System.out::println);
    }

    static class Node implements Comparable {
        int x;
        int y;

        public Node(int[] num) {
            this.x = num[0];
            this.y = num[1];
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        @Override
        public int compareTo(Object o) {
            Node a = this;
            Node b = (Node)o;

            if (a.x > b.x) {
                return 1;
            }
            if (a.x < b.x) {
                return -1;
            }
            return Integer.compare(a.y, b.y);
        }
    }
}