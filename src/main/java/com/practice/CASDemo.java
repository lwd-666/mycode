package com.practice;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *     public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *         return var5;
 *     }
 *     Unsafe类可以让java操作内存地址，与指针类似
 *     所以可以在不加锁的情况下具有原子性
 *     根据 当前对象var1，该对象的内存地址var2 可以得到对象当前值var5
 *     再用while根据v1，v2在判断当前值是否是var5
 *     相同则替换为var5+var4的值
 *     不同则在做循环（自旋）消耗性能
 */
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
