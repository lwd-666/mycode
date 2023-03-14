package com.practice.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @autor soft
 * 2023/3/13
 */
class Cat{
    static int a = 1;
    public  void eat(){
        System.out.println("cat eat");
    }
}
class Dog{
    public  void eat(){
        System.out.println("dog eat");
    }
}
public class DeadLockDemo {
    static Object o1 = new Object();
    static Object o2 = new Object();
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(1);

        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        },"A").start();
        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        },"B").start();
    }
}
