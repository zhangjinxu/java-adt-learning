package com.zjx.learning;

import org.junit.Test;

import java.util.List;

public class TestMyArrayList {

    @Test
    public void testAdd() {
        List<Integer> list = new MyArrayList<>();
        System.out.println("init size:"+list.size());
        for (int i = 0; i < 21; i++) {
            list.add(i);
        }

        System.out.println(list);

    }

    @Test
    public void testRemove() {

    }
}
