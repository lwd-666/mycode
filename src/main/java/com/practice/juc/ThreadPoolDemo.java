package com.practice.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        for (int i = 1; i <= 50; i++) {
            cachedThreadPool.submit(()->{
                bank.deal();
            },String.valueOf(i));
        }


/*        for (int i = 1; i <= 50; i++) {
            fixedThreadPool.submit(()->{
                bank.deal();
            },String.valueOf(i));
        }*/
    }
}
