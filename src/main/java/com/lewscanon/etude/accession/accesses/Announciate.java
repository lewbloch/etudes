package com.lewscanon.etude.accession.accesses;

import java.lang.reflect.Constructor;
import com.lewscanon.etude.accession.Ennunciate;

import static com.lewscanon.etude.accession.accesses.AnnouncerHolder.Announcer;

public class Announciate extends Announcer {
    public static void main(String... args) {

        Announciate announciate = new Announciate();
        announciate.announce();

        Constructor<?>
        constructor = announciate.getClass().getConstructors()[0];
        System.out.printf("%s access is %s\n",
            constructor, constructor.accessFlags());

        Ennunciate ennunciate = new Ennunciate();
        ennunciate.announce();
        constructor = ennunciate.getClass().getConstructors()[0];
        System.out.printf("%s access is %s\n",
                constructor, constructor.accessFlags());

        AnnouncerHolder.Announcer announcer = new Announcer();
        announcer.announce();

        Constructor<?>[] constructors = announcer.getClass().getConstructors();
        if (constructors.length == 0) {
            System.out.printf("%s Constructor array is empty, length %d\n",
                    announcer.getClass().getName(), constructors.length);
        }
        else {
            constructor = announcer.getClass().getConstructors()[0];
            System.out.printf("%s access is %s\n",
                constructor, constructor.accessFlags());
        }
    }
}
