import java.util.Scanner;

public class Beec1234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String result = DacnceDead(sentence);
            System.out.println(result);
        }
    }

    public static String DacnceDead(String sentence) {
        String result = "";
        boolean isUpper = true;

        for (int i = 0; i < sentence.length(); i++) {
            char currentChar = sentence.charAt(i);

            if (currentChar != ' ') {
                if (isUpper) {
                    result += Character.toUpperCase(currentChar);
                } else {
                    result += Character.toLowerCase(currentChar);
                }
                isUpper = !isUpper;
            } else {
                result += " ";
            }
        }

        return result;
    }
}
