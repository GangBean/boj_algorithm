import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P1931 {

    /**
    * [문제]
    * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
    *  각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
    *  단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
    *  회의의 시작시간과 끝나는 시간이 같을 수도 있다.
    *  이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
    * 

    * 
    * [입력]
    * 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
    *  둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
    *  시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.
    * 

    * 
    * [출력]
    * 첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        List<Meeting> ret = new ArrayList<>();

        while (N-- > 0) {
            ret.add(new Meeting(Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        Collections.sort(ret);
        int count = 0;
        int idx = 0;
        int prevEnd = 0;
        while (idx < ret.size()) {
            Meeting current = ret.get(idx);
            if (prevEnd <= current.start) {
                count++;
                prevEnd = current.end;
            }
            idx++;
        }
        System.out.println(count);
    }

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int[] duration) {
            this.start = duration[0];
            this.end = duration[1];
        }

        @Override
        public int compareTo(Meeting b) {
            return (this.end == b.end) ? Integer.compare(this.start, b.start) : Integer.compare(this.end, b.end);
        }
    }
}