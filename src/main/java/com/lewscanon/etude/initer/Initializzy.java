package com.lewscanon.etude.initer;

class Parent {
    static void announce() {
        System.out.println("Parent class static method");
    }

    static {
        System.out.println("Parent static initializer");
    }

    {
        System.out.println("Parent instance init 1");
    }

    Parent(String name) {
        this.name = name;
        System.out.printf("Parent constructor %s\n", this.name);
    }

    Parent() {
        this("anonymous Parent");
    }

    {
        System.out.println("Parent instance init 2");
    }

    String name;
    String getName() { return name; }
}

class Child extends Parent {
    static String FMT = "%s/%s";

    static void announce() {
        System.out.println("Child class static method");
    }

    static {
        System.out.println("Child static initializer");
    }

    {
        System.out.println("Child instance init 1");
    }

    Child(String name) {
        this.childName = name;
        System.out.printf("Child constructor %s\n", this.childName);
    }

    Child() {
        this("anonymous Child");
    }

    {
        System.out.println("Child instance init 2");
    }

    String childName;
    @Override String getName() { return String.format(FMT, super.getName(), childName); }
}

public class Initializzy {
    static final String FMT = "%s name = %s\n";

    static void reveal(Parent instance) {
        System.out.printf(FMT, instance.getClass().getSimpleName(), instance.getName());
    }

    static void testParentInstance() {
        reveal(new Parent());
    }

    static void testChildInstance() {
        reveal(new Child());
    }

    public static void main(String... args) {
        if (args.length == 0) {
            testChildInstance();
        }
        else switch (args[0]) {
            case "ParentAnnounce":
                Parent.announce();
                break;
            case "ChildAnnounce":
                Child.announce();
                break;
            case "parent":
                testParentInstance();
                break;
            case "child":
            default:
                testChildInstance();
                break;
        }
    }
}
