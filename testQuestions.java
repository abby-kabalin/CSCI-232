public class testQuestions {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j = j * 2) {
                count = count + 1;
                System.out.println("count: " + count + " i: " + i + " j: " + j);
            }
        }
    }
}
