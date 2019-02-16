/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.pinyinsort.test;
import com.example.pinyinsort.util.PinyinComparator;
import com.ibm.icu.text.Collator;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Comparator;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/02/16 10:40
 * @since 1.0
 */
public class testEnglishAndChinese {
    public int compare(String o1, String o2) {
        for (int i = 0; i < o1.length() && i < o2.length(); i++) {
            // 逐个获取字母
            char codePoint1 = o1.charAt(i);
            char codePoint2 = o2.charAt(i);
            // 确定字符是否在增补字符范围内,在范围内则跳过(确定指定字符是否为Unicode空白字符)
            if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
                // 如果不相等则返回比较结果
                if (codePoint1 != codePoint2) {
                    return codePoint1 - codePoint2;
                } else {// 相等则比较下一个
                    continue;
                }
            }
            //将汉字转换为拼音，不是汉子则为NULL
            String pinyin1 = pinyin(codePoint1);
            String pinyin2 = pinyin(codePoint2);
            //不为汉字则与原英文比较
            if(pinyin1 == null){
                pinyin1 = (codePoint1+"");
            }
            if(pinyin2 == null){
                pinyin2 = (codePoint2+"");
            }
            //忽略大小写比较
            if (!pinyin1.toLowerCase().equals(pinyin2.toLowerCase())) {
                return pinyin1.toLowerCase().compareTo(pinyin2.toLowerCase());
            }else{
                //不忽略大小写比较
                if(!pinyin1.equals(pinyin2)){
                    return pinyin1.compareTo(pinyin2);
                }
            }
        }
        return o1.length() - o2.length();
    }

    public static void main(String[] args) {
   /*     int cp1 = 0x10ffd;
        int cp2 = 0x004ca;
        PinyinComparator com = new PinyinComparator();
        // System.out.println(com.compare("b", "a") < 0);//1>2，则true
        // System.out.println(com.compare("a", "b") < 0);
        System.out.println(com.compare("本", "C") < 0);
        System.out.println(com.compare("a", "A") < 0);*/
        // Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA

        Comparator comparator = Collator.getInstance(java.util.Locale.CHINA);



//          Comparator comparator = Collator.getInstance(java.util.Locale.CHINESE);
        String[] arrStrings = { "1","A","a","Aasfdsaf","乔峰", "郭靖", "Yang","杨过", "张无忌","韦小宝","怡宝" };
        String english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] AZ = english.split("");

        arrStrings =  ArrayUtils.addAll(arrStrings, AZ);
        // 使根据指定比较器产生的顺序对指定对象数组进行排序。
        Arrays.sort(arrStrings, comparator);

        System.out.println(ArrayUtils.toString(arrStrings));

        Arrays.sort(arrStrings, new PinyinComparator());

        System.out.println(ArrayUtils.toString(arrStrings));
    }

    /**
     * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
     */
    private String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyins == null) {
            return null;
        }
        return pinyins[0];
    }

}
