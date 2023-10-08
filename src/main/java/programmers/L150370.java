package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L150370 {
    static class Solution {
        static final int YEAR = 12 * 28;
        static final int MONTH = 28;
        public int[] solution(String today, String[] terms, String[] privacies) {
            List<Integer> answer = new ArrayList<>();
            Map<String, Integer> term = new HashMap<>();
            for (String t : terms) {
                String[] s = t.split(" ");
                term.put(s[0], Integer.parseInt(s[1]) * MONTH);
            }

            int todayValue = intOf(today);
            int idx = 1;
            for (String privacy : privacies) {
                String[] s = privacy.split(" ");
                int duration = todayValue - intOf(s[0]);
                if (duration >= term.get(s[1])) {
                    answer.add(idx);
                }
                idx++;
            }
            return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }

        private static int intOf(String date) {
            int[] ymd = Arrays.stream(date.split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();
            return ymd[0] * YEAR
                + ymd[1] * MONTH
                + ymd[2];
        }

        private static String dateOf(int num) {
            String year = String.valueOf(num / YEAR);
            num %= YEAR;
            String month = String.format("%02d", num / MONTH);
            String day = String.format("%02d", num % MONTH);
            return year + "." + month + "." + day;
        }
    }
}
