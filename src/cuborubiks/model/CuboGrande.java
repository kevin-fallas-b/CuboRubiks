/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

import java.util.Date;
import javafx.scene.paint.Color;

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
        Double posY = -1.50;
        Double posZ = -1.50;
        for (int i = 0; i < 3; i++) {
            posY = -1.50;
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    CuboPeq cub = new CuboPeq(cont, posX, posY, posZ);
                    cont++;
                    cubo[i][k][j] = cub;
                    posX += 1.10;//05
                }
                posY += 1.10;//05
                posX = -1.50;//50
            }
            posZ += 1.10;//05

        }
    }

    public void mover(String direccion) {
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
        CuboPeq auxCubo[][] = new CuboPeq[3][3];
        switch (direccion) {
            case "r":
                for (int i = 0; i < 3; i++) {
                    for (int k = 0; k < 3; k++) {
                        System.out.println("cubo:[2]["+k+"]["+ i+"]    pos:   "+cubo[2][k][i].getPosY());
                        
                        /*cubo[2][k][i] = auxCubo[i][2 - k];
                        cubo[2][i][2-k].movenrCuboPeq(cubo[2][k][i].getPosX(), cubo[2][k][i].getPosY(), cubo[2][k][i].getPosZ());
                        cubo[2][k][i].moverCuboPeq(auxCubo[i][2-k].getPosX(), auxCubo[i][2-k].getPosY(), auxCubo[i][2-k].getPosZ());*/
                    }
                }
                break;
            case "ri":
                break;
            case "l":
                break;
            case "li":
                break;
            case "b":
                break;
            case "bi":
                break;
            case "d":
                break;
            case "di":
                break;
            case "f":
                break;
            case "fi":
                break;
            case "u":
                break;
            case "ui":
                break;
        }
    }

    public Movimiento getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimientos) {
        this.movimientos = movimientos;
    }
    
    public CuboPeq getCuboPeq(Integer x, Integer y, Integer z){
        return cubo[x][y][z];
    }
}
