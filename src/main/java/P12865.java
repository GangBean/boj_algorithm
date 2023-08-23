import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class P12865 {

    /**
    * [문제]
    * 이 문제는 아주 평범한 배낭에 관한 문제이다.
    * 한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다.
    *  세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
    * 준서가 여행에 필요하다고 생각하는 N개의 물건이 있다.
    *  각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
    *  아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다.
    *  준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
    * 

    * 
    * [입력]
    * 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
    *  두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
    * 입력으로 주어지는 모든 수는 정수이다.
    * 

    * 
    * [출력]
    * 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] maxValue = new int[input[1] + 1];
        List<Node> items = new ArrayList<>();
        for (int i = 0; i < input[0]; i++) {
            int[] item = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            items.add(new Node(item[0], item[1]));
        }
        Collections.sort(items);
        boolean[] isPacked = new boolean[items.size()];
        packing(items, isPacked, maxValue, input[1], 0, 0);
        System.out.println(Arrays.stream(maxValue).max().orElseThrow());
    }

    private static void packing(List<Node> items, boolean[] isPacked, int[] maxValue, int maxWeight, int weight, int value) {
        if (maxWeight == weight) return;

        for (int i = 0; i < isPacked.length; i++) {
            int nextWeight = weight + items.get(i).weight;
            int nextValue = value + items.get(i).value;
            if (!isPacked[i] && nextWeight <= maxWeight && maxValue[nextWeight] < nextValue) {
                isPacked[i] = true;
                maxValue[nextWeight] = nextValue;
                packing(items, isPacked, maxValue, maxWeight, nextWeight, maxValue[nextWeight]);
                isPacked[i] = false;
            }
        }
    }

    static class Node implements Comparable<Node> {
        int weight;
        int value;

        public Node(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return (value == o.value) ? Integer.compare(weight, o.weight) : Integer.compare(o.value, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return weight == node.weight && value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, value);
        }
    }
}