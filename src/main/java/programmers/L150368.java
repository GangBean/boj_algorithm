package programmers;

public class L150368 {

    static class Solution {
        static final int[] DISCOUNT = {40, 30, 20, 10};
        static int maxAmount;
        static int maxCount;
        public int[] solution(int[][] users, int[] emoticons) {
            buyEmoticons(users, emoticons, 0, new int[emoticons.length]);
            return new int[]{maxCount, maxAmount};
        }

        private static void buyEmoticons(int[][] users, int[] emoticons, int idx, int[] rates) {
            if (idx == emoticons.length) {
                int[] result = calculated(users, emoticons, rates);
                int count = result[0];
                int amount = result[1];
                if (maxCount < count) {
                    maxCount = count;
                    maxAmount = amount;
                    return;
                }
                if (maxCount == count) {
                    maxAmount = Math.max(maxAmount, amount);
                }
                return;
            }

            for (int rate : DISCOUNT) {
                rates[idx] = rate;
                buyEmoticons(users, emoticons, idx+1, rates);
            }
        }

        private static int[] calculated(int[][] users, int[] emoticons, int[] rates) {
            int totalAmount = 0;
            int count = 0;

            for (int[] user : users) {
                int cRate = user[0];
                int cPrice = user[1];
                int amount = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    int rate = rates[i];
                    if (rate >= cRate) {
                        amount += (emoticons[i] * (100 - rate) / 100);
                    }
                }
                totalAmount += amount;
                if (amount >= cPrice) {
                    count++;
                    totalAmount -= amount;
                    continue;
                }
            }

            return new int[]{count, totalAmount};
        }
    }
}
