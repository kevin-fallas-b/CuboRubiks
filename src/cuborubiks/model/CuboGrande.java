/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

import java.util.Date;

/**
 *
 * @author Kevin F
 */
public class CuboGrande {

    CuboPeq cubo[][][] = new CuboPeq[3][3][3];
    Date fecha;//fecha se utiliza para cuando se guarda la partida, caso contrario es NULL
    String nombre;//solo se pide el nombre cuando se guarda la partida
    Integer tiempo = 0;// guarda tiempo en segundos
    Movimiento movimientos;//Lista enlazada que me lleva los movimientos realizados

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
                    posX += 1.05;
                }
                posY += 1.05;
                posX = -1.50;
            }
            posZ += 1.05;

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
            while (aux != null) {
                aux = aux.getmSig();
            }
            aux.setmSig(movNuevo);
        }
        //girar cubo
        switch (direccion) {
            case "r":
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

}
