// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
import java.util.stream.Stream;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line = in.nextLine();
            int[] nums = Stream.of(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    min = Math.min(min, Math.abs(nums[i] + nums[j]));
                }
            }
            System.out.println(min);
        }
    }
}