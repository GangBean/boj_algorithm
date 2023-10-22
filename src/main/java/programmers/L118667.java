package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class L118667 {
    static class Solution {
        public int solution(int[] queue1, int[] queue2) {
            long total = 0;
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();
            long sum1 = 0;
            for (int num : queue1) {
                total += num;
                sum1 += num;
                q1.offer(num);
            }
            for (int num : queue2) {
                total += num;
                q2.offer(num);
            }

            if (total % 2 != 0) return -1;

            long target = total / 2;
            int count = 3 * queue1.length;
            int c = 0;
            while (count-- > 0) {
                if (target == sum1) return c;
                if (sum1 > target) {
                    sum1 -= q1.peek();
                    q2.offer(q1.poll());
                    c++;
                    continue;
                }
                sum1 += q2.peek();
                q1.offer(q2.poll());
                c++;
            }
            return -1;
        }
    }
}
