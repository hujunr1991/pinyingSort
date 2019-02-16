/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.pinyinsort.test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/02/15 13:51
 * @since 1.0
 */
public class TestSort {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("你");
        list.add("好");
        list.add("啊");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                return com.compare(o1, o2);
            }
        });

        for (String temp : list) {
            System.out.println(temp);
        }
    }

}
