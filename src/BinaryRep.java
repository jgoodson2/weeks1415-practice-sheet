import java.util.Scanner;

public class BinaryRep {

    public static void main(String[] args) {
        int num;
        String binString = "";

        num = getPositiveInt("Please enter a positive integer: ");
        System.out.println("Binary representation = " + getBinaryString(num));
    }

    private static String getBinaryString(int num) {
        String binRep = "";
        char newChar;

        do {
            newChar = (num % 2 > 0) ? '1' : '0';
            binRep = newChar + binRep;
            num /= 2;
        } while (num > 0);

        return binRep;
    }

    private static int getPositiveInt(String prompt) {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        boolean isValid = false;
        do {
            System.out.println(prompt);
            if (!scan.hasNextInt()) {
                System.out.println("Error: not an integer.");
                scan.nextLine();//clear the scanner
            } else {
                input = scan.nextInt();
                if (input <= 0) {
                    System.out.println("Error: input is not positive.");
                } else {
                    isValid = true;
                }
            }
        } while (!isValid);
        return input;
    }
}
