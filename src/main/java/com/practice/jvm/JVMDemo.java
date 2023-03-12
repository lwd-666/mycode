package com.practice.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor soft
 * 2023/3/12
 */
public class JVMDemo {
    public static void main(String[] args) {
        List<Pig> list = new ArrayList<>();
        while (true){
            list.add(new Pig());
        }
    }
}
//-Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\myDump
class Pig{
    byte[] aByte = new byte[1*1024*1024];
}
