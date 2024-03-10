import java.util.Scanner;

public class LogicalSequence2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        int counter = 0;
        for (int i = 1; i <= y; i++) {
            System.out.print(i);
            counter++;
            if (i == y) {
                System.out.println();
                break;
            }
            if (counter == x) {
                System.out.println();
                counter = 0;
            } else {
                System.out.print(" ");
            }
        }
        scanner.close();
    }
}