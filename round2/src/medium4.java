import java.util.*;

public class medium4 {
    static int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[128];
        int ans = 0, n = s.length();

        for (int l = 0, r = 0; r < n; ++r) {
            char c = s.charAt(r);
            ++cnt[c];

            while (cnt[c] > 1) {
                --cnt[s.charAt(l++)];
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

    public static void main(String args[]) {
        String str = "takeUforward";
        System.out.println("Длина самой длинной подстроки без повторений... Как бы это помягче сказать... " + lengthOfLongestSubstring(str) + ", ну и всё.");
    }
}
