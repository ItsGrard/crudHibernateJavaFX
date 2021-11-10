/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudhibernateandjavafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.Query;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author Katsu
 */
public class PrimaryController implements Initializable {

    @FXML
    private TableColumn<Pedido, Integer> id;
    @FXML
    private TableColumn<Pedido, String> alumno;
    @FXML
    private TableColumn<Pedido, String> ciclo;
    @FXML
    private TableColumn<Pedido, String> fecha;
    @FXML
    private TableColumn<Pedido, Integer> prod_id;
    @FXML
    private TableColumn<Pedido, String> estado;
    
        private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
        private static Session s = sf.openSession();
    @FXML
    private TableView<Pedido> tabla;
    @FXML
    private Label pendientes;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);
        
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        contenido.clear();
                        contenido.addAll(listarComandas());
                        count();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);



        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        alumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
        ciclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        prod_id.setCellValueFactory(new PropertyValueFactory<>("prodId"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        contenido.addAll(listarComandas());

    }
        public void count(){
         pendientes.setText("Pedidos pendientes: " + String.valueOf(listarComandasPendientes().size()));
        }
        public static ArrayList<Pedido> listarComandas(){
        
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();
       // System.out.println(qres.toString());
       return qres;
    }
        public static ArrayList<Pedido> listarComandasPendientes(){
        
        Query q = s.createQuery("FROM Pedido WHERE estado = 'pendiente'", Pedido.class);
        
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();
       // System.out.println(qres.toString());
       return qres;
    }
        
    @FXML
    private void click(MouseEvent event) {

       Pedido p = tabla.getSelectionModel().getSelectedItem();
         Transaction t = s.beginTransaction();
            p.setEstado("Recogido");
            s.update(p);
            t.commit();
            listarComandas();
    }

}
