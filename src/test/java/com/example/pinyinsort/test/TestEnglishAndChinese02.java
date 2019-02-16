/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.pinyinsort.test;

import com.example.pinyinsort.model.PersonBean;
import com.example.pinyinsort.util.PinyinComparator;
import com.ibm.icu.text.Collator;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/02/16 14:08
 * @since 1.0
 */
public class TestEnglishAndChinese02 {
    //主函数
    public static void main(String[] args) {
        //第一类根据数组中首字母的排序
//      String data[] = new String[]{"王二麻子","张三","李四","阿一","田七","AAA"};
//      data = sort(data);
//      for(String val : data){
//          System.out.println(val);
//      }

        //第二类根据集合对象的某个属性的首字母排序
        List<PersonBean> list = new ArrayList<PersonBean>();
        PersonBean person1 = new PersonBean();
        person1.setId(1);
        person1.setName("张三");
        PersonBean person2 = new PersonBean();
        person2.setId(2);
        person2.setName("王二麻子");
        PersonBean person3 = new PersonBean();
        person3.setId(3);
        person3.setName("阿一");
        PersonBean person4 = new PersonBean();
        person4.setId(4);
        person4.setName("田七");
        PersonBean person5 = new PersonBean();
        person5.setId(5);
        person5.setName("ABHH");
        PersonBean person6 = new PersonBean();
        person6.setId(6);
        person6.setName("CSHSH");
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);
        System.out.println("排序前");
        for(PersonBean person : list){
            System.out.println(person.getId()+"  "+person.getName());
        }


        list = listToSortByName(list);


        System.out.println("排序后");
        for(PersonBean person : list){
            System.out.println(person.getId()+"  "+person.getName());
        }

    }

    /**
     * 根据数组里面首字母排序
     * @param data
     * @return
     */
    public static String[] sort(String [] data){
        if(data==null || data.length==0){
            return null;
        }
        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(data, comparator);
        return data;
    }


    /**
     * @TODO 将一个装有person对象的 list 根据name 首字母排序
     * @param List 排序前的数据源
     * @return list排序后的数据
     */
    public static List<PersonBean> listToSortByName(List<PersonBean> list){
        if(list==null || list.size()==0){
            return null;
        }
        Map<String, PersonBean> map = new HashMap<String, PersonBean>();
        String names[] = new String[list.size()];
        for(int i=0;i<list.size();i++){
            String name = list.get(i).getName();
            String alphabet = name.substring(0, 1);
            /*判断首字符是否为中文，如果是中文便将首字符拼音的首字母和&符号加在字符串前面*/
            if (alphabet.matches("[\\u4e00-\\u9fa5]+")) {
                name = getAlphabet(name) + "&" + name;
                names[i] = name;
            }else{
                names[i]=name;
            }
            //names[i] = name;
            map.put(name, list.get(i));
        }
        names = sort(names);
        list.clear();
        for(String name : names){
            if(map.containsKey(name))
                list.add(map.get(name));
        }
        return list;
    }

    /**
     * 调用汉子首字母转化为拼音的根据类，，需要在项目中导入pinyin4j.jar包
     * @param str
     * @return
     */
    public static String getAlphabet(String str) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyin = null;
        try {
            pinyin = (String) PinyinHelper.toHanyuPinyinStringArray(str.charAt(0), defaultFormat)[0];
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pinyin.substring(0, 1);
    }

}