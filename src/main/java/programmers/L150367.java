package programmers;

import java.util.ArrayList;
import java.util.List;

public class L150367 {

// number 를 2진수로 표현
// 포화 이진 트리는 노드의 수가 항상 홀수
// 자릿수가 홀수가 아니면 return 0
// mid가 root node
// root는 더미일수 없음 더미면 return 0
// root를 기준으로 왼쪽은 왼쪽 서브트리/오른쪽은 오른쪽 서브트리
// 왼쪽 서브트리에 대해 가능한지 판단
// 오른쪽 서브트리에 대해 가능한지 판단
// 가능여부: 자릿수가 홀수인가
// left -> this -> right
// inorder()

    static class Solution {
        public int[] solution(long[] numbers) {
            List<Integer> answer = new ArrayList<>();
            for (long number : numbers) {
                String binaryStr = padd(toBinary(number));
                // System.out.println(number + " => " + binaryStr);
                answer.add(isFullBinaryTree(binaryStr, 0) ? 1 : 0);
            }
            return answer.stream()
                .mapToInt(i -> i)
                .toArray();
        }

        private static String toBinary(long number) {
            StringBuilder binary = new StringBuilder();
            int divisor = 2;
            while (number > 0) {
                binary.append(number%divisor);
                number /= divisor;
            }
            return binary.reverse()
                .toString();
        }

        // 포화 이진트리의 노드 개수는 정해져있다
        // 1 3 7 15 31 63 ...
        // 루트 노드의 수 * 2 만큼 늘어난다
        // 루트 노드의 수 = pow(2, input.length - 1)
        private static String padd(String input) {
            if (isFull(input.length())) return input;
            return String.format("%0" + paddSize(input) + "d", 0) + input;
        }

        // 포화 이진트리가 맞는지 판단
        // 1을 더한 값이 2의 제곱이어야한다
        private static boolean isFull(int len) {
            len += 1;
            while (len > 1) {
                if (len % 2 == 1) return false;
                len /= 2;
            }
            return true;
        }

        // 필요한 0의 개수 = pow(2, input.length) - input.length
        private static int paddSize(String input) {
            return minimumFullSize(input.length()) - input.length();
        }

        private static int minimumFullSize(int len) {
            int size = 1;
            int leaf = 2;
            while (size < len) {
                size += leaf;
                leaf *= 2;
            }
            return size;
        }

        private static boolean isFullBinaryTree(String binary, int level) {
            // System.out.println(String.format("%s in %d level", binary, level));
            if (binary.length() == 1) return true;
            if (level > 0 && binary.length() % 2 == 0) return false;
            int root = binary.length() / 2;
            if (binary.charAt(root) == '0') return !binary.contains("1");
            return isFullBinaryTree(binary.substring(0, root), level+1) && isFullBinaryTree(binary.substring(root+1), level+1);
        }
    }
}
