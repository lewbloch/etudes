package com.lewscanon.etude.symantics;

public class Privy extends AbstractPrivy{
    public Privy(String name) {
        super(name);
    }

    @Override
    public boolean login(long token) {
        System.out.println("Logging in name with token: " + token);
        return true;
    }

    public static void main(String[] args) {
        Privy privy = new Privy("Jo Jones");
        long token = privy.getToken();
        boolean success = privy.login(token);
        System.out.println("Login succeeded? " + success);
    }
}
