package com.lewscanon.etude.patternz;

public class Duper implements Prototype {
    private String FMT = "Duper {%s, %f, %d}";

    private String name;
    private Double weight;
    private Integer priority;

    public Duper(String name, Double weight, Integer priority) {
        this.name = name;
        this.weight = weight;
        this.priority = priority;
    }

    private Duper(Duper duped) {
        this(duped.getName(), duped.getWeight(), duped.getPriority());
    }

    @Override
    public Duper dupe() {
        return new Duper(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format(FMT, getName(), getWeight(), getPriority());
    }

    public static void main(String... args) {
        Duper dup = new Duper("Herbert", 35.4, 17);
        Duper duple = dup.dupe();
        System.out.printf("  dup = %s\nduple = %s\n", dup, duple);
    }
}
