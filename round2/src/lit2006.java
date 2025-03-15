class lit2006 {
    static class Solution {
        public int countKDifference(int[] nums, int k) {
            int ans = 0;
            for (int i = 0, n = nums.length; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (Math.abs(nums[i] - nums[j]) == k) {
                        ++ans;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 2, 1};
        int k = 1;
        int result = solution.countKDifference(nums, k);
        System.out.println("Результат: " + result);
    }
}
