package com.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @autor soft
 * 2023/3/7
 * 多个线程争抢加减两种方法，在保证方法轮询的前提下运行
 * 使用ReentrantLock可重入锁，替换了synchronized
 * await和signalAll替换了wait和notifyAll方法
 * 加减方法中使用 while对条件不停的重复判断
 */
class ResourceCondition{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void add(){
        lock.lock();
        while (number!=0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+"=======>"+(++number));;
        condition.signalAll();
        lock.unlock();
    }
    public void subtract(){
        lock.lock();
        while (number!=1){
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+"=======>"+(--number));
        condition.signalAll();
        lock.unlock();
    }
}
public class ProdConsumerTest {
    public static void main(String[] args) {
        ResourceCondition resourceCondition = new ResourceCondition();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resourceCondition.add();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resourceCondition.subtract();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resourceCondition.add();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resourceCondition.subtract();
            }
        },"D").start();
    }
}
