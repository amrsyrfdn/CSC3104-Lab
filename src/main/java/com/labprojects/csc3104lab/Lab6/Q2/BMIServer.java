/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB6Q2
 */
package com.labprojects.csc3104lab.Lab6.Q2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BMIServer extends Application {
    private PreparedStatement pstmt;

    public BMIServer() {
        initializeDB();
    }

    protected void initializeDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521/FSKTM", "E224300", "224300");
            pstmt = conn.prepareStatement("INSERT INTO BMIRECORD (name, id, age, weight, height, bmi, category, classification, risk) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (Exception ex) {
            System.out.println("Database connection failed: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        TextArea ta = new TextArea();
        ta.setEditable(false);
        pane.add(ta, 0, 0);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

                while (true) {
                    Socket socket = serverSocket.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    String name = input.readUTF();
                    String id = input.readUTF();
                    int age = input.readInt();
                    double weight = input.readDouble();
                    double height = input.readDouble();

                    double bmi = weight / (height * height);
                    String category, classification, risk;

                    if (bmi < 18.5) {
                        category = "< 18.5";
                        classification = "Underweight";
                        risk = "Increased";
                    } else if (bmi <= 24.9) {
                        category = "18.5 - 24.9";
                        classification = "Normal Weight";
                        risk = "Least";
                    } else if (bmi <= 29.9) {
                        category = "25.0 - 29.9";
                        classification = "Overweight";
                        risk = "Increased";
                    } else if (bmi <= 34.9) {
                        category = "30.0 - 34.9";
                        classification = "Obese Class I";
                        risk = "High";
                    } else if (bmi <= 39.9) {
                        category = "35.0 - 39.9";
                        classification = "Obese Class II";
                        risk = "Very High";
                    } else {
                        category = ">= 40.0";
                        classification = "Obese Class III";
                        risk = "Extremely High";
                    }

                    pstmt.setString(1, name);
                    pstmt.setString(2, id);
                    pstmt.setInt(3, age);
                    pstmt.setDouble(4, weight);
                    pstmt.setDouble(5, height);
                    pstmt.setDouble(6, bmi);
                    pstmt.setString(7, category);
                    pstmt.setString(8, classification);
                    pstmt.setString(9, risk);
                    pstmt.executeUpdate();

                    Platform.runLater(() -> ta.appendText("Record added for: " + name + '\n'));

                    output.writeUTF(name + "'s BMI is: " + String.format("%.2f", bmi));
                    output.writeUTF(id);
                    output.writeInt(age);
                    output.writeUTF(category);
                    output.writeUTF(classification);
                    output.writeUTF(risk);
                }
            } catch (IOException ex) {
                Platform.runLater(() -> ta.appendText("Error: " + ex.getMessage() + '\n'));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
