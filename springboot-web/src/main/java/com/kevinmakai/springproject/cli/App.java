package com.kevinmakai.springproject.cli;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static boolean  isSplitPoolKey(String key){
        String regex = "^([a-zA-Z_]+)_nosplit#([0-9_]*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(key);
        if(matcher.matches()){
            return false;
        }else{
            return true;
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str1 = "8-8";
//String regex = "^([0-9]+)-([0-9]+)$";
//        Pattern cursorPattern1 = Pattern.compile(regex);
//        Matcher matcher2 = cursorPattern1.matcher(str1);
//matcher2.find();
//System.out.println(matcher2.group(1));
//
//         String cursorPatternStr = "^[0-9]$";
//         String FINISHED_CURSOR = "8";
//        Pattern cursorPattern = Pattern.compile(cursorPatternStr);
//        String string = "0-0 5461-0";
//        Matcher matcher = cursorPattern.matcher(FINISHED_CURSOR);
//        matcher.find();
//        System.out.println(matcher.group());
//        byte[] ss = SafeEncoder.encode("test:" + string);
//        System.out.println(new String(ss, "utf-8"));
//        Integer concurrent = 3;
//        final AtomicInteger countdown = new AtomicInteger(concurrent);
//        for (int theadNum = 0; theadNum < concurrent; theadNum++) {
//            int step = 16384 / concurrent;
//
//            String startCursor = theadNum * step + "-0";
//            String endCursor = ((theadNum == concurrent - 1) ? 0 : ((theadNum + 1) * step)) + "-0";
//
//            System.out.println(startCursor +" " + endCursor);
//
//        }
//
//        String str = "Wor-ld!";
//        Pattern pattern = Pattern.compile("W(or)-(ld!)");
//        Matcher matcher1 = pattern.matcher(str);
//        while(matcher1.find()){
//            System.out.println("Group 0:"+matcher1.group(0));//得到第0组——整个匹配
//            System.out.println("Group 1:"+matcher1.group(1));//得到第一组匹配——与(or)匹配的
//            System.out.println("Group 2:"+matcher1.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式
//            System.out.println("Start 0:"+matcher1.start(0)+" End 0:"+matcher1.end(0));//总匹配的索引
//            System.out.println("Start 1:"+matcher1.start(1)+" End 1:"+matcher1.end(1));//第一组匹配的索引
//            System.out.println("Start 2:"+matcher1.start(2)+" End 2:"+matcher1.end(2));//第二组匹配的索引
//            System.out.println(str.substring(matcher1.start(0),matcher1.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
//        }
//        String ssa = "test_123";
//        System.out.println(ssa.indexOf("test"));

        String regex = "^([a-zA-Z_]+)#([0-9]+)_([0-9]*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("rx_redis_pool_with_score_hospital_nosplit#70_2");
        matcher.matches();
        System.out.println(matcher.group());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));

        System.out.println(isSplitPoolKey("rx_redis_pool_with_score_hospital_nosplit#70_1000"));

        List<String> rxIdList = new ArrayList<>(5);
        System.out.println(rxIdList.size());

//        System.out.println("0".substring(0,"0".indexOf("-")));

        Set<String> set = new HashSet<>();
        set.add(null);
        System.out.println("size:"+ set.size());
    }
}
