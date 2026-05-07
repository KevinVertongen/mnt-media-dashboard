open module be.mnt.mediadashboard.application {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.hibernate;
    requires spring.boot.persistence;
    requires spring.context;
    requires spring.core;
    requires spring.data.jpa;

    requires jakarta.persistence;
    requires org.apache.commons.logging;
    requires org.slf4j;
    requires org.hibernate.orm.community.dialects;
    requires org.yaml.snakeyaml;
    requires com.zaxxer.hikari;
    requires org.jspecify;
}
