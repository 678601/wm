package com.example.demo.utils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author LiWenMing
 * @Description TODO
 * @createTime 2021/1/6/0006 21:06
 */
public class TestMap {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<>();
        Person person = new Person("Li",1);
        Person person1 = new Person("Li",2);
        boolean b = set.add(person);
        boolean b1 = set.add(person1);
        System.out.printf(b+"--"+b1);
        System.out.printf(set.toString());
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        hashMap.put("1","2");
        System.out.println(hashMap);
    }
}

class Person{
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
