import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FindBracketErrors {

    public static void main(String[] args) {


        String fileName = "code.txt";
        String filePath = new File("").getAbsolutePath() + "/" + fileName;
        String fileContents = getFileContents(filePath);

        System.out.println("fileContents = " + fileContents);
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
            //display total of 0
            System.out.println(fnf.getMessage());
        }

        return contents.toString();
    }
}