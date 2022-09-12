package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        // 如果首个数字已经大于0了 那么一定不会有符合条件的值
        if (nums[0] > 0) return new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果出现连续的值 则直接跳过，防止重复
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 双指针
            int left = i + 1, right = nums.length - 1;
            while (left < right) {

                // 去重
                if(left > i + 1 && nums[left] == nums[left - 1]) {
                    left ++;
                    continue;
                }

                // 双指针对应的数字之和小于目标值，说明需要往右移动指针
                if (nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else if (nums[left] + nums[right] > -nums[i]) {
                    // 双指针的数字之和大于目标值，说明需要向左移动指针
                    right--;
                } else {
                    // 符合条件的数据
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 由于本题中需要返回所有匹配的值，因此，需要继续向后遍历是否又匹配的值 例如
                    // -4 -2 2 4

                    // 移动指针位置
                    left++;
                    right--;

                    // 去重，如果中途又很多重复的数据，则直接跳过
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
