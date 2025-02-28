import java.util.HashMap;
import java.util.Map;

class lit1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complements = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (complements.containsKey(nums[i])) {
                return new int[]{complements.get(nums[i]), i};
            }
            complements.put(target - nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        lit1 solution = new lit1();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = solution.twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
