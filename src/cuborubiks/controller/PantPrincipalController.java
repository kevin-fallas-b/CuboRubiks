/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.controller;

import com.jfoenix.controls.JFXButton;
import cuborubiks.model.CuboGrande;
import cuborubiks.model.CuboPeq;
import cuborubiks.util.CoordinateAxes;
import cuborubiks.util.FlowController;
import cuborubiks.util.Viewer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import cuborubiks.util.Xform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import cuborubiks.util.Mensaje;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class PantPrincipalController extends Controller implements Initializable {

    @FXML
    private BorderPane bpPrincipal;
    @FXML
    private AnchorPane apCentro;
    @FXML
    private AnchorPane apBottom;
    @FXML
    private JFXButton btnGCamDerecha;
    @FXML
    private JFXButton btnGCamIzquierda;
    @FXML
    private JFXButton btnGCamAbajo;
    @FXML
    private JFXButton btnGCamArriba;
    @FXML
    private JFXButton btnHistorial;
    @FXML
    private JFXButton btnResolver;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private Label lblTiempo;
    @FXML
    private JFXButton btnPausar;
    @FXML
    private VBox vBoxMoverCamara;
    @FXML
    private VBox vBoxOpciones;
    @FXML
    private HBox hBoxMoverCamara;
    public static CuboGrande cubo = new CuboGrande();
    public static Timer timer = new Timer();
    private Boolean pausa = false;
    @FXML
    private JFXButton btnAyuda;
    @FXML
    private JFXButton btnMovimientoR;
    @FXML
    private JFXButton btnMovimientoD;
    @FXML
    private JFXButton btnMovimientoRi;
    @FXML
    private JFXButton btnMovimientoDi;
    @FXML
    private JFXButton btnMovimientoL;
    @FXML
    private JFXButton btnMovimientoF;
    @FXML
    private JFXButton btnMovimientoLi;
    @FXML
    private JFXButton btnMovimientoFi;
    @FXML
    private JFXButton btnMovimientoB;
    @FXML
    private JFXButton btnMovimientoU;
    @FXML
    private JFXButton btnMovimientoBi;
    @FXML
    private JFXButton btnMovimientoUi;
    @FXML
    private Label lblJuegoPausado;
    @FXML
    private Label lblMovimientos;
    @FXML
    private VBox vBoxMoverCamara1;
    @FXML
    private JFXButton btnGCamAdelante;
    @FXML
    private JFXButton btnGCamAtras;
    @FXML
    private Label lblGirarCamara;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblJuegoPausado.setVisible(false);
        cubo.crearCubo();
        bpPrincipal.setCenter(cubo.getSubScene());
        iniciarTiempo();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void presionarBtnGCamDerecha(ActionEvent event1) {
        cubo.rotarCamara("de");
    }

    @FXML
    private void presionarBtnGCamIzquierda(ActionEvent event) {
        cubo.rotarCamara("iz");
    }

    @FXML
    private void presionarBtnGCamAbajo(ActionEvent event) {
        cubo.rotarCamara("ab");
    }

    @FXML
    private void presionarBtnGCamArriba(ActionEvent event) {
        cubo.rotarCamara("ar");
    }

    @FXML
    private void presionarBtnGCamAdelante(ActionEvent event) {
        cubo.rotarCamara("ad");
    }

    @FXML
    private void presionarBtnGCamAtras(ActionEvent event) {
        cubo.rotarCamara("at");
    }

    @FXML
    private void presionarBtnHistorial(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("PantHistorial", (Stage) btnHistorial.getScene().getWindow(), false);
    }

    @FXML
    private void presionarBtnResolver(ActionEvent event) {
    }

    @FXML
    private void presionarBtnGuardar(ActionEvent event) {
    }

    @FXML
    private void presionarBtnSalir(ActionEvent event) {
        if (new Mensaje().showConfirmation("Salir", getStage(), "Usted esta a punto de salir del programa.\n\nLos datos no guardados se perderan. Desea Continuar?")) {
            {
                timer.cancel();
                FlowController.getInstance().salir();
            }
        }
    }

    @FXML
    private void presionarBtnPausar(ActionEvent event) {
        //solamente se encarga de cambiar pausa, el metodo iniciar tiempo se encarga de no seguir
        //pausa = !pausa;
        if (pausa == false) {
            pausa = true;
            lblGirarCamara.setDisable(true);
            lblJuegoPausado.setVisible(true);
            lblTiempo.setDisable(true);
            lblMovimientos.setDisable(true);
            vBoxMoverCamara.setDisable(true);
            vBoxMoverCamara1.setDisable(true);
            vBoxOpciones.setDisable(true);
            hBoxMoverCamara.setDisable(true);
        } else {
            pausa = false;
            lblTiempo.setDisable(false);
            lblGirarCamara.setDisable(false);
            lblJuegoPausado.setVisible(false);
            lblMovimientos.setDisable(false);
            vBoxMoverCamara.setDisable(false);
            vBoxMoverCamara1.setDisable(false);
            vBoxOpciones.setDisable(false);
            hBoxMoverCamara.setDisable(false);
        }
    }

    @FXML
    private void presionarBtnAyuda(ActionEvent event) {
    }

    @FXML
    private void presionarBtnMovimientoR(ActionEvent event) {
        String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("r");
                break;
            case "de":
                cubo.mover("b");
                break;            
            case "at":
                cubo.mover("l");
                break;
            case "iz":
                cubo.mover("f");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoD(ActionEvent event) {
        cubo.mover("d");
    }

    @FXML
    private void presionarBtnMovimientoRi(ActionEvent event) {        
        String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("ri");
                break;
            case "de":
                cubo.mover("bi");
                break;            
            case "at":
                cubo.mover("li");
                break;
            case "iz":
                cubo.mover("fi");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoDi(ActionEvent event) {
        cubo.mover("di");

    }

    @FXML
    private void presionarBtnMovimientoL(ActionEvent event) {
         String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("l");
                break;
            case "de":
                cubo.mover("f");
                break;            
            case "at":
                cubo.mover("r");
                break;
            case "iz":
                cubo.mover("b");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoF(ActionEvent event) {
        String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("f");
                break;
            case "de":
                cubo.mover("r");
                break;            
            case "at":
                cubo.mover("b");
                break;
            case "iz":
                cubo.mover("l");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoLi(ActionEvent event) {
        String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("li");
                break;
            case "de":
                cubo.mover("fi");
                break;            
            case "at":
                cubo.mover("ri");
                break;
            case "iz":
                cubo.mover("bi");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoFi(ActionEvent event) {
         String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("fi");
                break;
            case "de":
                cubo.mover("ri");
                break;            
            case "at":
                cubo.mover("bi");
                break;
            case "iz":
                cubo.mover("li");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoB(ActionEvent event) {
         String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("b");
                break;
            case "de":
                cubo.mover("l");
                break;            
            case "at":
                cubo.mover("f");
                break;
            case "iz":
                cubo.mover("r");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoU(ActionEvent event) {
        cubo.mover("u");
    }

    @FXML
    private void presionarBtnMovimientoBi(ActionEvent event) {
        String caraActiva = cubo.getLadoActivo();
        switch (caraActiva) {
            case "ad":
                cubo.mover("bi");
                break;
            case "de":
                cubo.mover("li");
                break;            
            case "at":
                cubo.mover("fi");
                break;
            case "iz":
                cubo.mover("ri");
                break;

        }
    }

    @FXML
    private void presionarBtnMovimientoUi(ActionEvent event) {
        cubo.mover("ui");
    }

    private void iniciarTiempo() {
        TimerTask task = new TimerTask() {
            Integer tiempo = cubo.getTiempo();
            Integer minutos = tiempo / 60;
            Integer segundos = tiempo % 60;

            @Override
            public void run() {
                if (segundos < 60) {
                    if (pausa == false) {
                        segundos++;
                    }
                } else {
                    segundos = 00;
                    minutos++;
                }
                cubo.setTiempo(minutos * 60 + segundos);
                Platform.runLater(() -> lblTiempo.setText("Tiempo: " + minutos + ":" + segundos));
            }
        ;

        };
         timer.scheduleAtFixedRate(task, 1000, 1000);

    }

}
