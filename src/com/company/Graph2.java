package com.company;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.util.*;

public class Graph2 {


    static class Solution {

        int find(int[] parentMap, int x) {
            if (parentMap[x] == x) {
                return x;
            }
            // 压缩，父节点直接设置为根节点
            parentMap[x] = find(parentMap, parentMap[x]);
            return parentMap[x];
        }

        void merge(int[] parentMap, int p, int c) {
            // 分别找到两个节点的父节点，让父节点进行merge
            parentMap[find(parentMap, c)] = find(parentMap, p);
        }

        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) return false;

            //  并查集
            Map<String, Integer> index = new HashMap<>();
            int[] parentMap = new int[similarPairs.size() * 2];
            // init
            for (int i = 0; i < parentMap.length; i++) {
                parentMap[i] = i;
            }
            int count = 0;
            for (List<String> similarPair : similarPairs) {
                for (String s : similarPair) {
                    if (!index.containsKey(s)) {
                        index.put(s, count++);
                    }
                }
                merge(parentMap, index.get(similarPair.get(0)), index.get(similarPair.get(1)));
            }

            for (int i = 0; i < sentence1.length; i++) {
                String w1 = sentence1[i], w2 = sentence2[i];
                if (w1.equals(w2)) {
                    continue;
                }
                Integer w1Index = index.get(w1);
                Integer w2Index = index.get(w2);
                if (w1Index == null || w2Index == null) {
                    return false;
                }
                int p1 = find(parentMap, w1Index);
                int p2 = find(parentMap, w2Index);
                if (p1 != p2) {
                    return false;
                }
            }

            return true;
        }
    }


    public static void main(String[] args) {
        String[] sentence1 = JSON.parseArray("[\"great\",\"acting\",\"skills\"]").toArray(new String[0]);
        String[] sentence2 = JSON.parseArray("[\"fine\",\"drama\",\"talent\"]").toArray(new String[0]);
        List<List<String>> similarity = JSON.parseObject("[[\"great\",\"good\"],[\"fine\",\"good\"],[\"drama\",\"acting\"],[\"skills\",\"talent\"]]",
                new TypeReference<List<List<String>>>() {
                });
        boolean result = new Solution().areSentencesSimilarTwo(sentence1, sentence2, similarity);
        System.out.println(result);
    }

}
