import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class P11725 {

    /**
    * [문제]
    * 루트 없는 트리가 주어진다.
    *  이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다.
    *  둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Map<Integer, List<Integer>> connected = new HashMap<>();

        for (int i = 2; i <= N; i++) {
            int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
            List<Integer> list = connected.getOrDefault(nums[0], new ArrayList<>());
            list.add(nums[1]);
            connected.put(nums[0], list);

            list = connected.getOrDefault(nums[1], new ArrayList<>());
            list.add(nums[0]);
            connected.put(nums[1], list);
        }

        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);
        pairing(connected, parent, 1);

        StringJoiner joiner = new StringJoiner("\n");
        Arrays.stream(parent, 2, N + 1)
            .mapToObj(String::valueOf)
            .forEach(joiner::add);

        System.out.println(joiner);
    }

    private static void pairing(Map<Integer, List<Integer>> connected, int[] parent, int node) {
        List<Integer> candidates = connected.get(node);

        for (int candidate : candidates) {
            if (parent[candidate] != -1) continue;
            parent[candidate] = node;
            pairing(connected, parent, candidate);
        }
    }
}