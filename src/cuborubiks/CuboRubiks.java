/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks;

import javafx.application.Application;
import javafx.stage.Stage;
import cuborubiks.util.FlowController;
import javafx.scene.paint.Color;

/**
 *
 * @author Kevin F
 */
public class CuboRubiks extends Application {
    public static Color colores[] = new Color[]{Color.GREEN,Color.BLUE,Color.WHITE,Color.YELLOW,Color.RED,Color.ORANGERED};
    /*
    vector que contiene el color de cada cara del cubo, recordar que cada pieza, sin importar si es centro o no tiene 6 lados
        0=frente
        1=atras
        2=arriba
        3=abajo
        4=derecha
        5=izquieda
    */
    @Override
    public void start(Stage stage) throws Exception {
        FlowController.getInstance().InitializeFlow(stage, null);
        FlowController.getInstance().goViewInWindow("PantInicio");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
