package com.company;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Long uID = 54644352L;
        String token,hashCalculatedAtServer,hashFromPhone= null;
        ExecutorService ex = Executors.newFixedThreadPool(2);
        DBReader reader = new DBReader(latch,uID);
        ex.submit(reader);
        token = AuthUtilities.generateToken();
        HashCodeFetcher phoneHash = new HashCodeFetcher(latch,reader,token);
        Future<String> f = ex.submit(phoneHash);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hashCalculatedAtServer = AuthUtilities.calculateHashcode(reader.getKey(),token);
        try {
            hashFromPhone = f.get();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception.");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Execution Exception.");
            e.printStackTrace();
        }
        if(hashCalculatedAtServer.equals(hashFromPhone)){
            System.out.println("User Authenticated.");
        }
        ex.shutdown();
    }
}
