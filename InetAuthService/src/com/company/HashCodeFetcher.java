package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class HashCodeFetcher implements Callable<String> {
    private CountDownLatch latch;
    private DBReader reader;
    private String token;

    public HashCodeFetcher(CountDownLatch l, DBReader reader, String token) {
        this.latch = l;
        this.reader = reader;
        this.token = token;
    }

    @Override
    public String call() throws Exception {
        latch.await();
        System.out.println("Sending token to deviceId "+reader.getDeivceId());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AuthUtilities.calculateHashcode(reader.getKey(),token);
    }
}
