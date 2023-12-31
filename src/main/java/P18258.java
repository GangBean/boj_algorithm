import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;

public class P18258 {

    /**
    * [문제]
    * 정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
    * 명령은 총 여섯 가지이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 2,000,000)이 주어진다.
    *  둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
    *  주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
    *  문제에 나와있지 않은 명령이 주어지는 경우는 없다.
    * 

    * 
    * [출력]
    * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Stack<Integer> head = new Stack<>();
        Stack<Integer> tail = new Stack<>();
        StringJoiner joiner = new StringJoiner("\n");
        while (N-- > 0) {
            String[] input = reader.readLine().split(" ");
            if (input[0].equals("push")) {
                tail.push(Integer.valueOf(input[1]));
                continue;
            }
            if (input[0].equals("pop")) {
                if (head.isEmpty()) {
                    while (!tail.isEmpty()) {
                        head.push(tail.pop());
                    }
                }
                joiner.add(String.valueOf(head.isEmpty()?-1:head.pop()));
                continue;
            }
            if (input[0].equals("size")) {
                joiner.add(String.valueOf(head.size() + tail.size()));
                continue;
            }
            if (input[0].equals("empty")) {
                joiner.add(String.valueOf((head.isEmpty() && tail.isEmpty()) ? 1 : 0));
                continue;
            }
            if (input[0].equals("front")) {
                if (head.isEmpty()) {
                    while (!tail.isEmpty()) {
                        head.push(tail.pop());
                    }
                }
                joiner.add(String.valueOf(head.isEmpty()?-1:head.peek()));
                continue;
            }
            if (input[0].equals("back")) {
                if (tail.isEmpty()) {
                    while (!head.isEmpty()) {
                        tail.push(head.pop());
                    }
                }
                joiner.add(String.valueOf(tail.isEmpty()?-1:tail.peek()));
            }
        }
        System.out.println(joiner);
    }
}