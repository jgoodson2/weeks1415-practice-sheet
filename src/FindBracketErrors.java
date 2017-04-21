import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FindBracketErrors {

    public static void main(String[] args) {

        StringBuilder fileContents = new StringBuilder();
        String fileName = "code.txt";
        String filePath = new File("").getAbsolutePath() + "/" + fileName;
        File myFile = new File(filePath);
        Scanner inputFile;

        System.out.println("filePath = " + filePath);

        try {
            inputFile = new Scanner(myFile);

            while (inputFile.hasNext()) {
                fileContents.append(inputFile.nextLine());
            }
        } catch (FileNotFoundException fnf) {
            //display total of 0
            System.out.println(fnf.getMessage());
        }
        System.out.println("fileContents = " + fileContents);
    }
}