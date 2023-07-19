import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ProblemClassGenerator {

    public static final String BOJ_PROBLEM_URL = "https://www.acmicpc.net/problem/%d";
    public static final String PROBLEM_DESCRIPTION = "problem_description";
    public static final String PROBLEM_INPUT = "problem_input";
    public static final String PROBLEM_OUTPUT = "problem_output";
    public static final String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");
    public static final String TARGET_DIRECTORY = PROJECT_ROOT_DIRECTORY + "/src/main/java/P%d.java";
    public static final String LINE_END = "\\.";
    public static final String END_WITH_LINE_FEED = ".\n    * ";
    public static final String BLANK_STRING = "";
    public static final String P_TAG = "p";
    public static final BinaryOperator<String> STRING_CONCAT = (a, b) -> a + b;
    public static final String ONE_LEVEL_INDENT = "    ";
    public static final String COMMENT_INDENT = "    * ";
    public static final String COMMENT_END = "    */";

    public static void main(String[] args) throws IOException {
        System.out.print("> 문제번호를 입력하세요: ");
        generate(new Scanner(System.in).nextInt());
    }

    public static void generate(int problemNumber) throws IOException {
        String url = String.format(BOJ_PROBLEM_URL, problemNumber);
        Connection connection = Jsoup.connect(url);
        Document document = connection.get();

        write(problemNumber, getStringValue(document, PROBLEM_DESCRIPTION)
            , getStringValue(document, PROBLEM_INPUT), getStringValue(document, PROBLEM_OUTPUT));
    }

    private static String getStringValue(Document document, String id) {
        return Objects.requireNonNull(
            document.getElementById(id)).select(P_TAG).stream()
            .map(Element::text)
            .reduce(BLANK_STRING, STRING_CONCAT).replaceAll(LINE_END, END_WITH_LINE_FEED);
    }

    private static void write(int problemNumber, String problemDescription
        , String problemInput, String problemOutput) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.format(TARGET_DIRECTORY, problemNumber)), StandardCharsets.UTF_8));
        writer.write(getClassFile(problemNumber, problemDescription, problemInput, problemOutput));
        writer.close();
    }

    private static String getClassFile(int problemNumber, String problemDescription,
        String problemInput, String problemOutput) {
        return new StringBuilder().append("public class P").append(problemNumber).append(" {\n\n")
            .append(ONE_LEVEL_INDENT).append("/**").append("\n")
            .append(COMMENT_INDENT).append("[문제]").append("\n")
            .append(COMMENT_INDENT).append(problemDescription).append("\n").append("\n")
            .append(COMMENT_INDENT).append("\n")
            .append(COMMENT_INDENT).append("[입력]").append("\n")
            .append(COMMENT_INDENT).append(problemInput).append("\n").append("\n")
            .append(COMMENT_INDENT).append("\n")
            .append(COMMENT_INDENT).append("[출력]").append("\n")
            .append(COMMENT_INDENT).append(problemOutput).append("\n").append("\n")
            .append(COMMENT_END).append("\n")
            .append(ONE_LEVEL_INDENT).append("public static void main(String[] args) {").append("\n")
            .append(ONE_LEVEL_INDENT).append("}").append("\n")
            .append("}").toString();
    }
}
