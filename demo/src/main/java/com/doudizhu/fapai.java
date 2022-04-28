package com.doudizhu;
 import java.util.*;

public class fapai {


     public HashMap fapai() {
         //准备花色
         ArrayList<String>color=new ArrayList<String >();
         color.add("黑桃");
         color.add("红桃");
         color.add("方块");
         color.add("梅花");
         //准备数字，用列表将纸牌从大到小排列
         ArrayList<Integer>number=new ArrayList<Integer>();
         for(int i=1;i<=10;i++){
             number.add(i);
         }
//         number.add("J");
//         number.add("Q");
//         number.add("K");
//         number.add("A");
//         number.add("2");

         number.add(11);
         number.add(12);
         number.add(13);

         //定义一个map集合，用来将数字与每一张纸牌进行对应
         HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
         //纸牌编号
         int index=0;
         //循环纸牌数字
         for(Integer thisNumber:number){
             //循环纸牌花色
//             for(String thisColor:color){
                 //将花色与数字组合，形成52张牌，并赋予编号
//                 map.put(index++,thisColor+thisNumber);
//             }
             for(String thisColor:color){
                 map.put(index++,thisNumber);
             }
         }
         map.put(index++,15);
         map.put(index++,16);
         //创建0-53的数字集合代表54张牌
         ArrayList<Integer>cards=new ArrayList<Integer>();
         for(int i=0;i<=53;i++){
             cards.add(i);
         }
         //洗牌，使用Collections工具类的shuffle()方法
         Collections.shuffle(cards);
         //创建三个玩家和底牌
         ArrayList<Integer>iPlayer=new ArrayList<Integer>();
         ArrayList<Integer>iPlayer2=new ArrayList<Integer>();
         ArrayList<Integer>iPlayer3=new ArrayList<Integer>();
         ArrayList<Integer>iSecretCards=new ArrayList<Integer>();
         //遍历这副洗好的牌，遍历的过程中，将牌发到三个玩家和底牌中
         for(int i=0;i<cards.size();i++){
             if(i>=51){
                 //留取三个底牌
                 iSecretCards.add(cards.get(i));
             }else{
                 if(i%3==0){//与3取余为0的发给玩家1
                     iPlayer.add(cards.get(i));
                 }else if(i%3==1){//与3取余为1的发给玩家2
                     iPlayer2.add(cards.get(i));
                 }else {//剩余的牌发给玩家3
                     iPlayer3.add(cards.get(i));
                 }
             }
         }
         //对每个人手中的牌进行排序，使用的使Collections工具类中的sort()方法
         Collections.sort(iPlayer);
         Collections.sort(iPlayer2);
         Collections.sort(iPlayer3);
         ArrayList<Integer>sPlayer=new ArrayList<Integer>();
         ArrayList<Integer>sPlayer2=new ArrayList<Integer>();
         ArrayList<Integer>sPlayer3=new ArrayList<Integer>();
         ArrayList<Integer>sSectCards=new ArrayList<Integer>();
         //循环主键，从map中获取纸牌
         for (Integer key:iPlayer){
             sPlayer.add(map.get(key));
         }
         for (Integer key:iPlayer2){
             sPlayer2.add(map.get(key));
         }
         for (Integer key:iPlayer3){
             sPlayer3.add(map.get(key));
         }
         for (Integer key:iSecretCards){
             sSectCards.add(map.get(key));
         }
         //将分发的牌显示出来
         HashMap<String, List<Integer>> map1 = new HashMap<>();
         map1.put("1",sPlayer);
         map1.put("2",sPlayer2);
         map1.put("3",sPlayer3);
         map1.put("4",sSectCards);

         return map1;
     }
 }