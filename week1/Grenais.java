import java.util.Scanner;

public class Grenais {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int interWins = 0;
        int gremioWins = 0;
        int answerPrintCount = 0;
        int answer;
        do {
            int amountOfInterGoals = scanner.nextInt();
            int amountOfGremioGoals = scanner.nextInt();
            if (amountOfGremioGoals > amountOfInterGoals)
                gremioWins++;
            else if (amountOfGremioGoals < amountOfInterGoals)
                interWins++;
            System.out.println("Novo grenal (1-sim 2-nao)");
            answer = scanner.nextInt();
            answerPrintCount++;
        } while (answer == 1);

        System.out.println(answerPrintCount + " grenais");
        System.out.println("Inter:" + interWins);
        System.out.println("Gremio:" + gremioWins);
        System.out.println("Empates:" + (answerPrintCount - (gremioWins + interWins)));

        if (interWins > gremioWins)
            System.out.println("Inter venceu mais");
        else if (interWins < gremioWins)
            System.out.println("Gremio venceu mais");
        else
            System.out.println("Não houve vencedor");

        scanner.close();
    }
}