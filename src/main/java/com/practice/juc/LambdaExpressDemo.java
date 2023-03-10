package com.practice.juc;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor soft
 * 2023/3/4
 */
@FunctionalInterface
interface Foo{
//    void sayHello(int x, int y);
    int put(int x,int y);
    List<String> list = new ArrayList<>();
    default int sub(int x,int y){
        return x-y;
    }
    static int div(int x,int y){
        return x/y;
    }
}
public class LambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = (x,y)-> System.out.println("sayHello"+x+y);
//        foo.sayHello(2,3);
        Foo foo = ((x, y) -> {
            System.out.println(x + y);
            return x+y;
        });
//        foo.put(1,2);
//        System.out.println(foo.sub(2,3));
        System.out.println(Foo.div(4, 2));
    }
}
