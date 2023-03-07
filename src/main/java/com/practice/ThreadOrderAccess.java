package com.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ResourceTest {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (flag!=1){
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            flag = 2;
            condition2.signal();
            lock.unlock();
        }

    }
    public void print10(){
        lock.lock();
        try {
            while (flag!=2){
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            flag = 3;
            condition3.signal();
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            while (flag!=3){
                condition2.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @autor soft
 * 2023/3/7
 * 多线程之间按顺序调用，实现A->B->C
 * new3个condition，每个线程执行成功后调用下个下个condition的signal()方法
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        //AA打印5次，BB打印10次，CC打印15次
        ResourceTest resourceTest = new ResourceTest();
        new Thread(()->resourceTest.print5(), "A").start();
        new Thread(()->resourceTest.print10(),"B").start();
        new Thread(()->resourceTest.print15(),"C").start();
    }
}
