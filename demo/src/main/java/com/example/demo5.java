package com.example;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.*;
import java.util.stream.Stream;

public class demo5 {
    public static Character GetRandomChar(char c1, char c2) {
        Character result = (char)(c1 + Math.random() * (c2 - c1 + 1));
        return result;
    }

    //获取随机array
    public static Integer[] GetRandomArray() {
        int n = 10;
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    //获取随机arrayList
    public static ArrayList GetRandomArrayList() {
        int n = 10;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(i, random.nextInt(10));
        }
        return arrayList;
    }

    //随机linkedlist
    public static LinkedList GetRandomLinkedList() {
        int n = 10;
        Random random = new Random();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.add(i, random.nextInt(10));
        }
        return linkedList;
    }

    //随机hashMap
    public static HashMap GetRandomHashMap() {
        int n = 10;
        HashMap<Integer, Character> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(i, GetRandomChar('a', 'z'));
        }
        return hashMap;
    }

    //随机hashTable
    public static Hashtable GetRandomHashTable() {
        int n = 10;
        Hashtable<Integer, Character> hashtable = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            hashtable.put(i, GetRandomChar('a', 'z'));
        }
        return hashtable;
    }

    //随机hashSet
    public static HashSet GetRandomHashSet() {
        int n = 10;
        Random random = new Random();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            hashSet.add(random.nextInt(100));
        }
        return hashSet;
    }

    //随机Queue
    public static ArrayQueue GetRandomQueue() {
        Random random = new Random();
        ArrayQueue arrayQueue = new ArrayQueue(10);
        for (int i = 0; i < 10; i++) {
            arrayQueue.add(random.nextInt(10));
        }
        return arrayQueue;
    }

    public static void main(String[] args) {


        /**数组*/
        Integer[] arr = GetRandomArray();
        long[] longArray = new long[]{1,2,3,4,5,6};
        System.out.print("数组容量：" + arr.length);
        System.out.println("  第一位：" + arr[0] + "  最后一位：" + arr[arr.length - 1]);
        //Arrays工具类升序
        Arrays.sort(arr);
        //用流来去重：
        System.out.print("用流来去重:");
        Stream<Integer> stream = Arrays.stream(arr);
        stream.distinct().forEach(System.out::print);
        System.out.println("  升序：" + Arrays.toString(arr));

        /**链表 arraylist*/
        ArrayList arrayList = GetRandomArrayList();
        System.out.print("链表容量：" + arrayList.size());
        System.out.print("  第一位：" + arrayList.get(0) + "  ");
        arrayList.add(0, 100);
        arrayList.remove(0);
        arrayList.addAll(0, GetRandomArrayList());
        //arrayList.foreach打印
        arrayList.forEach(a -> System.out.print(a));
        //collections工具类升序
        Collections.sort(arrayList);
        // 重写comparator比较接口排序
        // 返回负数，意味着o1比o2小
        // 返回零，意味着o1等于o2
        // 返回正数，意味着o1大于o2
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        //collections工具类逆序
        Collections.reverse(arrayList);
        System.out.println("  降序：" + arrayList);

        /**链表 linkedlist*/
        LinkedList linkedList = GetRandomLinkedList();
        linkedList.add(0, 100);
        linkedList.offer(1000);
        System.out.println(linkedList.iterator().hasNext());
        linkedList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.print("" + linkedList.peekFirst() + linkedList.peekLast());
        System.out.println(linkedList);
        linkedList.clear();

        /**队列  Queue*/
        ArrayQueue arrayQueue = GetRandomQueue();

        System.out.println(arrayQueue.size());
        System.out.println(arrayQueue);
        Stream distinct = arrayQueue.stream().distinct();
        System.out.println("去重：");
        distinct.forEach(System.out::print);
        Object o = arrayQueue.get(0);
        System.out.println( "      " + o);
        arrayQueue.resize(12);
        System.out.println(arrayQueue.size());
        arrayQueue.add(1000);
        System.out.println(arrayQueue);
        boolean contains = arrayQueue.contains(1);
        System.out.println(contains);
        boolean empty = arrayQueue.isEmpty();
        System.out.println(empty);
        arrayQueue.remove(0);

        arrayQueue.clear();
        System.out.println(arrayQueue.size());


        /**双边队列 hashmap*/
        HashMap hashMap = GetRandomHashMap();
        System.out.println(hashMap);
        hashMap.put(0,1000);
        boolean empty1 = hashMap.isEmpty();
        int size = hashMap.size();
        Object clone = hashMap.clone();
        hashMap.remove(0);
        hashMap.replace(0,11111);
        Set set1 = hashMap.keySet();
        Set set = hashMap.entrySet();
        System.out.println("" + empty1 + size + clone + set + set1);
        hashMap.put(0,Arrays.toString(GetRandomArray()));
        System.out.println(hashMap);
        hashMap.forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });


        /**双边队列 hashtable*/
        Hashtable hashtable = GetRandomHashTable();
        System.out.println(hashtable);
        hashMap.put(0,1000);
        boolean empty2 = hashtable.isEmpty();
        int size2 = hashtable.size();
        Object clone2 = hashtable.clone();
        hashtable.remove(0);
        hashtable.replace(0,11111);
        Set set2 = hashtable.keySet();
        Set set3 = hashtable.entrySet();
        System.out.println("" + empty2 + size2 + clone2 + set2 + set3);
        hashtable.put(0,Arrays.toString(GetRandomArray()));
        System.out.println(hashtable);


        /**set hashset*/
        HashSet hashSet =  GetRandomHashSet();
        System.out.println(hashSet);
        hashSet.forEach(v -> System.out.print(v + "  "));
        hashSet.stream().forEach(System.out::print);
        hashSet.add(1000);
        int size1 = hashSet.size();
        boolean contains1 = hashSet.contains(1000);
        Spliterator spliterator = hashSet.spliterator();

        System.out.println("" + hashSet + size1 + contains1 );

        hashSet.add(1000);
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            Integer s = (Integer) iterator.next();
            System.out.print(s+"  ");
        }
        hashSet.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        hashSet.stream().sorted();
        System.err.println(hashSet);


    }
}
