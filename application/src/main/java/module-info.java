module be.mnt.mediadashboard.application {

    requires be.mnt.mediadashboard.desktop;

    requires jakarta.cdi;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.xml.bind;

    requires java.instrument;
    requires javafx.controls;

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

    opens be.mnt.mediadashboard.application to
        javafx.graphics,
        spring.beans,
        spring.context,
        spring.core;
}
