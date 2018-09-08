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
import javafx.scene.Node;
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

    private Group root = new Group();
    public static Xform world = new Xform();
    //se utiliza SubScene ya que se puede incluir dentro del BorderPane
    private SubScene subScene = new SubScene(root, 1024, 450, true, javafx.scene.SceneAntialiasing.BALANCED);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.setDepthTest(DepthTest.ENABLE);
        root.getChildren().add(world);
        CoordinateAxes coordinateAxes = new CoordinateAxes();
        world.getChildren().addAll(coordinateAxes.get());
        cubo.crearCubo();
        subScene.setFill(Color.GREY);
        //viewer le agrega camara y control de camara al subscene
        Viewer viewer = new Viewer(subScene, root);
        bpPrincipal.setCenter(subScene);
        iniciarTiempo();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void presionarBtnGCamDerecha(ActionEvent event1) {
        //disminuir X en 180,
        System.out.println("moviendo camara a la derecha");
        Camera camera = subScene.getCamera();
        Xform cameraXform = new Xform();
        Xform cameraXform2 = new Xform();
        Xform cameraXform3 = new Xform();
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);

        //btnGCamDerecha.setOnAction((event) -> {
        //event.fireEvent(camera, new MouseDragEvent(0,0,0 ,0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, false, false, new PickResult(), btnGCamDerecha));
        //});
    }

    @FXML
    private void presionarBtnGCamIzquierda(ActionEvent event) {
    }

    @FXML
    private void presionarBtnGCamAbajo(ActionEvent event) {
    }

    @FXML
    private void presionarBtnGCamArriba(ActionEvent event) {
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
            vBoxMoverCamara.setDisable(true);
            vBoxOpciones.setDisable(true);
            hBoxMoverCamara.setDisable(true);
            btnAyuda.setDisable(true);
        } else {
            pausa = false;
            vBoxMoverCamara.setDisable(false);
            vBoxOpciones.setDisable(false);
            hBoxMoverCamara.setDisable(false);
            btnAyuda.setDisable(false);
        }
    }

    @FXML
    private void presionarBtnAyuda(ActionEvent event) {
    }

    @FXML
    private void presionarBtnMovimientoR(ActionEvent event) {
        cubo.mover("r");
    }

    @FXML
    private void presionarBtnMovimientoD(ActionEvent event) {
        cubo.mover("d");
    }

    @FXML
    private void presionarBtnMovimientoRi(ActionEvent event) {
        cubo.mover("ri");
    }

    @FXML
    private void presionarBtnMovimientoDi(ActionEvent event) {
        cubo.mover("di");
    }

    @FXML
    private void presionarBtnMovimientoL(ActionEvent event) {
        cubo.mover("l");
    }

    @FXML
    private void presionarBtnMovimientoF(ActionEvent event) {
        cubo.mover("f");
    }

    @FXML
    private void presionarBtnMovimientoLi(ActionEvent event) {
        cubo.mover("li");
    }

    @FXML
    private void presionarBtnMovimientoFi(ActionEvent event) {
        cubo.mover("fi");
    }

    @FXML
    private void presionarBtnMovimientoB(ActionEvent event) {
        cubo.mover("b");
    }

    @FXML
    private void presionarBtnMovimientoU(ActionEvent event) {
        cubo.mover("u");
    }

    @FXML
    private void presionarBtnMovimientoBi(ActionEvent event) {
        cubo.mover("bi");
        CuboPeq mos;
        mos= cubo.getCuboPeq(0, 0, 0);
        mos.moverCuboPeq(mos.getPosX()-1.00, mos.getPosY(), mos.getPosZ());
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
