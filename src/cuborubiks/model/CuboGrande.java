/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

import cuborubiks.CuboRubiks;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Kevin F
 */
public class CuboGrande {

    public CuboPeq cubo[][][] = new CuboPeq[3][3][3];
    private Date fecha;//fecha se utiliza para cuando se guarda la partida, caso contrario es NULL
    private String nombre;//solo se pide el nombre cuando se guarda la partida
    private Integer tiempo = 0;// guarda tiempo en segundos
    private Movimiento movimientos;//Lista enlazada que me lleva los movimientos realizados

    private List<CuboPeq> cambios = new ArrayList<>();

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
                    cubo[i][k][j] = cub;
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
        imprimirCubo();
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
        CuboPeq cuboTemp[][][] = new CuboPeq[3][3][3];
        Point3D axis = new Point3D(0, 0, 0);//eje sobre el cual se gira a la hora de hacer la animacion, cada movimiento lo cambia
        int t = 0;
        //recordar, profundidad, altura, ancho, y ancho esta al reves
        switch (direccion) {
            case "r":
                axis = new Point3D(-1, 0, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[t][k][2] = cubo[k][i][2];
                        cambios.add(cubo[k][i][0]);
                    }
                    t++;
                }

                break;
            case "ri":
                axis = new Point3D(1, 0, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[k][t][0] = cubo[i][k][0];
                        cambios.add(cubo[i][k][0]);
                    }
                    t++;
                }

                break;
            case "l":
                axis = new Point3D(1, 0, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[k][t][2] = cubo[i][k][2];
                        cambios.add(cubo[i][k][2]);
                    }
                    t++;
                }
                
                break;
            case "li":
                axis = new Point3D(-1, 0, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[t][k][0] = cubo[k][i][0];
                        cambios.add(cubo[k][i][0]);
                    }
                    t++;
                }
                
                break;
            case "b":
                axis = new Point3D(0, 0, 1);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[2][t][k] = cubo[2][k][i];
                        cambios.add(cubo[2][k][i]);
                    }
                    t++;
                }
                
                break;
            case "bi":
                axis = new Point3D(0, 0, -1);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[2][k][t] = cubo[2][i][k];
                        cambios.add(cubo[2][i][k]);
                    }
                    t++;
                }
                
                break;
            case "d":
                axis = new Point3D(0, -1, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[k][0][t] = cubo[i][0][k];
                        cambios.add(cubo[i][0][k]);
                    }
                    t++;
                }
                
                break;
            case "di":
                axis = new Point3D(0, 1, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[t][0][k] = cubo[k][0][i];
                        cambios.add(cubo[k][0][i]);
                    }
                    t++;
                }
                
                break;
            case "f":
                axis = new Point3D(0, 0, -1);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[0][k][t] = cubo[0][i][k];
                        cambios.add(cubo[0][i][k]);
                    }
                    t++;
                }
                
                break;
            case "fi":
                axis = new Point3D(0, 0, 1);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[0][t][k] = cubo[0][k][i];
                        cambios.add(cubo[0][k][i]);
                    }
                    t++;
                }
                break;
            case "u":
                axis = new Point3D(0, 1, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[t][2][k] = cubo[k][2][i];
                        cambios.add(cubo[k][2][i]);
                    }
                    t++;
                }
                
                break;
            case "ui":
                axis = new Point3D(0, -1, 0);
                for (int i = 2; i >= 0; i--) {
                    for (int k = 0; k < 3; k++) {
                        cuboTemp[k][2][t] = cubo[i][2][k];
                        cambios.add(cubo[i][2][k]);
                    }
                    t++;
                }
                
                break;
        }
        girar(axis);
        //guardar, pasar del cubo temporal al real
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                for (int g = 0; g < 3; g++) {
                    if (cuboTemp[i][k][g] != null) {
                        cubo[i][k][g] = cuboTemp[i][k][g];
                    }
                }
            }
        }
        //imprimirCubo();
    }

    public void moverDeInternet(String dir) {
        int t = 0;
        Point3D eje = new Point3D(0, 0, 0);
        CuboPeq tempCube[][][] = new CuboPeq[3][3][3];
        for (int y = 2; y >= 0; --y) {
            for (int x = 0; x < 3; x++) {
                switch (dir) {
                    case "L":
                        tempCube[x][t][0] = cubo[y][x][0];
                        break;
                    case "Li":
                        tempCube[t][x][0] = cubo[x][y][0];
                        break;
                    case "M":
                        tempCube[x][t][1] = cubo[y][x][1];
                        break;
                    case "Mi":
                        tempCube[t][x][1] = cubo[x][y][1];
                        break;
                    case "R":
                        tempCube[t][x][2] = cubo[x][y][2];
                        break;
                    case "Ri":
                        tempCube[x][t][2] = cubo[y][x][2];
                        break;
                    case "U":
                        tempCube[t][0][x] = cubo[x][0][y];
                        break;
                    case "Ui":
                        tempCube[x][0][t] = cubo[y][0][x];
                        break;
                    case "E":
                        tempCube[x][1][t] = cubo[y][1][x];
                        break;
                    case "Ei":
                        tempCube[t][1][x] = cubo[x][1][y];
                        break;
                    case "D":
                        tempCube[x][2][t] = cubo[y][2][x];
                        break;
                    case "Di":
                        tempCube[t][2][x] = cubo[x][2][y];
                        break;
                    case "F":
                        tempCube[0][x][t] = cubo[0][y][x];
                        break;
                    case "Fi":
                        tempCube[0][t][x] = cubo[0][x][y];
                        break;
                    case "S":
                        tempCube[1][x][t] = cubo[1][y][x];
                        break;
                    case "Si":
                        tempCube[1][t][x] = cubo[1][x][y];
                        break;
                    case "B":
                        tempCube[2][t][x] = cubo[2][x][y];
                        break;
                    case "Bi":
                        tempCube[2][x][t] = cubo[2][y][x];
                        break;
                }
            }
            t++;
        }

    }

    private void girar(Point3D eje) {

        for (int i = 0; i < cambios.size(); i++) {
            cambios.get(i).rotarCuboPeq(eje);
        }
        cambios.clear();//limpiar la lista de cambios
    }

    public Movimiento getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimientos) {
        this.movimientos = movimientos;
    }

    public CuboPeq getCuboPeq(Integer x, Integer y, Integer z) {
        return cubo[x][y][z];
    }

    private void imprimirCubo() {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                for (int g = 0; g < 1; g++) {
                    System.out.println("  " + cubo[i][k][g].getNumCubo() + " " + cubo[i][k][g + 1].getNumCubo() + " " + cubo[i][k][g + 2].getNumCubo());
                }

            }
            System.out.println("\n\n");
        }
    }
}
