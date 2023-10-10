package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class L60061 {
    static class Solution {
        static final int ADD = 1;
        static final int REMOVE = 0;
        static final int[][] DIR = {
                {1,0}, {-1,0}, {0,1}, {0,-1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 2}, {1, 2}, {-1, 2}
        };
        public int[][] solution(int n, int[][] build_frame) {
            boolean[][][] map = new boolean[n+1][n+1][2];

            for (int[] frame : build_frame) {
                int x = frame[0];
                int y = frame[1];
                int type = frame[2];
                int action = frame[3];

                if (action == ADD) {
                    // 가능하면 추가
                    Point point = new Point(x, y, type);
                    if (!point.isOk(map)) continue;
                    map[y][x][type] = true;
                    continue;
                }

                if (action == REMOVE) {
                    // 삭제한 상태로 상하좌우 정상여부 판단해 하나라도 비정상인 경우 되돌림
                    map[y][x][type] = false;
                    boolean isOk = true;
                    // for (int[] dir : DIR) {
                    //     int ny = y + dir[0];
                    //     int nx = x + dir[1];
                    //     if (0 <= ny && ny <= n
                    //        && 0 <= nx && nx <= n
                    //        && !new Point(nx, ny, map[ny][nx]).isOk(map)) {
                    //         isOk = false;
                    //         break;
                    //     }
                    // }

                    for (int ny = 0; ny <= n; ny++) {
                        for (int nx = 0; nx <= n; nx++) {
                            for (int t = 0; t < 2; t++) {
                                if (map[ny][nx][t] && !new Point(nx, ny, t).isOk(map)) {
                                    isOk = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (isOk) continue;
                    map[y][x][type] = true;
                }
            }

            List<int[]> list = new ArrayList<>();

            for (int y = n; y >= 0; y--) {
                for (int x = 0; x <= n; x++) {
                    // System.out.print(map[y][x] + "\t");
                    for (int type = 0; type < 2; type++) {
                        if (map[y][x][type]) {
                            list.add(new int[]{x, y, type});
                        }
                    }

                }
                // System.out.println();
            }

            Collections.sort(list, new Comparator<>(){
                @Override
                public int compare(int[] a, int[] b) {
                    if (a[0] == b[0]) {
                        if (a[1] == b[1]) {
                            return Integer.compare(a[2], b[2]);
                        }
                        return Integer.compare(a[1], b[1]);
                    }
                    return Integer.compare(a[0], b[0]);
                }
            });
            return list.toArray(new int[0][0]);
        }

        static class Point {
            static final int COLUMN = 0;
            static final int ROW = 1;
            int y;
            int x;
            int type;
            public Point(int x, int y, int type) {
                this.x = x;
                this.y = y;
                this.type = type;
            }

            public boolean isOk(final boolean[][][] map) {
                if (type == COLUMN) {
                    return isColumnOk(map);
                }
                if (type == ROW) {
                    return isRowOk(map);
                }
                return true;
            }

            private boolean isColumnOk(final boolean[][][] map) {
                if (y == 0 // 바닥이거나
                        || (0 <= x-1 && map[y][x-1][ROW]) // 왼쪽에 보가 있거나
                        || (x < map.length && map[y][x][ROW]) // 오른쪽에 보가 있거나
                        || (0 <= y-1 && map[y-1][x][COLUMN])) { // 아래에 다른기둥이 있으면
                    return true;
                }

                return false;
            }

            private boolean isRowOk(final boolean[][][] map) {
                if ((0 <= y-1 && map[y-1][x][COLUMN]) // 왼쪽끝이 기둥위에 있거나
                        || (0 <= y-1 && x+1 < map.length && map[y-1][x+1][COLUMN]) // 오른쪽 끝이 기둥위에 있거나
                        || (0 <= x-1 && map[y][x-1][ROW] && x+1 < map.length && map[y][x+1][ROW]) // 양쪽 모두에 보가 있으면
                ) {
                    return true;
                }
                return false;
            }
        }
    }
}
