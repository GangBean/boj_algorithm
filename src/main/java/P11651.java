import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P11651 {

    /**
    * [문제]
    * 2차원 평면 위의 점 N개가 주어진다.
    *  좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
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

        List<Point> ret = new ArrayList<>();
        while (N-- > 0) {
            int[] ints = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            ret.add(new Point(ints[0], ints[1]));
        }

        ret.stream()
            .sorted()
            .forEach(System.out::println);
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return (this.y == o.y) ? Integer.compare(this.x, o.x) : Integer.compare(this.y, o.y);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}