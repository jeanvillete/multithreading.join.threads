package multithreading.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main( String[] args ) {
        List<Long> inputNumbers = Arrays.asList(100000000L, 0L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
        // we want to calculate !0, !3435, !35435, !2324, !4656, !23, !2435, !5566

        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < inputNumbers.size(); i++){
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

}
