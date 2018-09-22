/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

import cuborubiks.util.ContentModel;
import cuborubiks.CuboRubiks;
import cuborubiks.util.Model3D;
import cuborubiks.util.Rotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import cuborubiks.util.Util;

/**
 *
 * @author Kevin F
 */
public class CuboGrande {

    public CuboPeq cuboLogico[][][] = new CuboPeq[3][3][3];
    private CuboPeq cuboTemp[][][] = new CuboPeq[3][3][3];//auxiliar
    private Date fecha;//fecha se utiliza para cuando se guarda la partida, caso contrario es NULL
    private String nombre;//solo se pide el nombre cuando se guarda la partida
    private Integer tiempo = 0;// guarda tiempo en segundos
    private Movimiento movimientos;//Lista enlazada que me lleva los movimientos realizados
    private String ladoActivo="ad";//me dice cual cara del cubo esta activa para asi hacer el movimiento correcto 
    private final BooleanProperty onRotation = new SimpleBooleanProperty(false);

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public void crearCubo() {
        Integer cont = 0;
        Double posX = -1.50;
        Double posY = -1.25;
        Double posZ = -1.50;
        crearCuboConObjeto();
        for (int i = 0; i < 3; i++) {//i va hacia atras
            posY = -1.50;
            for (int k = 0; k < 3; k++) {//k va hacia arriba
                for (int j = 0; j < 3; j++) {//j va a los lados
                    //este desorden de if y else estan ahi para que solo la cara indicada sea de un color, el resto permanece negra. hace que se vea mejor el cubo
                    Color colores[] = new Color[]{Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED, Color.ORANGERED};
                    if (i == 0) {
                        colores[0] = CuboRubiks.colores[0];
                    } else {
                        colores[0] = Color.BLACK;
                    }
                    if (i == 2) {
                        colores[1] = CuboRubiks.colores[1];
                    } else {
                        colores[1] = Color.BLACK;
                    }
                    if (k == 2) {
                        colores[2] = CuboRubiks.colores[2];
                    } else {
                        colores[2] = Color.BLACK;
                    }
                    if (k == 0) {
                        colores[3] = CuboRubiks.colores[3];
                    } else {
                        colores[3] = Color.BLACK;
                    }
                    if (j == 0) {
                        colores[4] = CuboRubiks.colores[4];
                    } else {
                        colores[4] = Color.BLACK;
                    }
                    if (j == 2) {
                        colores[5] = CuboRubiks.colores[5];
                    } else {
                        colores[5] = Color.BLACK;
                    }
                    CuboPeq cub = new CuboPeq(cont, posX, posY, posZ, colores);
                    cub.setNumCubo(cont);
                    cuboLogico[i][k][j] = cub;
                    cuboTemp[i][k][j] = cuboLogico[i][k][j];
                    posX += 1.05;//1.10
                    cont++;
                }
                posY += 1.05;//1.10
                posX = -1.50;//-1.50
            }
            posZ += 1.05;//1.10

        }
    }

    public void mover(String direccion) {//metodo solo se encarga de mover logicamente los cubos y luego llama el metodo que hace la animacion
        if(isOnRotation().getValue()){
            return;
        }
        //agregar el movimiento a la lista enlazada
        Movimiento aux = movimientos;
        Movimiento movNuevo = new Movimiento();
        movNuevo.setDireccion(direccion);
        movNuevo.setmSig(null);
        if (aux == null) {
            movimientos = movNuevo;
        } else {
            while (aux.getmSig() != null) {
                aux = aux.getmSig();
            }
            aux.setmSig(movNuevo);
        }
        //girar cubo
        System.out.println("moviendo cubo con direccion: " + direccion);
        int t = 0;
        //recordar, profundidad, altura, ancho, y ancho esta al reves
        for (int y = 2; y >= 0; --y) {
            for (int x = 0; x < 3; x++) {
                switch (direccion) {
                    case "li":
                        cuboTemp[x][t][2] = cuboLogico[y][x][2];
                        break;
                    case "l":
                        cuboTemp[t][x][2] = cuboLogico[x][y][2];
                        break;
                    case "ri":
                        cuboTemp[t][x][0] = cuboLogico[x][y][0];
                        break;
                    case "r":
                        cuboTemp[x][t][0] = cuboLogico[y][x][0];
                        break;
                    case "ui":
                        cuboTemp[t][2][x] = cuboLogico[x][2][y];
                        break;
                    case "u":
                        cuboTemp[x][2][t] = cuboLogico[y][2][x];
                        break;
                    case "E":
                        cuboTemp[x][1][t] = cuboLogico[y][1][x];
                        break;
                    case "Ei":
                        cuboTemp[t][1][x] = cuboLogico[x][1][y];
                        break;
                    case "di":
                        cuboTemp[x][0][t] = cuboLogico[y][0][x];
                        break;
                    case "d":
                        cuboTemp[t][0][x] = cuboLogico[x][0][y];
                        break;
                    case "fi":
                        cuboTemp[0][x][t] = cuboLogico[0][y][x];
                        break;
                    case "f":
                        cuboTemp[0][t][x] = cuboLogico[0][x][y];
                        break;
                    case "S":
                        cuboTemp[1][x][t] = cuboLogico[1][y][x];
                        break;
                    case "Si":
                        cuboTemp[1][t][x] = cuboLogico[1][x][y];
                        break;
                    case "bi":
                        cuboTemp[2][t][x] = cuboLogico[2][x][y];
                        break;
                    case "b":
                        cuboTemp[2][x][t] = cuboLogico[2][y][x];
                        break;
                }
            }
            t++;
        }

        for (int f = 0; f < 3; f++) {
            for (int l = 0; l < 3; l++) {
                System.arraycopy(cuboTemp[f][l], 0, cuboLogico[f][l], 0, 3);
            }
        }
        
        rotarCuboJP(direccion);
    }

    public Movimiento getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimientos) {
        this.movimientos = movimientos;
    }

    public String getLadoActivo() {
        return ladoActivo;
    }

    public void setLadoActivo(String ladoActivo) {
        this.ladoActivo = ladoActivo;
    }
    
    

    /*
    todo de aqui para abajo es importado de Jpereda en un intento de cambiar el codigo por un error grafico
    yo habia hecho todo un cubo desarrollado por mi mismo, unicamente importando la clase Viewer y XForm, ambas otorgadas por oracle para facilitar el desarrollo 3d
    sin embargo a 5 dias de la entrega del proyecto no habia podido resolver un problema
    grafico a la hora de hacer rotaciones, por lo que me vi obligado (para poder continuar con el proyecto) a eliminar todo lo desarrollado por mi persona
    e importar el modelo hecho por Jose Pereda en http://jperedadnr.blogspot.com/2014/04/rubikfx-solving-rubiks-cube-with-javafx.html
    es dificil tener que desechar mas de mes y medio de investigacion y desarrollo por falta de tiempo sin embargo mucho fue lo aprendido
    y mucho de lo que ya habia programado me sirve con este nuevo modelo grafico
   */
    private final Group cube = new Group();
    private Map<String, MeshView> mapMeshes = new HashMap<>();
    private double dimCube;

    private ContentModel content;

    private Rotations rot;

    private List<Integer> order;
    private List<Integer> reorder, layer;
    private final DoubleProperty rotation = new SimpleDoubleProperty(0d);
    private Point3D axis = new Point3D(0, 0, 0);
    private ChangeListener<Number> rotMap;

    public SubScene getSubScene() {
        return content.getSubScene();
    }

    public BooleanProperty isOnRotation() {
        return onRotation;
    }

    private void crearCuboConObjeto() {
        // Import Rubik's Cube model and arrows
        Model3D model = new Model3D();
        model.importObj();
        mapMeshes = model.getMapMeshes();
        cube.getChildren().setAll(mapMeshes.values());
        dimCube = cube.getBoundsInParent().getWidth();

        // Create content subscene, add cube, set camera and lights
        content = new ContentModel(1024, 450, dimCube);
        content.setContent(cube);

        // Initialize 3D array of indexes and a copy of original/solved position
        rot = new Rotations();
        order = rot.getCube();

        // Listener to perform an animated face rotation
        rotMap = (ov, angOld, angNew) -> {
            mapMeshes.forEach((k, v) -> {
                layer.stream().filter(l -> k.contains(l.toString()))
                        .findFirst().ifPresent(l -> {
                            Affine a = new Affine(v.getTransforms().get(0));
                            a.prepend(new Rotate(angNew.doubleValue() - angOld.doubleValue(), axis));
                            v.getTransforms().setAll(a);
                        });
            });
        };
    }

    private void rotarCuboJP(String btRot) {
        if (onRotation.get()) {
            return;
        }
        onRotation.set(true);

        boolean bFace = !(btRot.startsWith("X") || btRot.startsWith("Y") || btRot.startsWith("Z"));

        // rotate cube indexes
        rot.turn(btRot);
        // get new indexes in terms of blocks numbers from original order
        reorder = rot.getCube();

        // select cubies to rotate: those in reorder different from order.
        if (!bFace) {
            layer = reorder.stream().collect(Collectors.toList());
        } else {
            AtomicInteger index = new AtomicInteger();
            layer = order.stream()
                    .filter(o -> !Objects.equals(o, reorder.get(index.getAndIncrement())))
                    .collect(Collectors.toList());
            // add central cubie
            layer.add(0, reorder.get(Util.getCenter(btRot)));
        }
        // set rotation axis            
        axis = Util.getAxis(btRot);
        // define rotation
        double angEnd = 90d * (btRot.endsWith("i") ? 1d : -1d);

        rotation.set(0d);
        rotation.addListener(rotMap);

        // create animation
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(600), e -> {
                    rotation.removeListener(rotMap);
                    onRotation.set(false);
                }, new KeyValue(rotation, angEnd)));
        timeline.playFromStart();

        // update order with last list, to start all over again in the next rotation
        order = reorder.stream().collect(Collectors.toList());

    }
    public void rotarCamara(String direccion){
        content.moverCamara(direccion);
    }
}
