import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class P1991 {

    /**
    * [문제]
    * 이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.
    * 예를 들어 위와 같은 이진 트리가 입력되면,가 된다.
    * 

    * 
    * [입력]
    * 첫째 줄에는 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다.
    *  둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다.
    *  노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다.
    *  자식 노드가 없는 경우에는 .
    * 으로 표현한다.
    * 

    * 
    * [출력]
    * 첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다.
    *  각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(reader.readLine());
        Map<String, Node> map = new HashMap<>();
        for (int i = 0; i < s; i++) {
            String[] input = reader.readLine().split(" ");
            Node left = null;
            Node right = null;
            if (!input[1].equals(".")) {
                left = map.getOrDefault(input[1], new Node(input[1], null, null));
                map.put(input[1], left);
            }
            if (!input[2].equals(".")) {
                right = map.getOrDefault(input[2], new Node(input[2], null, null));
                map.put(input[2], right);
            }
            Node node = map.getOrDefault(input[0], new Node(input[0], null, null));
            node.left = left;
            node.right = right;
            map.put(input[0], node);
        }

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.join("", Node.preOrder(map.get("A"))));
        joiner.add(String.join("", Node.inOrder(map.get("A"))));
        joiner.add(String.join("", Node.postOrder(map.get("A"))));
        System.out.print(joiner);
    }

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public static List<String> preOrder(Node root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<String> ret = new ArrayList<>();
            ret.add(root.value);
            ret.addAll(preOrder(root.left));
            ret.addAll(preOrder(root.right));
            return ret;
        }

        public static List<String> inOrder(Node root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<String> ret = new ArrayList<>();
            ret.addAll(inOrder(root.left));
            ret.add(root.value);
            ret.addAll(inOrder(root.right));
            return ret;
        }

        public static List<String> postOrder(Node root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<String> ret = new ArrayList<>();
            ret.addAll(postOrder(root.left));
            ret.addAll(postOrder(root.right));
            ret.add(root.value);
            return ret;
        }
    }
}