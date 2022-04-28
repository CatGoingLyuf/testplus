package com.doudizhu;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author lyuf
 * @Date 2022/4/27 16:02
 * @Version 1.0
 */
public class dapai {

    @SneakyThrows
    @Test
    public void test() {
        fapai fapai = new fapai();
        HashMap<String, List<Integer>> paidui = fapai.fapai();
        System.out.println(paidui);
        // 分牌
        player player1 = new player();
        player1.setPai(paidui.get("1"));
        player1.setPaishu(player1.getPai().size());

        player player2 = new player();
        player2.setPai(paidui.get("2"));
        player2.setPaishu(player2.getPai().size());

        player player3 = new player();
        player3.setPai(paidui.get("3"));
        player3.setPaishu(player3.getPai().size());
        player dizhu = new player();
        ArrayList<player> renshu = new ArrayList<>();
        renshu.add(dizhu);
        renshu.add(player1);
        renshu.add(player2);
        renshu.add(player3);
        // 分地主

        Random random = new Random();
        int i1 = random.nextInt(10);
        if (i1 <= 3) {
            player1.getPai().addAll(paidui.get("4"));
            player1.setPaishu(player1.getPaishu() + 3);
            BeanUtils.copyProperties(dizhu, player1);
            System.out.println("地主为plauer1");
            renshu.remove(1);
            Collections.sort(dizhu.getPai());
        } else if (i1 <= 6) {
            player2.getPai().addAll(paidui.get("4"));
            player2.setPaishu(player2.getPaishu() + 3);
            BeanUtils.copyProperties(dizhu, player2);
            System.out.println("地主为plauer2");
            renshu.remove(2);
            Collections.sort(dizhu.getPai());
        } else if (i1 <= 9) {
            player3.getPai().addAll(paidui.get("4"));
            player3.setPaishu(player3.getPaishu() + 3);
            BeanUtils.copyProperties(dizhu, player3);
            System.out.println("地主为plauer3");
            renshu.remove(3);
            Collections.sort(dizhu.getPai());
        }

        Integer[] x = {0};
        for (int i = 0 ; i <= 10 ; i ++) {
            renshu.forEach(str -> {
                // 地主
                if (x[0] > 0) {
                    if (renshu.get(0).getPai().size() == 0){
                        return;
                    }
                    List<Integer> dizhuPai = renshu.get(0).getPai();
                    final Integer[] a = {0};
                    dizhuPai.forEach(integer -> {
                        if (integer > x[0]) {
                            a[0] =integer;
                            System.out.println("地主打牌:" + integer);
                        }
                    });
                    dizhuPai.remove(a[0]);
                    dizhu.setPaishu(dizhu.getPaishu() - 1);
                    x[0] = a[0];
                } else {
                    List<Integer> dizhuPai = dizhu.getPai();
                    System.out.println("地主打牌：" + dizhuPai.get(0));
                    x[0] = dizhuPai.get(0);
                    dizhuPai.remove(renshu.get(0).getPai().get(0));
                    dizhu.setPaishu(dizhu.getPaishu() - 1);
                }

                // 农民1
                if (renshu.get(1).getPai().size() == 0){
                    return;
                }
                player nongmin1 = renshu.get(1);
                final Integer[] a = {0};
                nongmin1.getPai().forEach(integer -> {
                    if (integer > x[0]) {
                        a[0] =integer;
                        System.out.println("农民1打牌:" + integer);
                    }
                });
                nongmin1.getPai().remove(a[0]);
                nongmin1.setPaishu(nongmin1.getPaishu() - 1);
                x[0] = a[0];

                // 农民2
                if (renshu.get(2).getPai().size() == 0){
                    return;
                }
                player nongmin2 = renshu.get(2);
                final Integer[] b = {0};
                nongmin2.getPai().forEach(integer -> {
                    if (integer > x[0]) {
                        b[0] =integer;
                        System.out.println("农民2打牌:" + integer);
                    }
                });
                nongmin2.getPai().remove(b[0]);
                nongmin2.setPaishu(nongmin2.getPaishu() - 1);
                x[0] = b[0];
            });
        }

        if(dizhu.getPai().size() == 0) {
            System.out.println("地主胜利！");
        } else if (renshu.get(1).getPai().size() == 0){
            System.out.println("农民胜利！");
        } else if (renshu.get(2).getPai().size() == 0) {
            System.out.println("农民胜利！");
        }
    }
}
