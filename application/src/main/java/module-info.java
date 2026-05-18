module be.mnt.mediadashboard.application {

    requires be.mnt.mediadashboard.desktop;

    requires javafx.graphics;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    requires org.apache.commons.logging;
    requires org.slf4j;
    requires org.yaml.snakeyaml;

    exports be.mnt.mediadashboard.application to
        javafx.graphics,
        spring.beans;
}
