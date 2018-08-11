/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.controller;

import com.jfoenix.controls.JFXButton;
import cuborubiks.util.FlowController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class PantTablaPuntajeController extends Controller implements Initializable {

    @FXML
    private JFXButton btnRegresar;
    @FXML
    private TableView<?> tvPuntaje;
    @FXML
    private TableColumn<?, ?> clmJugador;
    @FXML
    private TableColumn<?, ?> clmPuntaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void presionarBtnRegresar(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantInicio", btnRegresar);
    }
    
}
