package com;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import scala.collection.Set;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @Author lyuf
 * @Date 2022/1/19 11:41
 * @Version 1.0
 */
public class test {

    @Test
    public void test() {

        HashSet<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        List<String> list2 = new ArrayList<String>();
        list2.add("1");
        list2.add("2");
        list2.add("4");


        // 交集
        List<String> intersection = list1.stream().filter(list2::contains).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        //去重
//        projectDtosResult = projectDtosResult.stream().collect(
//                collectingAndThen(
//                        toCollection(() -> new TreeSet<>(Comparator.comparing(ProjectDto::getId))), ArrayList::new)
//        );

        //
//        Map<String, String> passwordMap = clusterLinkDTO.getNodeList().stream().collect(Collectors.toMap(NodeInfo::getIp, NodeInfo::getPassword));

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out::println);

    }


    @Test
    public void test2() {
        String s = "adsf +safsdf";
        String[] s1 = s.trim().split("");
        Collections.reverse(Arrays.asList(s1));
        System.out.println(String.join(" ", s1));
    }

    public static String test3(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .forEach(stringBuilder::append);
        return stringBuilder.charAt(0) == '0' ? "0" : stringBuilder.toString();
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    @Test
    public void testtt() {
        List<String> strings = Arrays.asList("asdf", "fhhhhh", "adfrhf", "asdfhjj");

        //新建空集合，用来存储查询出来的数据
        JSONArray responseArray = new JSONArray();
        //模糊查询 使用（Pattern、Matcher）
        Pattern pattern = Pattern.compile("asdf");
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (s != null) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {  //matcher.find()-为模糊查询   matcher.matches()-为精确查询
                    responseArray.add(strings.get(i));
                }
            }
        }
        System.out.println(responseArray);

    }

    public static void main(String[] args) {

        String a = "aaaaaaaaaa";
        //trim  删除字符串的头尾空白符
//        System.out.println(a.trim());

//        String s = new String("bbbbb");
//        System.out.println(s.intern());

        String c = a + "";
        // String c = new StringBuilder().append(a).append(null).toString();
//        System.out.println(a + "");
//        System.out.println(a.concat(""));
        boolean a1 = c.matches("a");
        System.out.println(a1);
    }

}
