import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    private Queue<String> queue;
    private boolean working;
    private volatile static int countAnswers;

    private final static int MAX_COUNT_CALLS = 20;
    private final static int MAX_COUNT_SPECIALIST = 100;
    private final static int TIME_ATS_SLEEP = 1000;
    private final static int TIME_SPECIALIST_SLEEP = 4000;

    public CallCenter() {
        queue = new ConcurrentLinkedQueue<String>();
        working = true;
        System.out.println("Колл-центр начал работать.");
    }

    public void workOfATS() {
        String bell;
        try {
            System.out.println("С АТС начали поступать звонки.");
            for (int i = 0; i < MAX_COUNT_CALLS; i++) {
                bell = "Hallo!";
                System.out.println(bell);
                queue.add(bell);
                Thread.sleep(TIME_ATS_SLEEP);
            }
            System.out.println("Звонки с АТС закончились.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void workOfSpecialist() {
        try {
            System.out.println(Thread.currentThread().getName() + " подключился.");
            for (int i = 0; i < MAX_COUNT_SPECIALIST; i++) {
                if (countAnswers == MAX_COUNT_CALLS) {
                    working = false;
                    System.out.println("Колл-центр заканчивает работу работать.");
                    break;
                }

                if (queue.size() > 0 && working) {
                    System.out.println(Thread.currentThread().getName() + " принял звонок.");
                    queue.poll();
                    Thread.sleep(TIME_SPECIALIST_SLEEP);
                    System.out.println(Thread.currentThread().getName() + " ответил на звонок.");
                    countAnswers++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}