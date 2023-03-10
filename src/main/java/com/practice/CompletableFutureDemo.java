package com.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student{
    private String name;
    private Integer age;
    private String major;
}

/**
 * @autor soft
 * 2023/3/9
 * 1.CompletableFuture的默认线程池 会随着主线程关闭而自动关闭，
 *      使当前CompletableFuture的线程池未正常运行结束便关闭，
 *      除非使用get()方法阻塞或主线程sleep()拖延，
 * 正常情况下使用 自定义线程池，可控制线程池关闭顺序
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = null;
        try {
             completableFuture= CompletableFuture.supplyAsync(()->{
                int result = ThreadLocalRandom.current().nextInt(10);
                System.out.println(Thread.currentThread().getName()+"=================启动");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                int a = 10/0;
                Student student = new Student();
                student.setAge(22).setName("李德翔").setMajor("体育专业");
                System.out.println(Thread.currentThread().getName()+"=================result："+result);
                return result;
            },threadPool).whenComplete((v,e)->{
                if (e==null) System.out.println("得到的返回值："+v);
            }).exceptionally(e->{
                e.printStackTrace();
                System.out.println("异常情况："+e.getCause());
                return null;
            });
        }finally {
            threadPool.shutdown();
        }
        System.out.println(Thread.currentThread().getName()+"结束");
        System.out.println(completableFuture.join());
//        m2();
//        m1();
/*        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },executorService);
        System.out.println(completableFuture.get());*/
    }

    private static void m2() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            int result = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread().getName()+"=================启动");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"=================result："+result);
            return result;
        }).whenComplete((v,e)->{
            if (e==null) System.out.println("得到的返回值："+v);
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println("异常情况："+e.getCause());
            return null;
        });
        System.out.println(Thread.currentThread().getName()+"获得的result是"+completableFuture.get());
        System.out.println(Thread.currentThread().getName()+"忙于其他事");
    }

    private static void m1() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("hello supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "supplyAsync over";
        },threadPool);

        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
}
