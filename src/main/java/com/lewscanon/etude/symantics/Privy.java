package com.lewscanon.etude.symantics;

public class Privy extends AbstractPrivy{
    public Privy(String name) {
        super(name);
    }

    @Override
    public boolean login() {
        System.out.println("Set up login method");
        return true;
    }

    public static void main(String[] args) {
        Privy privy = new Privy("Jo Jones");
        String name = privy.getName();
        boolean success = privy.login(name);
        System.out.println("Login succeeded? " + success);
    }
}
