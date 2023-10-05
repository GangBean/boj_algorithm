package programmers;

public class L64062 {
    class Solution {
        public int solution(int[] stones, int k) {
            int answer = 1;
            int min = 1;
            int max = 200_000_000;

            while (min <= max) {
                int mid = (min + max) / 2;
                if (!isAllAcross(stones, k, mid)) {
                    max = mid - 1;
                    continue;
                }
                answer = Math.max(answer, mid);
                min = mid + 1;
            }

            return answer;
        }

        private boolean isAllAcross(int[] stones, int k, int number) {
            int skip = 0;

            for (int stone : stones) {
                if (stone >= number) {
                    skip = 0;
                    continue;
                }
                skip++;
                if (skip == k) return false;
            }

            return true;
        }

    }
}
