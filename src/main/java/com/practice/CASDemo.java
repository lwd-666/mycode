package com.practice;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class AirTest {
    public static final int COUNT_DOWN = 100;

    AtomicInteger atomicInteger = new AtomicInteger();

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger() {
//        atomicInteger.getAndAdd(1);
        atomicInteger.getAndIncrement();
    }
}

/**
 * @autor soft
 * 2023/3/7
 * 生成100个线程，每个线程对num加100次1
 * AtomicInteger具有原子性，是线程安全的,在多线程情况下使用
 *
 * CAS是 比较并替换，一种乐观锁。
 * 根据内存地址V、预期原值B、实际新值A进行操作
 *      若 V的值等于B，则V的值跟新为A
 *      若不匹配，则不执行任何操作，多个线程同时执行CAS操作只有一个会成功
 */
public class CASDemo {
    public static void main(String[] args) throws InterruptedException {
        AirTest airTest = new AirTest();
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    airTest.setAtomicInteger();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
//        TimeUnit.SECONDS.sleep(2);
        System.out.println(airTest.getAtomicInteger().get());


//        try {
//            countDownLatch.await();
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }finally {
//            System.out.println(airTest.getAtomicInteger().get());
//        }

    }
}
