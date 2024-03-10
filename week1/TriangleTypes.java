import java.util.Arrays;
import java.util.Scanner;

public class TriangleTypes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] abc = new double[3];

        for (int i = 0; i < 3; i++) {
            abc[i] = scanner.nextFloat();
        }
        Arrays.sort(abc);

        double bc = Math.pow(abc[0], 2) + Math.pow(abc[1], 2);
        if (abc[2] >= abc[0] + abc[1]) System.out.println("NAO FORMA TRIANGULO");
        else if (Math.pow(abc[2], 2) > bc) System.out.println("TRIANGULO OBTUSANGULO");
        else if (Math.pow(abc[2], 2) < bc) System.out.println("TRIANGULO ACUTANGULO");
        else if (Math.pow(abc[2], 2) == bc) System.out.println("TRIANGULO RETANGULO");

        if (abc[2] == abc[1] && abc[1] == abc[0]) System.out.println("TRIANGULO EQUILATERO");
        else if (abc[2] == abc[1] || abc[1] == abc[0]) System.out.println("TRIANGULO ISOSCELES");

        scanner.close();
    }
}