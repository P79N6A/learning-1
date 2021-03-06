package com.guavademo.liam;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by guavademo on 2016/1/18.
 */
public class CacheDemo {

    private static Executor executor = Executors.newCachedThreadPool();

    private static LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "没有这个参数啊";
                }
            });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(0 | 1 | 2);
        System.out.println("testing begin ================= ");
        stringCache.put("test1", "guavademo");
        stringCache.invalidate("test1");
        TimeUnit.SECONDS.sleep(2);
        stringCache.cleanUp();
        System.out.println("test1: " + stringCache.get("test1"));
        CacheStats cacheStats = stringCache.stats();
        System.out.println(cacheStats);
//        try {
//            TimeUnit.SECONDS.sleep(2);
//            System.out.println("test1: " + stringCache.getIfPresent("test1"));
////            TimeUnit.SECONDS.sleep(10);
////            System.out.println("test1: " + stringCache.getIfPresent("test1"));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        cacheStats = stringCache.stats();
//        System.out.println(cacheStats);

        System.out.println("testing end =============");
    }

}
