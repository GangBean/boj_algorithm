package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class L72412 {

    // (언어, 직군, 경력, 소울 푸드) 가능한 조합에 대해 각각 점수 이상인 인원수를 담은 map<조합, 점수[]>을 생성
    // 문제점: "-" 조건에 해당하는 질의는 가능한 모든 조합의 점수 리스트를 만들어야 한다
    // 입력 조합에 대한 점수 배열을 가져와 해당하는 점수를 결과에 추가
    // 시간복잡도:
    // -- 생성: info 길이 * 점수 배열 조회 <= 50_000 * 100_000 = 5_000_000_000
    // -- 탐색: O(1)
    // 점수 배열 조회 말고 다른 방법 필요
    // 해당 점수이상인 사람의 수 = 오름차순 정렬된 점수 리스트에서 (리스트의 사이즈 - 해당 점수 이상인 첫번째 인덱스)
    // 시간복잡도:
    // -- 생성: 리스트 append O(1)
    // -- 탐색: 해당 조합 점수 이상인 첫번째 인덱스 찾는 시간 = 이분탐색 = O(log 리스트 최대 길이) = O(log 50,000)
    // 공간복잡도: 총 점수 리스트 사이즈 + a <= 50,000 + a
    static class Solution {
        public int[] solution(String[] info, String[] query) {
            List<Integer> answer = new ArrayList<>();
            // 전체 조합 생성
            Set<Choice> choices = Choice.possibleChoices();
            // System.out.println(choices);

            // 맵 초기화
            Map<Choice, List<Integer>> map = new HashMap<>();
            choices.forEach(choice -> map.put(choice, new ArrayList<>()));
            //System.out.println(map);

            // 정보 입력
            for (String i : info) {
                String[] data = i.split(" ");
                Choice choice = new Choice(data[0], data[1], data[2], data[3]);
                int score = Integer.parseInt(data[4]);
                List<Integer> scores = map.get(choice);
                scores.add(score);
                map.put(choice, scores);
            }

            // 점수리스트 정렬
            map.values().forEach(list -> Collections.sort(list));
            // System.out.println(map);

            // 질의 처리
            for (String q : query) {
                String[] data = q.split(" ");
                Set<Choice> candidates = Choice.possibleChoices(data[0], data[2], data[4], data[6]);
                int score = Integer.parseInt(data[7]);
                // System.out.println("-------------------------------");
                // System.out.println(candidates);
                int count = 0;
                for (Choice candidate : candidates) {
                    int c = countGreaterThan(map.get(candidate), score);
                    // System.out.println(String.format("%s / %d = %d", candidate.toString(), score, c));
                    count += c;
                }
                answer.add(count);
            }
            return answer.stream()
                .mapToInt(i -> i)
                .toArray();
        }

        public static int countGreaterThan(List<Integer> scores, int score) {
            int firstIdx = firstIdxGreaterThan(scores, score);
            return scores.size() - firstIdx;
        }

        private static int firstIdxGreaterThan(List<Integer> scores, int score) {
            int left = 0;
            int right = scores.size() - 1; // 수정: right를 scores.size() - 1로 초기화
            while (left <= right) { // 수정: left와 right가 같은 경우를 포함해야 함
                int mid = (left + right) / 2;
                if (scores.get(mid) >= score) {
                    right = mid - 1; // 목표값 이상인 경우, 더 작은 인덱스로 이동
                    continue;
                }
                left = mid + 1;
            }
            return left;
        }

        static class Choice {
            static final String[][] OPTIONS = {
                {"cpp", "java", "python"},
                {"backend", "frontend"},
                {"junior", "senior"},
                {"chicken", "pizza"}
            };
            String language;
            String job;
            String year;
            String food;
            public Choice(String language, String job, String year, String food) {
                this.language = language;
                this.job = job;
                this.year = year;
                this.food = food;
            }

            public static Set<Choice> possibleChoices(String language, String job, String year, String food) {
                Set<Choice> choices = new HashSet<>();
                String[] query = new String[OPTIONS.length];
                query[0] = language;
                query[1] = job;
                query[2] = year;
                query[3] = food;

                findChoice(choices, 0, query, new ArrayList<>());
                return choices;
            }

            private static void findChoice(Set<Choice> choices, int idx, String[] query, List<String> options) {
                if (idx == OPTIONS.length) {
                    choices.add(new Choice(options.get(0), options.get(1), options.get(2), options.get(3)));
                    return;
                }
                if (query[idx].equals("-")) {
                    for (String option : OPTIONS[idx]) {
                        options.add(option);
                        findChoice(choices, idx+1, query, options);
                        options.remove(options.size() - 1);
                    }
                    return;
                }
                options.add(query[idx]);
                findChoice(choices, idx+1, query, options);
                options.remove(options.size() - 1);
            }

            public static Set<Choice> possibleChoices() {
                Set<Choice> choices = new HashSet<>();
                findChoice(choices, 0, new ArrayList<>());
                return choices;
            }

            private static void findChoice(Set<Choice> choices, int optionIdx, List<String> options) {
                if (optionIdx == OPTIONS.length) {
                    choices.add(new Choice(options.get(0), options.get(1), options.get(2), options.get(3)));
                    return;
                }

                for (String option : OPTIONS[optionIdx]) {
                    options.add(option);
                    findChoice(choices, optionIdx+1, options);
                    options.remove(options.size() - 1);
                }
            }

            @Override
            public String toString() {
                return String.format("[%s, %s, %s, %s]", language, job, year, food);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Choice choice = (Choice) o;
                return Objects.equals(language, choice.language) &&
                    Objects.equals(job, choice.job) &&
                    Objects.equals(year, choice.year) &&
                    Objects.equals(food, choice.food);
            }

            @Override
            public int hashCode() {
                return Objects.hash(language, job, year, food);
            }
        }
    }
}
