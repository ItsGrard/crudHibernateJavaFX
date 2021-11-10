module com.mycompany.crudhibernateandjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;

    opens com.mycompany.crudhibernateandjavafx to javafx.fxml;
    opens models;
    exports com.mycompany.crudhibernateandjavafx;

}
