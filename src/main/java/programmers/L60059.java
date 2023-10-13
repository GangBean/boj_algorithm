package programmers;

public class L60059 {
    static class Solution {
        // 방향 3개
        // (N + M - 1) * (N + M - 1) => 1_600
        // 1 + ... + N * N => 80_000
        // 3 * 1_600 * 80_000 = 4_800 * 80_000 = 3_840_000
        // 1. void rotate(int[][] key): 90도 시계방향으로 돌림
        // 2. boolean isMatch(int[][] key, int[][] lock, int y, int x) : 키의 왼쪽 상단이 (y,x) 일때 매치여부
        public boolean solution(int[][] key, int[][] lock) {
            int N = lock.length;
            int M = key.length;

            int len = N + M - 1;

            for (int dir = 0; dir < 4; dir++) {
                System.out.println("--------");
                for (int y = 0; y < key.length; y++) {
                    for (int x = 0; x < key[0].length; x++) {
                        System.out.print(key[y][x] + "\t");
                    }
                    System.out.println();
                }
                for (int y = 0; y < len; y++) {
                    for (int x = 0; x < len; x++) {
                        if (isMatch(key, lock, y, x)) return true;
                    }
                }
                key = rotated(key);
            }
            return false;
        }

        // (0, 0) -> (0, 2)
        // (0, 1) -> (1, 2)
        // (0, 2) -> (2, 2)
        // (1, 0) -> (0, 1)
        // (1, 1) -> (1, 1)
        // (1, 2) -> (2, 1)
        // (2, 0) -> (0, 0)
        // (2, 1) -> (1, 0)
        // (2, 2) -> (2, 0)
        // y += (N - 1)
        // y %= N
        //
        private static int[][] rotated(int[][] key) {
            int[][] rotated = new int[key[0].length][key.length];
            for (int y = 0; y < key.length; y++) {
                for (int x = 0; x < key[0].length; x++) {
                    rotated[y][x] = key[x][key.length - 1 - y];
                }
            }
            return rotated;
        }

        // (0,0) -> ()
        private static boolean isMatch(int[][] key, int[][] lock, int y, int x) {
            // System.out.println(y + " / " + x + " , ");

            int[][] match = new int[lock.length][lock[0].length];

            for (int my = 0; my < match.length; my++) {
                for (int mx = 0; mx < match[0].length; mx++) {
                    match[my][mx] = lock[my][mx];
                }
            }

            for (int gy = y + key.length - 1; gy >= y; gy--) {
                for (int gx = x + key[0].length - 1; gx >= x; gx--) {
                    if (key.length-1 <= gy && gy < key.length -1 + lock.length
                            && key[0].length-1 <= gx && gx < key[0].length - 1 + lock[0].length) {
                        match[gy-key.length+1][gx-key[0].length+1] = (key[gy-y][gx-x] + lock[gy-key.length+1][gx-key[0].length+1]);
                    }
                }
            }

            for (int my = 0; my < match.length; my++) {
                for (int mx = 0; mx < match[0].length; mx++) {
                    if (match[my][mx] != 1) return false;
                }
            }
            return true;
        }
    }
}
