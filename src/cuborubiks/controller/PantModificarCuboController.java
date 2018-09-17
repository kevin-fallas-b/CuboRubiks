/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import cuborubiks.util.FlowController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class PantModificarCuboController extends Controller implements Initializable {

    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnIniciar;
    private ArrayList<String> colores;
    private ArrayList<String> disponibles;
    @FXML
    private JFXComboBox<?> CbColorArriba;
    @FXML
    private JFXComboBox<?> CbColorDerecha;
    @FXML
    private JFXComboBox<?> CbColorFrente;
    @FXML
    private JFXComboBox<?> CbColorAtras;
    @FXML
    private JFXComboBox<?> CbColorIzquierda;
    @FXML
    private JFXComboBox<?> CbColorAbajo;

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
    private void presionarBtnCancelar(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantInicio", btnCancelar);
    }

    @FXML
    private void presionarBtnIniciar(ActionEvent event) {
    }
    private void cargarColoresDisponibles(){
        disponibles.add("Rojo");
        disponibles.add("Azul");
        disponibles.add("Verde");
        disponibles.add("Blanco");
        disponibles.add("Amarillo");
        disponibles.add("Naranja");
        disponibles.add("Morado");
        disponibles.add("Oliva");
        disponibles.add("Verde Oscuro");
        disponibles.add("Rosado");
        disponibles.add("Rojo");
        disponibles.add("Rojo");
        String co[]=new String[]{"rojo","azul","Verde","Blanco","Amarillo","Naranja","Morado","Oliva","Verde Oscuro","Rosado"};
    }
}
