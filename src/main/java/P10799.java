import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P10799 {

    /**
    * [문제]
    * 여러 개의 쇠막대기를 레이저로 절단하려고 한다.
    *  효율적인 작업을 위해서 쇠막대기를 아래에서 위로 겹쳐 놓고, 레이저를 위에서 수직으로 발사하여 쇠막대기들을 자른다.
    *  쇠막대기와 레이저의 배치는 다음 조건을 만족한다.
    * 아래 그림은 위 조건을 만족하는 예를 보여준다.
    *  수평으로 그려진 굵은 실선은 쇠막대기이고, 점은 레이저의 위치, 수직으로 그려진 점선 화살표는 레이저의 발사 방향이다.
    * 이러한 레이저와 쇠막대기의 배치는 다음과 같이 괄호를 이용하여 왼쪽부터 순서대로 표현할 수 있다.
    * 위 예의 괄호 표현은 그림 위에 주어져 있다.
    * 쇠막대기는 레이저에 의해 몇 개의 조각으로 잘려지는데, 위 예에서 가장 위에 있는 두 개의 쇠막대기는 각각 3개와 2개의 조각으로 잘려지고, 이와 같은 방식으로 주어진 쇠막대기들은 총 17개의 조각으로 잘려진다.
    * 쇠막대기와 레이저의 배치를 나타내는 괄호 표현이 주어졌을 때, 잘려진 쇠막대기 조각의 총 개수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 한 줄에 쇠막대기와 레이저의 배치를 나타내는 괄호 표현이 공백없이 주어진다.
    *  괄호 문자의 개수는 최대 100,000이다.
    * 

    * 
    * [출력]
    * 잘려진 조각의 총 개수를 나타내는 정수를 한 줄에 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        Stack<Character> stack = new Stack<>();
        int ret = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ')') {
                stack.pop();
                if (line.charAt(i-1) == '(') {
                    ret += stack.size();
                    continue;
                }
                ret += 1;
                continue;
            }
            stack.push(c);
        }
        System.out.println(ret);
    }
}