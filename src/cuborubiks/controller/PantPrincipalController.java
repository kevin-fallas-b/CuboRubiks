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
    private Group root = new Group();
    public static Xform world = new Xform();
    //se utiliza SubScene ya que se puede incluir dentro del BorderPane
    private SubScene subScene = new SubScene(root, 1024, 450, true, javafx.scene.SceneAntialiasing.BALANCED);
    public static CuboGrande cubo = new CuboGrande();
    public static Timer timer = new Timer();
    private Boolean pausa = false;

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
        FlowController.getInstance().goViewInWindowModal("PantHistorial", (Stage)btnHistorial.getScene().getWindow(), false);
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
        pausa = !pausa;
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
