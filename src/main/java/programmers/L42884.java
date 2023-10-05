package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L42884 {
    static class Solution {
        public int solution(int[][] routes) {
            int answer = 0;

            List<Duration> list = new ArrayList<>();
            for (int[] route : routes) {
                list.add(new Duration(route[0], route[1]));
            }
            int pos = -30_000;

            Collections.sort(list);

            for (Duration d : list) {
                if (pos >= d.start) {
                    continue;
                }
                pos = d.end;
                answer++;
            }

            return answer;
        }

        static class Duration implements Comparable<Duration> {
            int start;
            int end;

            public Duration(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public int compareTo(Duration o) {
                return Integer.compare(this.end, o.end);
            }
        }
    }
}
