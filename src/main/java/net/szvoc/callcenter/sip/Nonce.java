package net.szvoc.callcenter.sip;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public final class Nonce {
    private static AtomicInteger seed = new AtomicInteger(10000000);

    public static String randomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int randomInteger() {
        return seed.incrementAndGet();
    }
}
