package com.company;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.util.*;

public class Graph {


    static class Solution {


        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) return false;

            //  构造图
            Map<String, Set<String>> graph = new HashMap<>();
            for (List<String> similarPair : similarPairs) {
                for (String s : similarPair) {
                    if (!graph.containsKey(s)) {
                        graph.put(s, new HashSet<>());
                    }
                }

                graph.get(similarPair.get(0)).add(similarPair.get(1));
                graph.get(similarPair.get(1)).add(similarPair.get(0));
            }

            for (int i = 0; i < sentence1.length; i++) {
                String w1 = sentence1[i], w2 = sentence2[i];
                if (w1.equals(w2)) {
                    continue;
                }
                Set<String> seen = new HashSet<>();
                seen.add(w1);

                Deque<String> stack = new ArrayDeque<>();
                stack.push(w1);

                outer: {
                    while (!stack.isEmpty()) {
                        // 从栈顶取一条数据用于图节点的遍历
                        String word = stack.pop();
                        if (word.equals(w2)) {
                            break outer;
                        }
                        // 如果没值了 说明找不到了
                        if (!graph.containsKey(word)) {
                            break;
                        }
                        // 如果邻接点包含w2说明可以找到
                        if (graph.get(word).contains(w2)) {
                            break outer;
                        }
                        // 遍历邻接点 继续向下遍历
                        for (String neighbor : graph.get(word)) {
                            if (!seen.contains(neighbor)) {
                                stack.push(neighbor);
                                seen.add(neighbor);
                            }
                        }
                    }
                    return false;
                }
            }

            return true;
        }
    }


    public static void main(String[] args) {
        String[] sentence1 = JSON.parseArray("[\"I\",\"love\",\"leetcode\"]").toArray(new String[0]);
        String[] sentence2 = JSON.parseArray("[\"I\",\"love\",\"onepiece\"]").toArray(new String[0]);
        List<List<String>> similarity = JSON.parseObject("[[\"manga\",\"onepiece\"],[\"platform\",\"anime\"],[\"leetcode\",\"platform\"],[\"anime\",\"manga\"]]",
                new TypeReference<List<List<String>>>() {
                });
        boolean result = new Solution().areSentencesSimilarTwo(sentence1, sentence2, similarity);
        System.out.println(result);
    }

}
