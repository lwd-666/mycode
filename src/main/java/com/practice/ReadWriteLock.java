package com.practice;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private static Map<String,String> map = new HashMap();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void write(String k,String v){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"准备写入"+k);
        map.put(k,v);
        System.out.println(Thread.currentThread().getName()+"已经写入"+k);
        readWriteLock.writeLock().unlock();
    }
    public void read(String k){
        readWriteLock.readLock().lock();
        readWriteLock.readLock().tryLock();
        System.out.println(Thread.currentThread().getName()+"============准备读取"+k);
        map.get(k);
        System.out.println(Thread.currentThread().getName()+"============已经读取"+k);
        readWriteLock.readLock().unlock();

    }
}
/**
 * @autor soft
 * 2023/3/8
 *  ReentrantReadWriteLock读写锁
 *  写锁时保证原子性
 *  读锁可一起读
 *
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.write(finalI +"",finalI+"");
            },"A").start();
        }
        for (int i = 10; i > 0; i--) {
            int finalI = i;
            new Thread(()->{
                myCache.read(finalI +"");
            },"A").start();
        }
    }
}
