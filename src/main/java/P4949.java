import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;

public class P4949 {

    /**
    * [문제]
    * 세계는 균형이 잘 잡혀있어야 한다.
    *  양과 음, 빛과 어둠 그리고 왼쪽 괄호와 오른쪽 괄호처럼 말이다.
    * 정민이의 임무는 어떤 문자열이 주어졌을 때, 괄호들의 균형이 잘 맞춰져 있는지 판단하는 프로그램을 짜는 것이다.
    * 문자열에 포함되는 괄호는 소괄호("()") 와 대괄호("[]")로 2종류이고, 문자열이 균형을 이루는 조건은 아래와 같다.
    * 정민이를 도와 문자열이 주어졌을 때 균형잡힌 문자열인지 아닌지를 판단해보자.
    * 

    * 
    * [입력]
    * 각 문자열은 마지막 글자를 제외하고 영문 알파벳, 공백, 소괄호("( )"), 대괄호("[ ]")로 이루어져 있으며, 온점(".
    * ")으로 끝나고, 길이는 100글자보다 작거나 같다.
    * 

    * 
    * [출력]
    * 각 줄마다 해당 문자열이 균형을 이루고 있으면 "yes"를, 아니면 "no"를 출력한다.
    * 

    */
    static final String YES = "yes";
    static final String NO = "no";
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner ret = new StringJoiner("\n");
        while (true) {
            String str = reader.readLine();
            if (str.equals(".")) break;
            ret.add(resultOfPairChecking(str));
        }

        System.out.print(ret);
    }

    private static String resultOfPairChecking(String str) {
        Stack<Character> left = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '[' || c == '(') {
                left.push(c);
                continue;
            }
            if (c == ')') {
                if (left.isEmpty() || left.pop() != '(') return NO;
            }
            if (c == ']') {
                if (left.isEmpty() || left.pop() != '[') return NO;
            }
        }

        return (left.isEmpty()) ? YES : NO;
    }
}