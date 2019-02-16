/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.pinyinsort.util;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/02/15 13:49
 * @since 1.0
 */
public class Record implements Comparable<Record> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Record o) {
        return name.compareTo(o.name);
    }
}
