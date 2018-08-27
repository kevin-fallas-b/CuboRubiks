/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import cuborubiks.CuboRubiks;
import cuborubiks.controller.Controller;
import cuborubiks.controller.PantInicioController;
import javafx.scene.layout.AnchorPane;

public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();

    private FlowController() {
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }
    public void deleteHashMap()
    {
        loaders.clear();
    }
    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma) {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(CuboRubiks.class.getResource("view/" + name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }

    public void goMain() {
        try {
            this.mainStage.setScene(new Scene(FXMLLoader.load(CuboRubiks.class.getResource("view/PantPrincipal.fxml"), this.idioma),1024,768,true));
            this.mainStage.setTitle("Cubo Rubiks - KF");
            this.mainStage.getIcons().add(new Image("cuborubiks/resources/icono.png"));
            this.mainStage.show();
            this.mainStage.setResizable(false);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goView(String viewName) {
        goView(viewName, "Center", null);
    }

    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        stage.setResizable(false);
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                ((AnchorPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().clear();
                ((AnchorPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(loader.getRoot());
                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                break;
            default:
                break;
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
        stage.setResizable(false);
    }

    public void goViewInWindow(String viewName) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage;
        if(viewName=="SplashScreen")
        {
            stage=new Stage(StageStyle.UNDECORATED);
        }
        else
        {
           stage=new Stage();
        }
        stage.getIcons().add(new Image("cuborubiks/resources/icono.png"));
        stage.setTitle("Cubo Rubiks - KF");
        stage.setResizable(false);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void goViewInWindowModal(String viewName, Stage parentStage, Boolean resizable) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("cuborubiks/resources/icono.png"));
        stage.setTitle("Cubo Rubiks - KF");
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();

    }

    //No habia ningun metodo que cambiara de escena sin cambiar de ventana, esto lo soluciona y mantiene uso de FlowController
    public void goViewSameWindow(String scene, Button bot) throws IOException {
        Parent parent = FXMLLoader.load(CuboRubiks.class.getResource("view/" + scene + ".fxml"));
        Scene sceneToGo = new Scene(parent);
        Stage stage = ((Stage) bot.getScene().getWindow());
        stage.setScene(sceneToGo);
        stage.show();
    }

    /*
    //Tengo problemas abriendo una pantalla que ya habia abierto y cerre, entonces voy a hacerlo a la antigua 
    public void goViewInWindowALaAntigua(String scene) throws IOException {
        Parent parent = FXMLLoader.load(CuboRubiks.class.getResource("view/" + scene + ".fxml"));
        Scene sceneToGo = new Scene(parent);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("cuborubiks/resources/icono.png"));
        stage.setTitle("Cubo Rubiks- KF");
        stage.setResizable(false);
        stage.setScene(sceneToGo);
        stage.show();
    }*/

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }

    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }
    public Stage getMainStage(){
        return this.mainStage;
    }

}
