package programmers;

import java.util.*;

public class L60060 {
    static class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
            Map<Integer, List<String>> map = new HashMap<>();
            Map<Integer, List<String>> imap = new HashMap<>();

            for (String word : words) {
                int len = word.length();
                List<String> list = map.getOrDefault(len, new ArrayList<>());
                list.add(word);
                map.put(len, list);

                List<String> reverse = imap.getOrDefault(len, new ArrayList<>());
                reverse.add(new StringBuilder(word).reverse().toString());
                imap.put(len, reverse);
            }

            for (int key : map.keySet()) {
                Collections.sort(map.get(key));
            }

            for (int key : imap.keySet()) {
                Collections.sort(imap.get(key));
            }

            for (int i=0; i < queries.length; i++) {
                String query = queries[i];
                Map<Integer, List<String>> m = map;
                if (query.charAt(0) == '?') {
                    query = new StringBuilder(query).reverse().toString();
                    m = imap;
                }
                int len = query.length();
                if (!map.containsKey(len)) continue;

                int lastIdx = lastIdxOf(m.get(len), query.replaceAll("[?]", "z"));
                int firstIdx = firstIdxOf(m.get(len), query.replaceAll("[?]", "a"));

                // System.out.println(m.get(len) + " > " + query + " = " + firstIdx + " ~ " + lastIdx);
                answer[i] = lastIdx - firstIdx;
            }
            return answer;
        }

        private static int lastIdxOf(List<String> list, String target) {
            if (list.size() == 0) return 0;
            int left = 0;
            int right = list.size();

            while (left < right) {
                // System.out.println("--- " + target + " => " + left + " ~ " + right);
                int mid = (left + right) / 2;
                if (list.get(mid).compareTo(target) == 0) return (mid + 1);
                if (list.get(mid).compareTo(target) < 0) {
                    left = mid + 1;
                    continue;
                }
                right = mid;
            }
            return right;
        }

        private static int firstIdxOf(List<String> list, String target) {
            int left = 0;
            int right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid).compareTo(target) == 0) return mid;
                if (list.get(mid).compareTo(target) > 0) {
                    right = mid;
                    continue;
                }
                left = mid + 1;
            }
            return left;
        }
    }
}
