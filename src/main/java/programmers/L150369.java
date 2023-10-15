package programmers;

import java.util.Stack;

public class L150369 {

    static class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long totalDistance = 0L;
            Stack<int[]> p = new Stack<>();
            Stack<int[]> d = new Stack<>();
            for (int i = 1; i <= n; i++) {
                if (pickups[i-1] > 0) p.push(new int[]{i, pickups[i-1]});
                if (deliveries[i-1] > 0) d.push(new int[]{i, deliveries[i-1]});
            }

            while (true) {
                if (p.isEmpty() && d.isEmpty()) break;
                int remain = cap;
                int pDistance = 0;
                while (!p.isEmpty()) {
                    int[] house = p.pop();
                    pDistance = Math.max(pDistance, house[0]);
                    if (remain > house[1]) {
                        remain -= house[1];
                        continue;
                    }
                    if (remain < house[1]) {
                        p.push(new int[]{house[0], house[1] - remain});
                    }
                    break;
                }

                remain = cap;
                int dDistance = 0;
                while (!d.isEmpty()) {
                    int[] house = d.pop();
                    dDistance = Math.max(dDistance, house[0]);
                    if (remain > house[1]) {
                        remain -= house[1];
                        continue;
                    }
                    if (remain < house[1]) {
                        d.push(new int[]{house[0], house[1] - remain});
                    }
                    break;
                }
                totalDistance += 2*Math.max(pDistance, dDistance);
            }
            return totalDistance;
        }
    }
}
