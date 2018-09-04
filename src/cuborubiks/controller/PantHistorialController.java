/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.controller;

import com.jfoenix.controls.JFXButton;
import cuborubiks.model.Movimiento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kevin F
 */
public class PantHistorialController extends Controller implements Initializable {

    @FXML
    private VBox vboxMovimientos;
    @FXML
    private JFXButton btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenar();
    }

    @Override
    public void initialize() {
    }
    private void llenar(){
        vboxMovimientos.getChildren().clear();
        Movimiento aux = PantPrincipalController.cubo.getMovimientos();
        while(aux!=null){
            Label label = new Label();
            label.setText(aux.getDireccion());
            vboxMovimientos.getChildren().add(label);
            aux=aux.getmSig();
        }
    }

    @FXML
    private void presionarBtnRegresar(ActionEvent event) {
        Stage stage = (Stage)btnRegresar.getScene().getWindow();
        stage.close();
    }
}   
