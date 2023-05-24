package 并发编程.线程池学习;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: wzx
 * @date: 2023/5/24
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        GetCouple couple=new GetCoupleImpl();
        Map<String,String> res=couple.getCouple();
        Iterator<Map.Entry<String,String>> entries=res.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,String> entry=entries.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
