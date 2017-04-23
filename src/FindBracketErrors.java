import java.io.FileNotFoundException;
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
//        String fileContents = getFileContents(filePath);
        File myFile = new File(filePath);
        evalForBracketErrors(myFile);
    }

    private static void evalForBracketErrors(File file) {
        Stack<Character> bracketStack = new Stack<>();
        Scanner inputFile2;
        int lineCount2 = 0;
        int colCount2;
        String line2;
        String bracketType;
        int bracketTypeIndex;
        char expectedOpener;
        char expectedCloser;
        try {
            inputFile2 = new Scanner(file);
            while (inputFile2.hasNextLine()) {
                lineCount2++;
                line2 = inputFile2.nextLine();
                colCount2 = 0;
                for (char ch : line2.toCharArray()) {
                    colCount2++;

                    if (!getBracketCharType(ch).equals("")) {
                        bracketType = getBracketCharType(ch).toLowerCase().substring(0, openerIndicator.length());
                        bracketTypeIndex = Integer.parseInt(getBracketCharType(ch).substring(openerIndicator.length()));
                        if (bracketType.equals(openerIndicator)) {
                            bracketStack.push(ch);
                        } else if (bracketType.equals(closerIndicator)) {
                            expectedOpener = openers[bracketTypeIndex];
                            if (expectedOpener == bracketStack.peek()) {
                                bracketStack.pop();
                            } else {
                                expectedCloser = closers[Integer.parseInt(getBracketCharType(bracketStack.peek()).substring(openerIndicator.length()))];
                                System.err.printf("Error: '%s' expected at line %d, column %d\n", expectedCloser, lineCount2, colCount2);
                                return;
                            }
                        }
                    }
                }
                System.out.println("No errors.");
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }

    }

    private static void printStack(Stack<Character> stack) {
        StringBuilder stackString = new StringBuilder();
        for (char ch : stack) {
            stackString.append(ch);
        }
        System.out.println("stackString = " + stackString);
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
            System.out.println("Number of \'" + openers[i] + "\' = " + openerCount[i]);

            for (char ch : code.toCharArray()) {
                if (ch == closers[i]) {
                    closerCount[i]++;
                }
            }
            System.out.println("Number of \'" + closers[i] + "\' = " + closerCount[i]);
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