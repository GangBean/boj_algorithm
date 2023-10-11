package programmers;

import java.util.ArrayList;
import java.util.List;

public class L60057 {
    static class Solution {
        public int solution(String s) {
            int answer = s.length();
            for (int count = 1; count <= s.length(); count++) {
                answer = Math.min(answer, zip(arrSplitedBy(s, count)).length());
            }
            return answer;
        }

        private static List<String> arrSplitedBy(String s, int n) {
            List<String> ret = new ArrayList<>();
            int idx = 0;
            while (idx < s.length() && idx + n <= s.length()) {
                ret.add(s.substring(idx, idx + n));
                idx += n;
            }

            if (idx < s.length()) ret.add(s.substring(idx));
            return ret;
        }

        private static String zip(List<String> list) {
            String ret = "";
            if (list.size() == 0) return ret;
            String prev = list.get(0);
            int count = 1;
            for (int idx = 1; idx < list.size(); idx++) {
                if (!list.get(idx).equals(prev)) {
                    if (count > 1) ret += String.valueOf(count);
                    ret += prev;

                    prev = list.get(idx);
                    count = 1;
                    continue;
                }
                count++;
            }

            if (count > 1) ret += String.valueOf(count);
            ret += prev;

            return ret;
        }
    }
}
