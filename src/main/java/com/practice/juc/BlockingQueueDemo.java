package com.practice.juc;

import java.util.Collections;
import java.util.concurrent.*;

/**
 * @autor soft
 * 2023/3/8
 *
 * BlockingQueue阻塞队列
 * Collection<E>接口 -> Queue<E>接口 -> BlockingQueue<E>接口
 *
 * 常用实现类有
 *      ArrayBlockingQueue：数组组成的有界阻塞
 *      LinkedBlockingQueue：链表组成的有界阻塞（21亿默认无界）
 *      LinkedBlockingDeque：链表组成的双向阻塞队列
 *      SynchronousQueue：单个元素的队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> linkedBlockingDeque = new LinkedBlockingDeque<>();
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("c", 2, TimeUnit.SECONDS));

/*        arrayBlockingQueue.put("a");
        System.out.println(arrayBlockingQueue.take());
        arrayBlockingQueue.put("b");
        System.out.println(arrayBlockingQueue.take());
        arrayBlockingQueue.put("c");
        arrayBlockingQueue.put("d");
        System.out.println(arrayBlockingQueue.take());*/

/*        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());*/

/*        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
//        System.out.println(arrayBlockingQueue.add("d"));
        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());*/



    }
}
