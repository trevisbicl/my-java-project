import java.util.HashMap;

class lit169 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);

            if (map.get(n) > nums.length / 2) {
                return n;
            }
        }

        return 0;
    }

//    public static void main(String[] args) {
//        int[] nums = {3, 2, 3};
//
//        lit169 solution = new lit169();
//        int result = solution.majorityElement(nums);
//
//        System.out.println("элемент это: " + result);
//    }
}
