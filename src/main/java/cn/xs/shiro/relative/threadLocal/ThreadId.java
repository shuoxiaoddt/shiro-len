package cn.xs.shiro.relative.threadLocal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by uwayxs on 2017/11/23.
 */
public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };
    public static int get() {
         return threadId.get();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0 ; i < 10 ; i++){
            new Thread(() ->{
                System.out.println(ThreadId.get());
            }).start();
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
