package programmers;

import java.util.Stack;

public class L60058 {
    static class Solution {
        public String solution(String p) {
            return perfectOf(p);
        }

        private String perfectOf(String p) {
            if (p.length() == 0) return p;
            String[] split = balancedPairOf(p);
            if (isPerfect(split[0])) {
                return split[0] + perfectOf(split[1]);
            }
            String ret = "(";
            ret += perfectOf(split[1]);
            ret += ")";
            ret += cutAndReversedOf(split[0]);
            return ret;
        }

        private static String[] balancedPairOf(String p) {
            Stack<Character> s = new Stack<>();
            char[] arr = p.toCharArray();
            int idx = 1;
            int left = (arr[0] == '(') ? 1 : 0;
            int right = (arr[0] == ')') ? 1: 0;
            while (idx < arr.length && (left != right)) {
                if (arr[idx] == ')') {
                    right++;
                    idx++;
                    continue;
                }
                left++;
                idx++;
            }
            if (idx == arr.length) return new String[]{p, ""};
            return new String[]{p.substring(0, idx), p.substring(idx)};
        }


        private static boolean isPerfect(String u) {
            if (u.length() == 0) return true;
            Stack<Character> s = new Stack<>();
            for (char c : u.toCharArray()) {
                if (c == '(') {
                    s.add(c);
                    continue;
                }
                if (s.isEmpty() || s.pop() != '(') return false;
            }
            return s.isEmpty();
        }

        private static String cutAndReversedOf(String u) {
            char[] arr = u.toCharArray();
            StringBuilder ret = new StringBuilder();
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] == ')') {
                    ret.append('(');
                    continue;
                }
                ret.append(')');
            }
            return ret.toString();
        }

    }
}
