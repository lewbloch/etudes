package com.lewscanon.etude.symantics;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

/** Use case for a private constructor in an abstract class. */
public abstract class AbstractPrivy {
    private final static String RAND_ALGO = "SHA1PRNG";
    private final SecureRandom security;

    private String name;
    private final long token;

    private long getToken() {
        return token;
    }

    public boolean login(String name) {
        System.out.println("Sending secret token first");
        return login();
    }

    public abstract boolean login();

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
        this.token = security.nextLong();
    }

    public AbstractPrivy(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + name + "}";
    }

    @Override
    public boolean equals(Object oth) {
        if (! (oth instanceof AbstractPrivy other)) {
            return false;
        }
        return Objects.equals(getToken(), other.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }
}
