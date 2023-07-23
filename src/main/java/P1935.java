import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1935 {

    /**
    * [문제]
    * 후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 피연산자의 개수(1 ≤ N ≤ 26) 가 주어진다.
    *  그리고 둘째 줄에는 후위 표기식이 주어진다.
    *  (여기서 피연산자는 A~Z의 영대문자이며, A부터 순서대로 N개의 영대문자만이 사용되며, 길이는 100을 넘지 않는다) 그리고 셋째 줄부터 N+2번째 줄까지는 각 피연산자에 대응하는 값이 주어진다.
    *  3번째 줄에는 A에 해당하는 값, 4번째 줄에는 B에 해당하는값 , 5번째 줄에는 C .
    * .
    * .
    * 이 주어진다, 그리고 피연산자에 대응 하는 값은 100보다 작거나 같은 자연수이다.
    * 후위 표기식을 앞에서부터 계산했을 때, 식의 결과와 중간 결과가 -20억보다 크거나 같고, 20억보다 작거나 같은 입력만 주어진다.
    * 

    * 
    * [출력]
    * 계산 결과를 소숫점 둘째 자리까지 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        Stack<Double> stack = new Stack<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String calculation = reader.readLine();
        double[] nums = new double[26];
        int idx = 0;
        while (N-- > 0) {
            nums[idx++] = Double.parseDouble(reader.readLine());
        }
        for (char c : calculation.toCharArray()) {
            if (c == '+') {
                stack.push(stack.pop() + stack.pop());
                continue;
            }
            if (c == '-') {
                stack.push(- (stack.pop() - stack.pop()));
                continue;
            }
            if (c == '*') {
                stack.push(stack.pop() * stack.pop());
                continue;
            }
            if (c == '/') {
                stack.push(1 / stack.pop() * stack.pop());
                continue;
            }
            stack.push(nums[c - 'A']);
        }

        System.out.printf("%.2f", stack.pop());
    }
}