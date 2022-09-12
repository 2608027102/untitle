import java.util.*;

public class Main {

    //
    public static void main(String[] args) {
        System.out.println(solve(100, new int[]{2, 3, 1, 2, 4, 3}));
    }

    static int solve(int s, int[] nums) {

        int min = Integer.MAX_VALUE;
        boolean found = false;


        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int sum = 0, count = 0;
            while (j < nums.length) {
                sum += nums[j++];
                count++;
                if (sum >= s) {
                    found = true;
                    min = Math.min(min, count);
                    break;
                }
            }
        }
        return found ? min : 0;
    }

}