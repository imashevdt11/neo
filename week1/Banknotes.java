import java.util.Scanner;

public class Banknotes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n);
        int numberOfBanknotes = 0;

        for (int i = 100; i >= 1; i /= 2) {
            if (i == 25) i = 20;
            if (n >= i) {
                numberOfBanknotes = n / i;
                n = n % i;
            }
            System.out.println(numberOfBanknotes + " nota(s) de R$ " + i + ",00");
            numberOfBanknotes = 0;
        }
        scanner.close();
    }
}