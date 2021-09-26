public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();

        Thread ATS = new Thread(callCenter::workOfATS, "АТС");

        Thread specialist_1 = new Thread(callCenter::workOfSpecialist, "Специалист_1");
        Thread specialist_2 = new Thread(callCenter::workOfSpecialist, "Специалист_2");
        Thread specialist_3 = new Thread(callCenter::workOfSpecialist, "Специалист_3");
        Thread specialist_4 = new Thread(callCenter::workOfSpecialist, "Специалист_4");
        Thread specialist_5 = new Thread(callCenter::workOfSpecialist, "Специалист_5");

        ATS.start();

        specialist_1.start();
        specialist_2.start();
        specialist_3.start();
        specialist_4.start();
        specialist_5.start();

        ATS.join();
    }
}
