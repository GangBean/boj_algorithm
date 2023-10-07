package programmers;

import java.util.Arrays;

public class L72414 {
    static class Solution {
        static final int HOUR = 60 * 60;
        static final int MINUTE = 60;
        public String solution(String play_time, String adv_time, String[] logs) {
            int[] playingCountPerSec = new int[100 * HOUR + 1];
            for (String log : logs) {
                String[] startAndEnd = log.split("-");
                int start = secOf(startAndEnd[0]);
                int end = secOf(startAndEnd[1]);
                playingCountPerSec[start]++;
                playingCountPerSec[end]--;
            }

            for (int i = 1; i < playingCountPerSec.length; i++) {
                playingCountPerSec[i] += playingCountPerSec[i-1];
            }

            // System.out.println("play_time = " + secOf(play_time) + " -> " + timeAt(secOf(play_time)));

            int playTimeInSec = secOf(play_time);
            int advTimeInSec = secOf(adv_time);

            int maxAdvStartTime = playTimeInSec - advTimeInSec;
            int maxStartSec = 0;
            long advPlayTime = 0;
            for (int time = 0; time < advTimeInSec; time++) {
                advPlayTime += playingCountPerSec[time]; // 00:00:00 의 누적재생시간
            }
            long maxAdvPlayTime = advPlayTime;
            for (int time = 1; time <= maxAdvStartTime; time++) {
                // 가능한 시작시간을 모두 탐색
                // 누적재생시간 = 이전누적재생시간 + 종료시점카운트 - 이전종료시점카운트
                // [0,1) -> [1,2)
                advPlayTime += (playingCountPerSec[time - 1 + advTimeInSec] - playingCountPerSec[time - 1]);
                if (maxAdvPlayTime < advPlayTime) {
                    maxAdvPlayTime = advPlayTime;
                    maxStartSec = time;
                }
            }

            return timeAt(maxStartSec);
        }

        private static int secOf(String time) {
            int[] hms = Arrays.stream(time.split(":"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return hms[2] +
                    hms[1] * MINUTE +
                    hms[0] * HOUR;
        }

        private static String timeAt(int sec) {
            if (sec < 0) throw new RuntimeException("초는 음수일 수 없습니다.");
            String hour = String.format("%02d", sec / HOUR);
            sec %= HOUR;
            String minute = String.format("%02d", sec / MINUTE);
            sec %= MINUTE;
            String secs = String.format("%02d", sec);
            return hour + ":" + minute + ":"+ secs;
        }
    }
}
