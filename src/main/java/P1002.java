import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1002 {

    /**
    * [문제]
    * 조규현과 백승환은 터렛에 근무하는 직원이다.
    *  하지만 워낙 존재감이 없어서 인구수는 차지하지 않는다.
    *  다음은 조규현과 백승환의 사진이다.
    * 이석원은 조규현과 백승환에게 상대편 마린(류재명)의 위치를 계산하라는 명령을 내렸다.
    *  조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.
    * 조규현의 좌표 $(x_1, y_1)$와 백승환의 좌표 $(x_2, y_2)$가 주어지고, 조규현이 계산한 류재명과의 거리 $r_1$과 백승환이 계산한 류재명과의 거리 $r_2$가 주어졌을 때, 류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 테스트 케이스의 개수 $T$가 주어진다.
    *  각 테스트 케이스는 다음과 같이 이루어져 있다.
    * 한 줄에 공백으로 구분 된 여섯 정수 $x_1$, $y_1$, $r_1$, $x_2$, $y_2$, $r_2$가 주어진다.
    * 

    * 
    * [출력]
    * 각 테스트 케이스마다 류재명이 있을 수 있는 위치의 수를 출력한다.
    *  만약 류재명이 있을 수 있는 위치의 개수가 무한대일 경우에는 $-1$ 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int T = Integer.parseInt(s);
        while (T-- > 0) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
            int x1 = inputs[0];
            int y1 = inputs[1];
            int r1 = inputs[2];
            int x2 = inputs[3];
            int y2 = inputs[4];
            int r2 = inputs[5];
            if (x1 == x2 && y1 == y2) {
                if (r1 == r2) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(0);
                continue;
            }

            int squareDistance = (int) (Math.pow(x1 - x2, 2)) + (int)(Math.pow(y1 - y2, 2));
            int squareDifference = (int) Math.pow(r1 - r2, 2);
            int squareSum = (int) Math.pow(r1 + r2, 2);

            if (squareDistance == squareDifference) {
                System.out.println(1);
                continue;
            }

            if (squareDistance < squareDifference) {
                System.out.println(0);
                continue;
            }

            if (squareDistance == squareSum) {
                System.out.println(1);
                continue;
            }

            if (squareDistance > squareSum) {
                System.out.println(0);
                continue;
            }

            System.out.println(2);
        }
    }
}