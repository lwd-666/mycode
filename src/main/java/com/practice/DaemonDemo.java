package com.practice;

/**
 * @autor soft
 * 2023/3/9
 */
public class DaemonDemo {
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        Thread t1 =new Thread(()->{
            System.out.println("t1是"+(Thread.currentThread().isDaemon()?"守护线程":"用户线程"));
            while (true){

            }
        },"A");
        t1.setDaemon(true);
        t1.start();

        System.out.println("主线程用户线程结束");
    }
}
