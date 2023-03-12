package com.practice.jvm;

/**
 * @autor soft
 * 2023/3/12
 */
public class MemorryAllocateDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory()/(double)1024/1024+"MB");
        System.out.println(Runtime.getRuntime().maxMemory()/(double)1024/1024+"MB");
        System.out.println(Runtime.getRuntime().freeMemory()/(double)1024/1024+"MB");
    }
}
