import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int startIndex = in.nextInt();
        int endIndex = in.nextInt();

        System.out.println(reverseWords(line, startIndex, endIndex));
    }

    static String reverseWords(String s, int start, int end) {
        String[] arr = s.split(" ");

        start = start < 0 ? 0 : (Math.min(start, arr.length));
        end = end < 0 ? 0 : (Math.min(end, arr.length));

        if (start == end) {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i >= start && i <= end) {
                int fix = end == arr.length ? 1 : 0;
                for (int j = fix; j <= (end-start); j++) {
                    builder.append(arr[end - j]).append(" ");
                }
                i += (end - start);
                continue;
            }
            builder.append(arr[i]).append(" ");
        }
        return builder.substring(0, builder.length() - 1);
    }
}