package com.practice.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @autor soft
 * 2023/3/7
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("神龙出现，可以许愿");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println("第"+Thread.currentThread().getName()+"颗龙珠到手");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }

    }
}
