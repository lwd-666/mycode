package com.practice;

class Ticket{
    private int num = 50;
    public synchronized void sell(){
        if (num>0)
            System.out.println(Thread.currentThread().getName() + num); num--;
    };
}
public class SaleTicketDemo extends Thread{
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"A").start();
        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"B").start();
        new Thread(()-> {for (int i = 1; i < 51; i++) ticket.sell();},"C").start();
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