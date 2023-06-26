package 并发编程.线程池学习;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wzx
 * @date: 2023/6/21
 */
public class PrintFlh {
    public static void main(String[] args) {
        AtomicInteger counter=new AtomicInteger(0);
        MyThread myThread1= new MyThread(counter, 0);
        MyThread myThread2= new MyThread(counter, 1);
        MyThread myThread3= new MyThread(counter, 2);
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
    public static class  MyThread extends Thread{
        private AtomicInteger num;
        private int key;
        private static final HashMap<Integer,Character> map=new HashMap<>();
        static {
            map.put(0,'f');
            map.put(1,'l');
            map.put(2,'h');
        }

        MyThread(AtomicInteger num,int key){
            this.num=num;
            this.key=key;
        }
        @Override
        public void run() {
            while(true) {
                if (num.get() % 3 == key) {
                    System.out.println(map.get(key));
                    num.incrementAndGet();
                }
            }
        }
    }
}
