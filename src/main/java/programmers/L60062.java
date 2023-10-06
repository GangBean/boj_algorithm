package programmers;

import java.util.ArrayList;
import java.util.List;

public class L60062 {

    static class Solution {
        // P(weak) = [1,5,6,10], [5,6,10,13], [6,10,13,18], [10,13,18,22]
        // P(dist) = 8! 가지
        // 4 * 8! = 4 * 120 * 6 * 7 * 8

        public int solution(int n, int[] weak, int[] dist) {
            int answer = Integer.MAX_VALUE;
            List<List<Integer>> listOfWeaks = listOfPossibleWeakOrders(n, weak);
            List<List<Integer>> listOfDists = listOfPossibleDistOrders(dist, new boolean[dist.length], new ArrayList<>());

            for (List<Integer> w : listOfWeaks) {
                for (List<Integer> d : listOfDists) {
                    int count = countOfMinWork(w, d);
                    // System.out.println(w + " , " + d + " = " + count);
                    answer = Math.min(answer, count);
                }
            }
            return (answer == Integer.MAX_VALUE) ? -1 : answer;
        }

        private static int countOfMinWork(List<Integer> weak, List<Integer> dist) {
            int count = 0;
            int start = 0;
            for (int d : dist) {
                if (start == weak.size()) return count;
                int end = start + 1;
                while (end < weak.size() && weak.get(end) - weak.get(start) <= d) {
                    end++;
                }
                count++;
                start = end;
            }

            return (start == weak.size()) ? count : Integer.MAX_VALUE;
        }

        private List<List<Integer>> listOfPossibleWeakOrders(int n, int[] weak) {
            List<List<Integer>> ret = new ArrayList<>();

            for (int start = 0; start < weak.length; start++) {
                ret.add(listOfWeakOrder(n, weak, start));
            }

            return ret;
        }

        private List<Integer> listOfWeakOrder(int n, int[] weak, int start) {
            List<Integer> ret = new ArrayList<>();
            int count = 0;
            int idx = start;
            while (count < weak.length) {
                int num = weak[idx%weak.length] + n * (idx / weak.length);
                ret.add(num);
                count++;
                idx++;
            }
            return ret;
        }

        private List<List<Integer>> listOfPossibleDistOrders(int[] dist, boolean[] isUsed, List<Integer> list) {
            if (list.size() == dist.length) {
                return new ArrayList<>(List.of(new ArrayList<>(list)));
            }
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < dist.length; i++) {
                if (isUsed[i]) continue;
                isUsed[i] = true;
                list.add(dist[i]);
                ret.addAll(listOfPossibleDistOrders(dist, isUsed, list));
                list.remove(list.size()-1);
                isUsed[i] = false;
            }
            return ret;
        }

    }
}
