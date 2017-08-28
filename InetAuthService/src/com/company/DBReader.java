package com.company;

import java.util.concurrent.CountDownLatch;

public class DBReader implements Runnable {
    private CountDownLatch latch;
    private Long uId;
    private Long deivceId;
    private String key;

    public DBReader(CountDownLatch l, Long uId) {
        this.latch = l;
        this.uId = uId;
    }
    public Long getDeivceId() {
        return deivceId;
    }

    public String getKey() {
        return key;
    }
    @Override
    public void run() {
        System.out.println("Retrieving device id and key from db for user "+uId+".");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deivceId = 125435L;
        key = "Secret Key";
        latch.countDown();
    }
}
