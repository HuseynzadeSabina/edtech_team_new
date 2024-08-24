package com.example.edtech_team_new;

import com.example.edtech_team_new.dao.DatesDAO;
import com.example.edtech_team_new.models.Dates;
import com.example.edtech_team_new.webSocket.MyWebSocketServerMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
@ServerEndpoint("/")


public class MainPageController implements Initializable {
    @FXML
    private Label date;

    @FXML
    private Label hum;

    @FXML
    private Label lat;

    @FXML
    private Label longg;

    @FXML
    private ImageView photo;

    @FXML
    private Button refresh_btn;

    @FXML
    private Label temp;

    @FXML
    private Label time;

    @FXML
    private Label x;

    @FXML
    private Label y;

    @FXML
    private Label z;

    private  Dates currentDate;

    DatesDAO datesDAO = new DatesDAO();
    MyWebSocketServerMain server=new MyWebSocketServerMain();
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
server.startUp();
        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                setCurrentDate(currentDate);
            }
        });

        
    }
    public void setCurrentDate(Dates date){
        currentDate = date;


        temp.setText(String.valueOf(currentDate.getTemprature()));
    }


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received: " + message);
        try {
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
