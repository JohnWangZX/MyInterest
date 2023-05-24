package 并发编程.线程池学习;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: wzx
 * @date: 2023/5/24
 */
public class GetCoupleImpl implements GetCouple{
    @Override
    public Map<String, String> getCouple() throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        CountDownLatch countDownLatch=new CountDownLatch(2);
        Map<String,String> map=new HashMap<>();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                GetBride shj=new GetSHJImpl();
                map.put("bride",shj.getBrideName());
                countDownLatch.countDown();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                GetGroom wzx=new GetWZXImpl();
                map.put("groom", wzx.getGroomName());
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        return map;
    }
}
