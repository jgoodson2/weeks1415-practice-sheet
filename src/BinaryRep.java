import java.util.Scanner;
import java.util.Stack;

public class BinaryRep {

    public static void main(String[] args) {
        int num;
//        String binString = "";

        num = getPositiveInt();
        System.out.println("Binary representation = " + getBinaryString(num));
    }

    private static String getBinaryString(int num) {
        Stack st = new Stack();
        StringBuilder binRep = new StringBuilder();
        char newChar;

        do {
            newChar = (num % 2 > 0) ? '1' : '0';
            st.push(newChar);
            num /= 2;
        } while (num > 0);

        while (!st.empty()) {
            binRep.append(st.pop());
        }

        return binRep.toString();
    }

    private static int getPositiveInt() {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        boolean isValid = false;
        do {
            System.out.println("Please enter a positive integer: ");
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
