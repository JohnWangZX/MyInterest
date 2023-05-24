package 并发编程.线程池学习;

import java.util.Map;

/**
 * @author: wzx
 * @date: 2023/5/24
 */
public interface GetCouple {
    Map<String,String> getCouple() throws InterruptedException;
}
