package com.lewscanon.etude.accession;

import com.lewscanon.etude.accession.AccessUnspecified.Announcing;
import com.lewscanon.etude.accession.accesses.AnnouncerHolder;
import com.lewscanon.etude.accession.accesses.Announciate;

import static com.lewscanon.etude.accession.AccessUnspecified.FMT;

public class Ennunciate extends AnnouncerHolder
{
    public void announce() {
        System.out.printf(FMT, getClass(),
            "does not implement " + Announcing.class.getName());
    }

    public static void main(String... args) {
        Announciate announciate = new Announciate();
        announciate.announce();

        Ennunciate ennunciate = new Ennunciate();
        ennunciate.announce();

//        AnnouncerHolder.Announcer announcer = new AnnouncerHolder.Announcer();
//        announcer.announce();
    }
}
