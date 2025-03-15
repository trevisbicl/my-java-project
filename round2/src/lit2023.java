public class lit2023 {
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && target.equals(nums[i] + nums[j])) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        lit2023 solution = new lit2023();
        String[] nums = {"123", "4", "12", "34"};
        String target = "1234";
        solution.numOfPairs(nums, target);
    }
}
