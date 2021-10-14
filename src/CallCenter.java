import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CallCenter {
    private final Queue<String> queue;
    private AtomicInteger countAnswers;

    private final static int MAX_COUNT_CALLS = 20;
    private final static int TIME_ATS_SLEEP = 500;
    private final static int TIME_SPECIALIST_WORKS = 4000;

    public CallCenter() {
        queue = new ConcurrentLinkedQueue<String>();
        countAnswers = new AtomicInteger(0);
    }

    public void workOfATS() {
        String bell;
        System.out.println("С АТС начали поступать звонки.");
        try {
            for (int i = 0; i < MAX_COUNT_CALLS; i++) {
                bell = "Hallo!";
                System.out.println(bell);
                queue.add(bell);

                Thread.sleep(TIME_ATS_SLEEP);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Звонки с АТС закончились.");
    }

    public void workOfSpecialist() {
        System.out.println(Thread.currentThread().getName() + " подключился.");
        try {
            while (countAnswers.get() < MAX_COUNT_CALLS) {

                while (queue.poll() != null) {
                    System.out.println(Thread.currentThread().getName() + " принял звонок.");

                    Thread.sleep(TIME_SPECIALIST_WORKS);

                    System.out.println(Thread.currentThread().getName() + " ответил на звонок.");

                    countAnswers.getAndIncrement();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
