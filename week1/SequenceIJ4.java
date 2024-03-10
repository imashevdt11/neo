public class SequenceIJ4 {
    public static void main(String[] args) {
        double[] Is = {0, 0.2, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};
        for (double i: Is) {
            for (int j = 1; j <= 3; j++) {
                if (i - Math.floor(i) == 0) System.out.print("I=" + (int)i + " ");
                else System.out.printf("I=%.1f ", i);
                if ((j + i) - Math.floor(j + i) == 0) System.out.println("J=" + ((int) (j + i)));
                else System.out.printf("J=%.1f\n", (j + i));
            }
        }
    }
}