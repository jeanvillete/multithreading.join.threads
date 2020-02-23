package multithreading.learning;

import java.math.BigInteger;

class FactorialThread extends Thread {
    private long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
        this.result = factorial(inputNumber);
        this.isFinished = true;
    }

    private BigInteger factorial(long inputNumber) {
        BigInteger tempResult = BigInteger.ONE;

        for (long i = inputNumber; i > 0; i--) {
            tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
        }

        return tempResult;
    }

    BigInteger getResult() {
        return result;
    }

    boolean isFinished() {
        return isFinished;
    }
}
