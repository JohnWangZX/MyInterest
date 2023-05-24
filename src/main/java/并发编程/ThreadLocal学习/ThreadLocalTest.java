package 并发编程.ThreadLocal学习;

/**
 * @author: wzx
 * @date: 2023/5/22
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> local = new ThreadLocal<String>();
    private static ThreadLocal<String> name =new ThreadLocal<String>();

    static void print(String str) {
        //打印当前线程中本地内存中变量的值
        System.out.println(str + " :" + local.get());
        //清除内存中的本地变量
        local.remove();
    }
    static void printName(){
        System.out.println(name.get());
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                ThreadLocalTest.local.set("xdclass_A");
                ThreadLocalTest.name.set("范龙辉");
                print("A");
                printName();
                //打印本地变量
                System.out.println("清除后：" + local.get());
            }
        },"A").start();
        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                ThreadLocalTest.local.set("xdclass_B");
                ThreadLocalTest.name.set("范龙辉的爸爸wzx");
                print("B");
                printName();
                System.out.println("清除后 " + local.get());
            }
        },"B").start();
    }
}

