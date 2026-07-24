package com.lewscanon.etude.symantics;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/** Use case for a private constructor in an abstract class. */
public abstract class AbstractPrivy {
    private final static String RAND_ALGO = "SHA1PRNG";
    private final SecureRandom security;

    private String name;
    private long token;

    public long getToken() {
        return token;
    }

    public abstract boolean login(long token);

    private AbstractPrivy() {
        System.out.println("Private constructor called");
        SecureRandom random;
        try {
            random =SecureRandom.getInstance(RAND_ALGO);
            security = SecureRandom.getInstance(RAND_ALGO);
        }
        catch (NoSuchAlgorithmException exc) {
            String message = "Couldn't use " + RAND_ALGO + ": " + exc.getMessage();
            System.err.println(message);
            throw new RuntimeException(exc);
        }
        security.setSeed(random.generateSeed(20));
    }

    public AbstractPrivy(String name) {
        this();
        this.name = name;
        this.token = security.nextLong();
    }
}
