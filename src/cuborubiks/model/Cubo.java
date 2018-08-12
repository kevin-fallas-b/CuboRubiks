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
/*
esta es a clase cubo, cada objeto de esta clase es un cubo rubiks 3x3
tiene una lista doblemente enlazada de pasos realizados y tiene metodos privados que realizan un movimiento en una cara del cubo 
y tiene una funcion publica que recibe de parametro un string que usa un switch para mover el cubo
esa ultima funcion se ejecuta cuando se presiona un boton en pantallla
 */
public class Cubo {

    private Integer posiciones[][][] = new Integer[3][3][3];

    private enum Integer {
        ROJO, AZUL, BLANCO, AMARILLO, VERDE, NARANJA
    };

    public void mover(String direccion) {
        switch (direccion) {
            case "fr":
                //aqui llamo la funcion privada de mover front y right, esa funcion se encarga de agregar a la lista de movimientos realizados de mover las posiciones en el cubo
                //despues de mover las posiciones deberia actualizar pantalla
        }
    }
}
