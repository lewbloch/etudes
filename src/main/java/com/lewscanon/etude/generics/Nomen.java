package com.lewscanon.etude.generics;

public class Nomen implements Scraper<Nomen> {
    private String primo;
    private String ultimo;

    public final String getPrimo() {
        return primo;
    }

    public final void setPrimo(String primo) {
        this.primo = primo;
    }

    public final String getUltimo() {
        return ultimo;
    }

    public final void setUltimo(String ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public final void scrape(Nomen n) {
        setPrimo(n.getPrimo());
        setUltimo(n.getUltimo());
    }

    public Payload<Nomen> makePayload() {
        Payload<Nomen> payl = new Payload<>(Nomen.class);
        payl.setPayload(this);
        return payl;
    }
}
