package com.practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @autor soft
 * 2023/3/8
 *
 * Callable接口相比Runnable接口
 *      1.有返回值   2.能抛出异常   3.支持泛型
 * 通过FutureTask类的构造时需要传入Callable接口
 */
class MyThread1 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
class MyThread2 implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"===>B");
        return "B";
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        FutureTask futureTask = new FutureTask(myThread2);
        new Thread(myThread1,"A").start();
        new Thread(futureTask,"B").start();
        new Thread(()->{
            try {
                myThread2.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"C").start();

//        System.out.println(futureTask.get());
    }
}
