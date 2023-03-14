package com.practice.jvm;

import lombok.Data;

/**
 * @autor soft
 * 2023/3/14
 */
@Data
class Person{
    private String name;
    public Person(String name) {
        this.name = name;
    }
}
public class PassValue {
    public static void main(String[] args) {
        int age = 20;
        change1(age);
        System.out.println(age);

        Person person = new Person("张志豪");
        change2(person);
        System.out.println(person.getName());

        String str1 = "123";
        change3(str1);
        System.out.println(str1);

        String str2 = new String("456");
        change4(str2);
        System.out.println(str2);

    }

    private static void change4(String str2) {
        str2 = "XXX";
    }

    private static void change3(String str1) {
        str1 = "XXX";
    }

    private static void change2(Person person) {
        person.setName("XXX");
    }

    private static void change1(int age) {
        age = 30;
    }
}
