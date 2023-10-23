package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L72411 {

    // 완전탐색
    // 전체 주문별로 코스를 원하는 코스 구성 요리 개수에 해당하는 조합별로 개수를 카운트
    // Map<Integer, Map<String, Integer>> countPerCourseGroupBySize; -> 코스 사이즈별 코스의 카운트
    // 주문별로 고려 가능한 모든 코스를 계산
    // 각 계산별로 카운트를 추가
    // 모든 사이즈별로 가장 많은 투표를 받은 코스를 결과에 담음
    // 결과를 오름차순으로 정렬 후 리턴
    static class Solution {
        public String[] solution(String[] orders, int[] course) {
            List<String> answer = new ArrayList<>();
            Map<Integer, Map<String, Integer>> countPerCourseGroupBySize = new HashMap<>();
            for (String order : orders) {
                List<String> possibleCourses = possibleCoursesOf(order, course);
                // System.out.println(possibleCourses);
                for (String candidate : possibleCourses) {
                    int size = candidate.length();
                    Map<String, Integer> countPerCourse = countPerCourseGroupBySize.getOrDefault(size, new HashMap<>());
                    countPerCourse.put(candidate, countPerCourse.getOrDefault(candidate, 0) + 1);
                    countPerCourseGroupBySize.put(size, countPerCourse);
                }
            }
            // System.out.println(countPerCourseGroupBySize);
            for (int size : countPerCourseGroupBySize.keySet()) {
                int maxCount = 0;
                Map<String, Integer> countPerCourse = countPerCourseGroupBySize.get(size);
                List<String> maxCountCourses = new ArrayList<>();
                for (String candidate : countPerCourse.keySet()) {
                    int count = countPerCourse.get(candidate);
                    if (count < 2) continue;
                    if (count > maxCount) {
                        maxCount = count;
                        maxCountCourses = new ArrayList<>(List.of(candidate));
                        continue;
                    }
                    if (count == maxCount) {
                        maxCountCourses.add(candidate);
                    }
                }
                answer.addAll(maxCountCourses);
            }
            return answer.stream()
                    .sorted()
                    .toArray(String[]::new);
        }

        private static List<String> possibleCoursesOf(String order, int[] counts) {
            List<String> courses = new ArrayList<>();
            for (int size : counts) {
                courses.addAll(possibleCoursesOf(order.toCharArray(), 0, size, new StringBuilder()));
            }
            return courses;
        }

        private static List<String> possibleCoursesOf(char[] order, int idx, int size, StringBuilder course) {
            if (course.length() == size) {
                return new ArrayList<>(List.of(Stream.of(course.toString().split(""))
                        .sorted()
                        .collect(Collectors.joining())));
            }
            if (idx == order.length) {
                return new ArrayList<>();
            }
            List<String> courses = new ArrayList<>();

            course.append(order[idx]);
            courses.addAll(possibleCoursesOf(order, idx+1, size, course));
            course.deleteCharAt(course.length() - 1);
            courses.addAll(possibleCoursesOf(order, idx+1, size, course));
            return courses;
        }
    }
}
