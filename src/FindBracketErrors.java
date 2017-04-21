import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FindBracketErrors {

    public static void main(String[] args) {


        String fileName = "code.txt";
        String filePath = new File("").getAbsolutePath() + "/" + fileName;
        String fileContents = getFileContents(filePath);
        System.out.println("fileContents = " + fileContents);
        System.out.println("All opening and closing bracket counts match = " + allBracketCountsMatch(fileContents));
        /////////
        File myFile = new File(filePath);
        Scanner inputFile;
        int lineCount = 0;
        String line;
        int colCount;
        try {
            inputFile = new Scanner(myFile);
            while (inputFile.hasNext()) {
                //contents.append(inputFile.nextLine());
                line = inputFile.nextLine();
                //System.out.println("Line " + ++lineCount + ": " + line);
                System.out.println("Line " + lineCount++ + ":");
                colCount = 0;
                for (char col : line.toCharArray()) {
                    System.out.println("Col " + colCount++ + ": " + col);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }


    }

    private static boolean allBracketCountsMatch(String st) {

        char[] openers = {'(', '{', '['};
        char[] closers = {')', '}', ']'};
        int[] openerCount = new int[3];
        int[] closerCount = new int[3];

        for (int i = 0; i < openers.length; ++i) {
            for (char ch : st.toCharArray()) {
                if (ch == openers[i]) {
                    openerCount[i]++;
                }
            }
            System.out.println("Number of \'" + openers[i] + "\' = " + openerCount[i]);

            for (char ch : st.toCharArray()) {
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

        System.out.println("filePath = " + filePath);

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