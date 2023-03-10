package com.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
class NetMall{
    private String mallName;

    public Double calcPrice(String productName){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble()*2 + productName.charAt(2);
    }
}
/**
 * @autor soft
 * 2023/3/9
 * 通过Completable可以看出
 */
public class CompletableFutureMallDemo {
    static List<NetMall> netMallList = Arrays.asList(
            new NetMall("京东"),
            new NetMall("淘宝"),
            new NetMall("当当"),
            new NetMall("咸鱼")
    );
    public static List<String> completable(String productName){

        List<CompletableFuture<String>> completableFutureList = netMallList.stream().map((netMall) ->
                CompletableFuture.supplyAsync(() ->
                        String.format(productName + " in %s price is %.2f ", netMall.getMallName(), netMall.calcPrice(productName))
                )).collect(Collectors.toList());
        List<String> list = completableFutureList.stream().map(stringCompletableFuture -> stringCompletableFuture.join()).collect(Collectors.toList());
        return list;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(completable("mysql"));
        long longTime = System.currentTimeMillis();
        System.out.println("花费时间："+(longTime-startTime));

    }
}
