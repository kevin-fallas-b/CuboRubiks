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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class PantInicioController extends Controller implements Initializable {

    @FXML
    private Button btnAyuda;
    @FXML
    private JFXButton btnAcercaDe;
    @FXML
    private JFXButton btnIniciar;
    @FXML
    private JFXButton btnCargarPartida;
    @FXML
    private JFXButton btnModificarPartida;
    @FXML
    private JFXButton btnTablaPuntaje;

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
    private void presionarBtnAyuda(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setResizable(true);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Este es el dialogo de ayuda\nAbajo se detalla la funcion de cada boton.");
        alert.setContentText("-Iniciar: Inicia una partida nueva con los colores predeterminados.\n\n-Cargar Partida: Permite buscar una partida guardada anteriormente y continuarla.\n\n-Modificar Partida: Permite que el usuario modifique los colores del cubo antes de iniciar la partida.\n\n-Tabla de puntaje: Permite al usuario observar cuales han sido los mejores puntajes obtenidos.\n\n-Acerca de: Muestra informacion acerca de quien desarrollo este software.");
        alert.showAndWait();
    }

    @FXML
    private void presionarBtnAcercaDe(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantAcercaDe", btnAcercaDe);
    }

    @FXML
    private void presionarBtnIniciar(ActionEvent event) {
        FlowController.getInstance().goMain();
        Stage stage = (Stage)btnIniciar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void presionarBtnCargarPartida(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantCargar", btnCargarPartida);
    }

    @FXML
    private void presionarBtnModificarPartida(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantModificarCubo", btnModificarPartida);
    }

    @FXML
    private void presionarBtnTablaPuntaje(ActionEvent event) throws IOException {
        FlowController.getInstance().goViewSameWindow("PantTablaPuntaje", btnTablaPuntaje);
    }

}
