/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.controller;

import cuborubiks.model.Cubo;
import cuborubiks.util.CoordinateAxes;
import cuborubiks.util.Viewer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import cuborubiks.util.Xform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

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

    Cubo cubo[][][] = new Cubo[3][3][3];

    public static Xform world = new Xform();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        root.getChildren().add(world);
        CoordinateAxes coordinateAxes = new CoordinateAxes();
        world.getChildren().addAll(coordinateAxes.get());
        crearCubo();
        //crea los cubos que se ven en pantalla, no es final, ahorita es solo para probar, recibe parent como parametro
        //buildSceneGaph(world);
        //se utiliza SubScene ya que se puede incluir dentro del BorderPane
        SubScene subScene = new SubScene(root, 1024, 450, true, javafx.scene.SceneAntialiasing.BALANCED);
        subScene.setFill(Color.GREY);
        //viewer le agrega camara y control de camara al subscene
        Viewer viewer = new Viewer(subScene, root);
        bpPrincipal.setCenter(subScene);
    }

    @Override
    public void initialize() {
    }

    private void buildSceneGaph(Group parent) {
        double sizeCubo = 50;

        Node cube000 = Cubo.createInterno(new Color[]{
            Color.RED, // Front
            Color.BLUE, // Back
            Color.GREEN, // Up 
            Color.WHITE, // Down 
            Color.BLUE, // Left 
            Color.BLACK // Right
        });
        Xform cubeXform000 = new Xform();
        cubeXform000.setScale(sizeCubo * 0.99);
        cubeXform000.setTranslate(-1.65 * sizeCubo, 0, 0);
        cubeXform000.getChildren().add(cube000);
        parent.getChildren().add(cubeXform000);

        Node cube100 = Cubo.createInterno(new Color[]{
            Color.RED, // Front
            Color.BLUE, // Back
            Color.GREEN, // Up 
            Color.WHITE, // Down 
            Color.BLACK, // Left 
            Color.BLACK // Right
        });
        Xform cubeXform100 = new Xform();
        cubeXform100.setScale(sizeCubo * 0.99);
        cubeXform100.setTranslate(-0.50 * sizeCubo, 0, 0);
        cubeXform100.getChildren().add(cube100);
        parent.getChildren().add(cubeXform100);

        Node cube200 = Cubo.createInterno(new Color[]{
            Color.RED, // Front
            Color.BLUE, // Back
            Color.GREEN, // Up 
            Color.WHITE, // Down 
            Color.BLACK, // Left 
            Color.YELLOW // Right
        });
        Xform cubeXform200 = new Xform();
        cubeXform200.setScale(sizeCubo * 0.99);
        cubeXform200.setTranslate(0.65 * sizeCubo, 0, 0);
        cubeXform200.getChildren().add(cube200);
        parent.getChildren().add(cubeXform200);

        /*cubo mio
        Node cube500 = Cubo.create(new Color[] { 
            Color.RED,    // Front
            Color.BLUE,  // Back
            Color.BLACK,  // Up 
            Color.WHITE,  // Down 
            Color.BLACK,  // Left 
            Color.GREEN   // Right
        });
        Xform cubeXform500 = new Xform();
        cubeXform500.setScale(size * 0.99);
        cubeXform500.setTranslate(3 * size,0,0);
        cubeXform500.getChildren().add(cube500);
        parent.getChildren().add(cubeXform500);*/
    }

    private void crearCubo() {
        Integer cont = 0;
        Double posX = -1.50;
        Double posY = -1.50;
        Double posZ = -1.50;
        for (int i = 0; i < 3; i++) {
            posY = -1.50;
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    Cubo cub = new Cubo(cont, posX, posY, posZ);
                    cont++;
                    cubo[i][k][j] = cub;
                    posX += 1.05;
                }
                posY += 1.05;
                posX = -1.50;
            }
            posZ += 1.05;

        }
    }
}
