package multithreading.learning;

import java.math.BigInteger;

public class MainComplexCalculation {

    public static void main(String[] args) {
        MainComplexCalculation complexCalculation = new MainComplexCalculation();

        System.out.println(
                complexCalculation.calculateResult(
                        new BigInteger("2"),
                        new BigInteger("10"),
                        new BigInteger("2"),
                        new BigInteger("10")
                )
        );
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;

        PowerCalculatingThread calc1 = new PowerCalculatingThread(base1, power1);
        calc1.setDaemon(true);
        calc1.start();

        PowerCalculatingThread calc2 = new PowerCalculatingThread(base2, power2);
        calc2.setDaemon(true);
        calc2.start();

        try {
            calc1.join(2000);
            calc2.join(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        result = calc1.getResult().add(calc2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            result = base;
            for (BigInteger i = BigInteger.ONE; i.compareTo(power) < 0; i = i.add(new BigInteger("1"))) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}
