public class Main {
    private static final int COUNT_SPECIALISTS = 5;

    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter();

        Thread ATS = new Thread(callCenter::workOfATS, "АТС");

        ATS.start();

        for (int i = 1; i <= COUNT_SPECIALISTS; i++) {
            String specialist = "Специалист_" + i;
            new Thread(callCenter::workOfSpecialist, specialist).start();
        }
    }
}
