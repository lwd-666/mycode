package com.practice.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @autor soft
 * 2023/3/7
 * 信号量，可做限流
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("第" + Thread.currentThread().getName() + "停好了车");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("第" + Thread.currentThread().getName() + "开走了车");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }
}
