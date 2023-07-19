package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_9093 {

    public static void main(String[] args) throws Exception {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            while (N-- > 0) {
                String line = reader.readLine();
                String[] words = line.split(" ");
                System.out.println(Arrays.stream(words)
                        .map(StringBuilder::new)
                        .map(StringBuilder::reverse)
                        .reduce(new StringBuilder(), (a, b) -> a.append(" ").append(b)).toString().trim());
            }
        }
    }
}
