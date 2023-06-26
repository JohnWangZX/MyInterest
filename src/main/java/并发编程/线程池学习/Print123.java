package 并发编程.线程池学习;

/**
 * @author: wzx
 * @date: 2023/6/15
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Print123 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicInteger counter = new AtomicInteger(1);

        executor.submit(new PrintTask(counter, 0));
        executor.submit(new PrintTask(counter, 1));
        executor.submit(new PrintTask(counter, 2));

        // 关闭线程池
        executor.shutdown();
    }

    static class PrintTask implements Runnable {
        private AtomicInteger counter;
        private int number;

        private static final Map<Integer,Character>map =new HashMap<>();
        static {
            map.put(0,'w');
            map.put(1,'z');
            map.put(2,'x');

        }


        public PrintTask(AtomicInteger counter, int number) {
            this.counter = counter;
            this.number = number;
        }

        @Override
        public void run() {
            while (true) {
                if (counter.get() %3 == number) {
                    System.out.println(map.get(number));
                    counter.incrementAndGet();
                    if (counter.get() > 3) {
                        counter.set(1);
                    }
                }
            }
        }
    }
}

