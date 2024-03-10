import java.util.Scanner;

public class WeightedAverages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            double[] numbers = new double[3];
            for (int j = 0; j < 3; j++) {
                numbers[j] = scanner.nextDouble();
            }
            double result = ((numbers[0] * 2) + (numbers[1] * 3) + (numbers[2] * 5)) / 10.0; // Вычисление взвешенного среднего
            System.out.printf("%.1f%n", result);
        }
        scanner.close();
    }
}