/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

/**
 *
 * @author kevin
 */
//clase que me sirve para crear la lista doblemente enlazada de movimientos hechos
public class Movimiento {

    private String direccion;
    private Movimiento mSig;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Movimiento getmSig() {
        return mSig;
    }

    public void setmSig(Movimiento mSig) {
        this.mSig = mSig;
    }

}
