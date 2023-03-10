package com.practice.juc;

import java.util.concurrent.*;

class Bank{
    private static int num = 50;
    public synchronized void deal(){
        System.out.println(Thread.currentThread().getName()+"处理"+(num--)+"号");
    }
}
/**
 * @autor soft
 * 2023/3/8
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Bank bank = new Bank();
        custom(bank);
/*        for (int i = 1; i <= 50; i++) {
            cachedThreadPool.submit(()->{
                bank.deal();
            },String.valueOf(i));
        }*/
/*        for (int i = 1; i <= 50; i++) {
            fixedThreadPool.submit(()->{
                bank.deal();
            },String.valueOf(i));
        }*/
    }
    private static void custom(Bank bank) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(()->{
                    bank.deal();
                },String.valueOf(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }


}
