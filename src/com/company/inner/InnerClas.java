package com.company.inner;

import com.company.Clas;

import java.util.*;

public class InnerClas {

    /**
     * 给定一个包含n个整数的数组nums。判断nums是否存在三个元素a，b，c是的a+b+c=0.请你找出所有和为0且不重复的三元组
     * <p></p>
     * 注：答案中不可以包含重复的三元组
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>(nums.length / 3);

        // [-1,0,1,2,-1,-4]
        // [-4,-1,-1,0,1,2]

        // [-2,0,1,1,2]
        // [-2,0,1,1,2]
        for (int i = 0; i < nums.length; i++) {
            // 遇到重复的数字就向后移动
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int num = nums[j] + nums[k];
                int target = -nums[i];
                if (num > target) {
                    k--;
                } else if (num < target) {
                    j++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 遇到重复的数字就向后移动
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    // 还要再移动一次
                    j++;

                    // 遇到重复的数字就向前移动
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    // 还要再移动一次
                    k--;
                }
            }
        }

        return result;
    }
}
