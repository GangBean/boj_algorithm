import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int N = Integer.parseInt(reader.readLine());
            Stack<Integer> low = new Stack<>();
            Stack<Integer> high = new Stack<>();
            List<String> result = new ArrayList<>();
            int finalN = N;
            IntStream.range(1, N + 1).map(i -> finalN - i + 1).boxed().forEach(high::push);
            while (N-- > 0) {
                int n = Integer.parseInt(reader.readLine());
                while (!high.isEmpty() && high.peek() <= n) {
                    low.push(high.pop());
                    result.add("+");
                }
                if (!low.empty() && n < low.peek()) {
                    System.out.println("NO");
                    return;
                }
                if (!low.empty() && n == low.peek()) {
                    low.pop();
                    result.add("-");
                }
            }

            result.stream().forEach(System.out::println);
        } finally {
            reader.close();
        }
    }
}
