module com.labprojects.csc3104lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.rmi;


    opens com.labprojects.csc3104lab to javafx.fxml;
    exports com.labprojects.csc3104lab;
    opens com.labprojects.csc3104lab.BuddyTesting to javafx.fxml;
    exports com.labprojects.csc3104lab.BuddyTesting;
    opens com.labprojects.csc3104lab.Lab1 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab1;
    opens com.labprojects.csc3104lab.Lab2 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab2;
    opens com.labprojects.csc3104lab.Lab3 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab3;
    opens com.labprojects.csc3104lab.Lab4 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab4;
    exports com.labprojects.csc3104lab.Lab4.Q1;
    opens com.labprojects.csc3104lab.Lab4.Q1 to javafx.fxml;
    exports com.labprojects.csc3104lab.Project;
    opens com.labprojects.csc3104lab.Project to javafx.fxml;
    opens com.labprojects.csc3104lab.Lab5.Q1 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab5.Q1;
    opens com.labprojects.csc3104lab.Lab5.Q2 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab5.Q2;
    /*opens com.labprojects.csc3104lab.Lab6 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab6;
    opens com.labprojects.csc3104lab.Lab7 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab7;
    opens com.labprojects.csc3104lab.Lab8 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab8;*/
    // open for each new lab session

}