module com.labprojects.csc3104lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.labprojects.csc3104lab to javafx.fxml;
    exports com.labprojects.csc3104lab;
    opens com.labprojects.csc3104lab.Lab1 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab1;
    opens com.labprojects.csc3104lab.Lab2 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab2;
    /*opens com.labprojects.csc3104lab.Lab3 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab3;
    opens com.labprojects.csc3104lab.Lab4 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab4;
    opens com.labprojects.csc3104lab.Lab5 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab5;
    opens com.labprojects.csc3104lab.Lab6 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab6;
    opens com.labprojects.csc3104lab.Lab7 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab7;
    opens com.labprojects.csc3104lab.Lab8 to javafx.fxml;
    exports com.labprojects.csc3104lab.Lab8;*/
    // open for each new lab session

}