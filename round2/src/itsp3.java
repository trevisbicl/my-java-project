import java.util.HashMap;
import java.util.Map;

class itsp3 {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            int current = romanMap.get(s.charAt(i));
            if (i + 1 < length && current < romanMap.get(s.charAt(i + 1))) {
                result -= current;
            } else {
                result += current;
            }
        }

        return result;
    }

//    public static void main(String[] args) {
//        itsp3 solution = new itsp3();
//
//        String romanNumeral1 = "III";
//        String romanNumeral2 = "IV";
//        String romanNumeral3 = "IX";
//        String romanNumeral4 = "LVIII";
//
//        System.out.println("Римское число " + romanNumeral1 + " равно " + solution.romanToInt(romanNumeral1));
//        System.out.println("Римское число " + romanNumeral2 + " равно " + solution.romanToInt(romanNumeral2));
//        System.out.println("Римское число " + romanNumeral3 + " равно " + solution.romanToInt(romanNumeral3));
//        System.out.println("Римское число " + romanNumeral4 + " равно " + solution.romanToInt(romanNumeral4));
//    }
}
