import java.util.Scanner;

public class BanknotesAndCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float n = scanner.nextFloat();
        System.out.println("NOTAS:");
        int[] banknotes = {100, 50, 20, 10, 5, 2};

        for (int note : banknotes) {
            int numberOfBanknotes = (int) (n / note);
            n %= note;
            System.out.println(numberOfBanknotes + " nota(s) de R$ " + note + ".00");
        }

        System.out.println("MOEDAS:");
        n = Math.round(n * 100);
        int[] coins = {100, 50, 25, 10, 5, 1};
        for (int coin : coins) {
            int numberOfCoins = (int) (n / coin);
            n %= coin;
            System.out.printf("%d moeda(s) de R$ %.2f\n", numberOfCoins, coin / 100.0);
        }
        scanner.close();
    }
}