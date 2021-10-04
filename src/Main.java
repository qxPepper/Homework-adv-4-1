public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();
        final int COUNT_SPECIALISTS = 5;

        Thread ATS = new Thread(callCenter::workOfATS, "АТС");

        ATS.start();

        for (int i = 1; i <= COUNT_SPECIALISTS; i++) {
            String specialist = "Специалист_" + i;
            new Thread(callCenter::workOfSpecialist, specialist).start();
        }

        ATS.join();
    }
}