import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CallCenter {
    private final Queue<String> queue;
    private volatile AtomicBoolean working;
    private volatile AtomicInteger countAnswers;

    private final static int MAX_COUNT_CALLS = 20;
    private final static int TIME_ATS_SLEEP = 500;
    private final static int TIME_SPECIALIST_WAIT = 500;
    private final static int TIME_SPECIALIST_WORKS = 4000;

    public CallCenter() {
        queue = new ConcurrentLinkedQueue<String>();
        working = new AtomicBoolean(true);
        countAnswers = new AtomicInteger(0);
    }

    public void workOfATS() {
        String bell;
        System.out.println("С АТС начали поступать звонки.");
        for (int i = 0; i < MAX_COUNT_CALLS; i++) {
            bell = "Hallo!";
            System.out.println(bell);
            queue.add(bell);

            try {
                Thread.sleep(TIME_ATS_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Звонки с АТС закончились.");
    }

    public void workOfSpecialist() {
        System.out.println(Thread.currentThread().getName() + " подключился.");
        try {
            while (countAnswers.get() < MAX_COUNT_CALLS) {
                Thread.sleep(TIME_SPECIALIST_WAIT);

                if (queue.size() > 0 && working.get()) {
                    System.out.println(Thread.currentThread().getName() + " принял звонок.");
                    queue.poll();

                    Thread.sleep(TIME_SPECIALIST_WORKS);

                    System.out.println(Thread.currentThread().getName() + " ответил на звонок.");
                    countAnswers.getAndIncrement();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (countAnswers.get() == MAX_COUNT_CALLS) {
            working.set(false);
        }
    }
}