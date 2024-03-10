import java.util.Scanner;

public class GameTimeWithMinutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int startHour = scanner.nextInt();
        int startMinute = scanner.nextInt();
        int endHour = scanner.nextInt();
        int endMinute = scanner.nextInt();
        int numberOfHours = 0;
        int numberOfMinutes = 0;

        if (startHour < endHour) {
            numberOfHours = endHour - startHour;
        } else if (startHour > endHour || startMinute > endMinute) {
            numberOfHours = 24 - startHour + endHour;
        }

        if (startMinute < endMinute) {
            numberOfMinutes = endMinute - startMinute;
        } else if (startMinute == endMinute) {
            if (numberOfHours == 0) numberOfHours = 24;
        } else {
            numberOfMinutes = 60 - startMinute + endMinute;
            numberOfHours--;
        }

        System.out.printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", numberOfHours, numberOfMinutes);
        scanner.close();
    }
}