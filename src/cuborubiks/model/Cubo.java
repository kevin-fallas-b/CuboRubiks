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
Clase cubo que representa un cubo pequeno*/
public class Cubo {
    
    private enum Integer {
        ROJO, AZUL, BLANCO, AMARILLO, VERDE, NARANJA, GRIS
    };
    private Integer posicion; //guardo la posicio para mas adelante tener facilidad a la hora de hace un movimiento
    private Integer lado[]= new Integer[6];
    /*
     vector que contiene el color de cada cara del cubo, recordar que cada pieza, sin importar si es centro o no tiene 6 lados
        0=frente
        1=arriba
        2=abajo
        3=izquierda
        4=derecha
        5=atras
    */
    
    //creacion del objeto 3d
}
