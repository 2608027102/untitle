package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {

    public static void main(String[] args) {
        System.out.println(new Solution().removeKdigits(
                "10", 2));
    }
}

class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();

        if (len <= k) {
            return "0";
        }

        Deque<Character> stack = new ArrayDeque<>();

        int count = len - k;

        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || stack.peek() <= num.charAt(i)) {
                stack.push(num.charAt(i));
            } else {
                while (!stack.isEmpty() && stack.peek() > num.charAt(i)) {
                    if (stack.size() + len - i > count) {
                        stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(num.charAt(i));
            }
        }


        StringBuilder sb = new StringBuilder();
        boolean nonZero = false;
        while (!stack.isEmpty() && count > 0) {
            if(stack.peekLast() != '0' || nonZero) {
                nonZero = true;
                sb.append(stack.removeLast());
            } else {
                stack.removeLast();
            }
            count--;
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
