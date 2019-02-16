/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.pinyinsort.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/02/16 15:11
 * @since 1.0
 */
public class Duoyinzitest {


    public static void main(String[] args) {
        String[] banks = {"中国银行", "重庆银行", "上海浦东发展银行", "兴业银行","ha哈尔滨银行","ao奥迪","长安","长安轿车","长城","JMC","开瑞"};

        Arrays.sort(banks, new Comparator<String>() {

            HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();

            @Override
            public int compare(String bank1, String bank2) {
                try {

                    bank1 = PinyinHelper.toHanYuPinyinString(bank1, pinyinOutputFormat, " ", true);
                    bank2 = PinyinHelper.toHanYuPinyinString(bank2, pinyinOutputFormat, " ", true);


                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                return bank1.compareTo(bank2);
            }
        });
        System.out.println(Arrays.toString(banks));
    }
}
