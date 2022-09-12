package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("cbacdcbc"));
    }

    static class Solution {
        public String removeDuplicateLetters(String num) {
            int len = num.length();

            Deque<Character> stack = new ArrayDeque<>();
            int[] index = new int[26];
            boolean[] stackIndex = new boolean[26];

            for (int i = 0; i < len; i++) {
                // 记录最后出现的位置
                index[num.charAt(i) - 'a'] = i;
            }

            for (int i = 0; i < len; i++) {
                if (stackIndex[num.charAt(i) - 'a']) {
                    continue;
                }
                if (stack.isEmpty() || stack.peek() < num.charAt(i)) {
                    stack.push(num.charAt(i));
                } else {
                    while (!stack.isEmpty() && stack.peek() >= num.charAt(i)) {
                        if (i < index[stack.peek() - 'a']) {
                            stackIndex[stack.peek() - 'a'] = false;
                            stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(num.charAt(i));
                }
                stackIndex[num.charAt(i) - 'a'] = true;
            }


            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.removeLast());
            }

            return sb.toString();
        }
    }
}

