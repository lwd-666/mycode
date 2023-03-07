package com.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @autor soft
 * 2023/3/6
 */
public class SynchronizedVSLockDemo {
    ReentrantLock lock = new ReentrantLock(true);
    public void m1(){
        try {
            if (lock.tryLock(5L,TimeUnit.SECONDS)){
                try {
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }else {
                System.out.println(Thread.currentThread().getName()+"抢锁失败");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        SynchronizedVSLockDemo synchronizedVSLockDemo = new SynchronizedVSLockDemo();
        new Thread(()->{
            synchronizedVSLockDemo.m1();
            synchronizedVSLockDemo.m1();
            synchronizedVSLockDemo.m1();
        },"A").start();
        new Thread(()->{
            synchronizedVSLockDemo.m1();
            synchronizedVSLockDemo.m1();
            synchronizedVSLockDemo.m1();
        },"B").start();
    }
}
