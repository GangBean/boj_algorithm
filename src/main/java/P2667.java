import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P2667 {

    /**
    * [문제]
    * <그림 1>과 같이 정사각형 모양의 지도가 있다.
    *  1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
    *  철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
    *  여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
    *  대각선상에 집이 있는 경우는 연결된 것이 아니다.
    *  <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
    *  지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
    * 

    * 
    * [출력]
    * 첫 번째 줄에는 총 단지수를 출력하시오.
    *  그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
    * 

    */
    static final int[][] DIR = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = reader.readLine().toCharArray();
        }

        char color = 'a';
        List<Integer> ret = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] == '1') {
                    ret.add(coloringSize(map, x, y, color));
                    color += 1;
                }
            }
        }
        System.out.println(ret.size());
        ret.stream().sorted()
                .forEach(System.out::println);
    }

    static int coloringSize(char[][] map, int x, int y, char color) {
        int size = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        map[x][y] = color;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            size++;
            for (int dir = 0; dir < DIR.length; dir++) {
                int nx = current[0] + DIR[dir][0];
                int ny = current[1] + DIR[dir][1];
                if (0 <= nx && nx < map.length && 0 <= ny && ny < map.length && map[nx][ny] == '1') {
                    q.offer(new int[]{nx, ny});
                    map[nx][ny] = color;
                }
            }
        }
        return size;
    }
}