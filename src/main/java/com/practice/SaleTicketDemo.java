package com.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Ticket{
    private int num = 50;
    public synchronized void sell(){
        if (num>0) {
            --num;
            System.out.println(Thread.currentThread().getName() + "," + num);
        }
    }
}
public class SaleTicketDemo extends Thread{
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <= 50; i++) {
                int finalI = i;
                threadPool.submit(()->{
//                    System.out.println(Thread.currentThread().getName()+":卖出了第"+ finalI+"张票");
                    ticket.sell();
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            threadPool.shutdown();
        }


//        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"A").start();
//        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"B").start();
//        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"C").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 51; i++) {
//                    ticket.sell();
//                }
//            }
//        },"A").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 51; i++) {
//                    ticket.sell();
//                }
//            }
//        },"B").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 51; i++) {
//                    ticket.sell();
//                }
//            }
//        },"C").start();
    }
}