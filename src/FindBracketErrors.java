import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class FindBracketErrors {

    private static String openerIndicator = "o";
    private static String closerIndicator = "c";

    //for this to work properly, matching brackets must be in same index in these arrays
    private static char[] openers = {'(', '{', '[', '<'};
    private static char[] closers = {')', '}', ']', '>'};

    public static void main(String[] args) {

        String fileName = "code.txt";
        String filePath = new File("").getAbsolutePath() + "/" + fileName;
        String fileContents = getFileContents(filePath);
        System.out.printf("Count of opening brackets is equal to count of closing brackets? %s\n", (allBracketCountsMatch(fileContents) ? "YES" : "NO"));
        File myFile = new File(filePath);
        evalForBracketErrors(myFile);
    }

    private static void evalForBracketErrors(File file) {
        Stack<Character> bracketStack = new Stack<>();
        Scanner inputFile;
        int lineCount = 0;
        int colCount;
        String line;
        String bracketType;
        int bracketTypeIndex;
        char expectedOpener;
        char expectedCloser;
        try {
            inputFile = new Scanner(file);
            while (inputFile.hasNextLine()) {
                lineCount++;
                line = inputFile.nextLine();
                colCount = 0;
                for (char ch : line.toCharArray()) {
                    colCount++;

                    if (!getBracketCharType(ch).equals("")) {
                        bracketType = getBracketCharType(ch).toLowerCase().substring(0, openerIndicator.length());
                        bracketTypeIndex = Integer.parseInt(getBracketCharType(ch).substring(openerIndicator.length()));
                        if (bracketType.equals(openerIndicator)) {
                            bracketStack.push(ch);
                        } else if (bracketType.equals(closerIndicator)) {
                            expectedOpener = openers[bracketTypeIndex];
                            try {
                                if (expectedOpener == bracketStack.peek()) {
                                    bracketStack.pop();
                                } else {
                                    expectedCloser = closers[Integer.parseInt(getBracketCharType(bracketStack.peek()).substring(openerIndicator.length()))];
                                    System.err.printf("Error: '%s' expected at line %d, column %d\n", expectedCloser, lineCount, colCount);
                                    return;
                                }
                            } catch (EmptyStackException ese) {
                                System.err.printf("Unopened bracket error: closing bracket '%s' found at line %d, column %d, but bracket set not opened previously\n", ch, lineCount, colCount);
                                return;
                            }
                        }
                    }
                }

                //check for unclosed brackets after reaching eof
                if (!bracketStack.isEmpty()) {
                    expectedCloser = closers[Integer.parseInt(getBracketCharType(bracketStack.peek()).substring(openerIndicator.length()))];
                    System.err.printf("Unclosed bracket error: '%s' expected at line %d, column %d\n", expectedCloser, lineCount, colCount + 1);
                    return;
                }

                System.out.println("No errors");

            }
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }

    }

    private static String getBracketCharType(char ch) {
        String result = "";
        int index = 0;
        for (char o : openers) {
            if (ch == o) {
                result = openerIndicator + Integer.toString(index);
                break;
            }
            index++;
        }
        index = 0;
        for (char c : closers) {
            if (ch == c) {
                result = closerIndicator + Integer.toString(index);
                break;
            }
            index++;
        }
        return result;

    }

    private static boolean allBracketCountsMatch(String code) {

        char[] openers = {'(', '{', '['};
        char[] closers = {')', '}', ']'};
        int[] openerCount = new int[3];
        int[] closerCount = new int[3];

        for (int i = 0; i < openers.length; ++i) {
            for (char ch : code.toCharArray()) {
                if (ch == openers[i]) {
                    openerCount[i]++;
                }
            }
//            System.out.println("Number of \'" + openers[i] + "\' = " + openerCount[i]);

            for (char ch : code.toCharArray()) {
                if (ch == closers[i]) {
                    closerCount[i]++;
                }
            }
//            System.out.println("Number of \'" + closers[i] + "\' = " + closerCount[i]);
        }

        for (int i = 0; i < openerCount.length; ++i) {
            if (openerCount[i] != closerCount[i]) {
                return false;
            }
        }
        return true;
    }

    private static String getFileContents(String filePath) {

        StringBuilder contents = new StringBuilder();
        Scanner inputFile;
        File myFile = new File(filePath);

        try {
            inputFile = new Scanner(myFile);
            while (inputFile.hasNext()) {
                contents.append(inputFile.nextLine());
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }

        return contents.toString();
    }
}