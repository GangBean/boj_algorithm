package programmers;

import java.util.Map;
import java.util.PriorityQueue;

public class L150365 {

// 중복 방문 가능
// 거리기준 최소가 아닌, 경로 최소 기준
// 경로 최소 = 경로 문자열 사전 순
// 이동 경로는 l,r,u,d 로 나타냄
// 결국 d->l->r->u 순으로 탐색해야함
// 우선 순위 큐

    static class Solution {
        static final String[] PATH = {"d", "l", "r", "u"};
        static final Map<String, int[]> DIR = Map.of(
            "d", new int[]{1,0},
            "l", new int[]{0,-1},
            "r", new int[]{0,1},
            "u", new int[]{-1,0}
        );
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            boolean[][][] dp = new boolean[n][m][k];

            for (String path : PATH) {
                int[] dir = DIR.get(path);
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (isValid(n, m, nx, ny)) {
                    dp[nx-1][ny-1][0] = true;
                    q.offer(new Node(path, nx, ny));
                }
            }

            while (!q.isEmpty()) {
                Node node = q.poll();

                int len = node.path.length();
                if (len == k && node.x == r && node.y == c) return node.path;

                if (len + 1 <= k) {
                    for (String path : PATH) {
                        int[] dir = DIR.get(path);
                        int nx = node.x + dir[0];
                        int ny = node.y + dir[1];
                        // System.out.println(node + " => " + String.format("(%d, %d) by \"%s\"", nx, ny, node.path + path));
                        if (isValid(n, m, nx, ny) && !dp[nx-1][ny-1][len]) {
                            dp[nx-1][ny-1][len] = true;
                            q.offer(new Node(node.path + path, nx, ny));
                        }
                    }
                }
            }
            return "impossible";
        }

        private static boolean isValid(int n, int m, int x, int y) {
            return (1<= x && x <= n && 1 <= y && y <= m);
        }

        static class Node implements Comparable<Node> {
            String path;
            int x;
            int y;

            public Node(String path, int x,int y) {
                this.path = path;
                this.x = x;
                this.y = y;
            }

            @Override
            public int compareTo(Node n) {
                return this.path.compareTo(n.path);
            }

            @Override
            public String toString() {
                return String.format("\"%s\" at (%d, %d)", path, x, y);
            }
        }
    }

}
