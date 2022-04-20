package com.es;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.elasticsearch.client.eql.EqlSearchResponse;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lyuf
 * @Date 2022/3/14 15:56
 * @Version 1.0
 */
public class queryparserDemo {

    @Test
    public void test() {

        ArrayList<String> strings = new ArrayList<>();

        String str = "Hello,World! in Java.1";
        Pattern pattern = Pattern.compile("W(or)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("Group 0:" + matcher.group(0));//得到第0组——整个匹配
            String replace = str.replace(matcher.group(0), "{id}");
            System.out.println(replace);
//            System.out.println("Group 1:" + matcher.group(1));//得到第一组匹配——与(or)匹配的
//            System.out.println("Group 2:" + matcher.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式
//            System.out.println("Start 0:" + matcher.start(0) + " End 0:" + matcher.end(0));//总匹配的索引
//            System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));//第一组匹配的索引
//            System.out.println("Start 2:" + matcher.start(2) + " End 2:" + matcher.end(2));//第二组匹配的索引
//            System.out.println(str.substring(matcher.start(0), matcher.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
        }
    }
//        strings.add("1");
//        strings.add("2");
//        strings.add("3");
//        strings.add("4");
//        strings.add("5");
//        for (int i = 0; i < 10; i++) {
//           strings.add("1");
//           strings.add("2");
//        }
//        System.out.println(strings);
//
//        ArrayList afterList = new ArrayList(new HashSet(strings));
//        System.out.println(afterList);
//        strings.stream().anyMatch(s -> {
//            for (int i = 0; i < 6; i++) {
//                if (s.equals("1")) {
//                    return false;
//                }
//                if (i == 5) {
//                    return true;
//                }
//                System.out.println(s);
//            }
//            return false;
//        });


//        Iterator<String> iterator = strings.iterator();
//
//        while(iterator.hasNext()){
////            if (iterator.next().equals("3")) {
////                iterator.remove();
////            }
//            System.out.println(iterator.next());
//
//        }
//        long now = new Date().getTime();
//        System.out.println(now);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("一个小时前的时间：" + calendar.getTime().getTime());
//        System.out.println("当前的时间：" + new Date().getTime());

//        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println(nowTime);
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - 12);
//        String beforTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
//        System.out.println(beforTime);


//        QueryParser parser=new QueryParser("context",new StandardAnalyzer());
//
//        Query q=parser.parse("太阳");

//        String escape = QueryParser.escape("1-1");
//        System.out.println(escape);
//        IndexSearcher searcher=new IndexSearcher(indexpath);
//
//        EqlSearchResponse.Hits hit=searcher.search(q);
}

