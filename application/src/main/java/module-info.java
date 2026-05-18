module be.mnt.mediadashboard.application {

    requires be.mnt.mediadashboard.desktop;

    requires jakarta.cdi;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.xml.bind;

    requires java.instrument;
    requires javafx.graphics;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.hibernate;
    requires spring.boot.persistence;
    requires spring.context;
    requires spring.core;
    requires spring.data.jpa;

    requires com.fasterxml.classmate;
    requires com.zaxxer.hikari;
    requires net.bytebuddy;
    requires org.apache.commons.logging;
    requires org.jboss.logging;
    requires org.jspecify;
    requires org.slf4j;
    requires org.hibernate.orm.community.dialects;
    requires org.yaml.snakeyaml;

    exports be.mnt.mediadashboard.application to
        javafx.graphics;

    opens be.mnt.mediadashboard.application to
        spring.beans,
        spring.context,
        spring.core;
}
