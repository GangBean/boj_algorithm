import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P10866 {

    /**
    * [문제]
    * 정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
    * 명령은 총 여덟 가지이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다.
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
        String s = reader.readLine();
        Stack<Integer> front = new Stack<>();
        Stack<Integer> back = new Stack<>();
        int N = Integer.parseInt(s);

        while (N-- > 0) {
            String[] input = reader.readLine().split(" ");
            if (input[0].equals("push_front")) {
                front.push(Integer.valueOf(input[1]));
                continue;
            }
            if (input[0].equals("push_back")) {
                back.push(Integer.valueOf(input[1]));
                continue;
            }
            if (input[0].equals("front")) {
                peekDeque(back, front);
                continue;
            }
            if (input[0].equals("back")) {
                peekDeque(front, back);
                continue;
            }
            if (input[0].equals("pop_front")) {
                popDeque(back, front);
                continue;
            }
            if (input[0].equals("pop_back")) {
                popDeque(front, back);
                continue;
            }
            if (input[0].equals("size")) {
                System.out.println(front.size() + back.size());
                continue;
            }
            if (input[0].equals("empty")) {
                System.out.println((front.size() + back.size()) == 0 ? 1 : 0);
            }
        }
    }

    private static void peekDeque(Stack<Integer> from, Stack<Integer> to) {
        if (to.isEmpty()) {
            if (from.isEmpty()) {
                System.out.println(-1);
                return;
            }
            while (!from.isEmpty()) {
                to.push(from.pop());
            }
        }
        System.out.println(to.peek());
    }

    private static void popDeque(Stack<Integer> from, Stack<Integer> to) {
        if (to.isEmpty()) {
            if (from.isEmpty()) {
                System.out.println(-1);
                return;
            }
            while (!from.isEmpty()) {
                to.push(from.pop());
            }
        }
        System.out.println(to.pop());
    }
}