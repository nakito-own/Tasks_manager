package com.databasefx;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_task;

    @FXML
    private VBox all_tasks;

    @FXML
    private TextField enter_task;

    DataBaseConnector db = null;

    @FXML
    void initialize() {
        db = new DataBaseConnector();

        add_task.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if(!enter_task.getText().trim().equals("")) {

                        db.insertTask(enter_task.getText());
                        loadInfo();
                        enter_task.setText("");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        loadInfo();
    }

    void loadInfo() {
        try {
            all_tasks.getChildren().clear();

            ArrayList<String> tasks = db.getTasks();
            for(int i = 0; i < tasks.size(); i++)
                all_tasks.getChildren().add(new Label(tasks.get(i)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}