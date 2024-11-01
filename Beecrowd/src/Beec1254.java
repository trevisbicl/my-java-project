import java.util.Scanner;

public class Beec1254 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tag = scanner.nextLine().trim();

        while (!tag.isEmpty()) {
            String newTag = scanner.nextLine().trim();
            String line = scanner.nextLine();

            String result = "";

            boolean insideTag = false;
            int matchIndex = 0;

            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);

                if (currentChar == '<') {
                    insideTag = true;
                    matchIndex = 0;
                    result += currentChar;
                } else if (currentChar == '>') {
                    insideTag = false;
                    if (matchIndex == tag.length()) {
                        result += newTag;
                    }
                    result += currentChar;
                } else if (insideTag) {
                    if (matchIndex < tag.length() &&
                            Character.toUpperCase(currentChar) == Character.toUpperCase(tag.charAt(matchIndex))) {
                        matchIndex++;
                    } else {
                        matchIndex = 0;
                    }
                    result += currentChar;
                } else {
                    result += currentChar;
                }
            }

            System.out.println(result);

            if (scanner.hasNextLine()) {
                tag = scanner.nextLine().trim();
            } else {
                break;
            }
        }

        scanner.close();
    }
}
