/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.util;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 *
 * @author Kevin F
 */
public class Util {
    public static Affine getAffine(double dimCube, double d0, boolean bFaceArrow, String face){
        Affine aff;
        double d=2d*dimCube/3d;
        if(!bFaceArrow){
            aff=new Affine(new Scale(80,80,50));
            aff.append(new Translate(-d0,-d0,d0));
        } else {
            aff=new Affine(new Scale(3,3,3));
            aff.append(new Translate(0,-d0,0));
        }
        switch(face){
            case "f": 
            case "fi":  aff.prepend(new Rotate(face.equals("f")?90:-90,Rotate.X_AXIS));
                        aff.prepend(new Rotate(face.equals("f")?45:-45,Rotate.Z_AXIS));
                        aff.prepend(new Translate(0,0,dimCube/2d));
                        break;
            case "b": 
            case "bi":  aff.prepend(new Rotate(face.equals("bi")?90:-90,Rotate.X_AXIS));
                        aff.prepend(new Rotate(face.equals("bi")?45:-45,Rotate.Z_AXIS));
                        aff.prepend(new Translate(0,0,-dimCube/2d));
                        break;
            case "r":  
            case "ri":  aff.prepend(new Rotate(face.equals("ri")?90:-90,Rotate.Z_AXIS));
                        aff.prepend(new Rotate(face.equals("ri")?45:-45,Rotate.X_AXIS));
                        aff.prepend(new Translate(dimCube/2d,0,0));
                        break;
            case "l":  
            case "li":  aff.prepend(new Rotate(face.equals("l")?90:-90,Rotate.Z_AXIS));
                        aff.prepend(new Rotate(face.equals("l")?45:-45,Rotate.X_AXIS));
                        aff.prepend(new Translate(-dimCube/2d,0,0));
                        break;
            case "u":   
            case "ui":  aff.prepend(new Rotate(face.equals("ui")?180:0,Rotate.Z_AXIS));
                        aff.prepend(new Rotate(face.equals("ui")?45:-45,Rotate.Y_AXIS));
                        aff.prepend(new Translate(0,dimCube/2d,0));
                        break;
            case "d": 
            case "di":  aff.prepend(new Rotate(face.equals("d")?180:0,Rotate.Z_AXIS));
                        aff.prepend(new Rotate(face.equals("d")?45:-45,Rotate.Y_AXIS));
                        aff.prepend(new Translate(0,-dimCube/2d,0));
                        break;
            case "z": 
            case "zi":  aff.prepend(new Rotate(face.equals("zi")?180:0,Rotate.Y_AXIS));
                        aff.prepend(new Rotate(face.equals("zi")?45:-45,Rotate.Z_AXIS));
                        aff.prepend(new Translate(0,0,d));
                        break;
            case "x":  
            case "xi":  aff.prepend(new Rotate(face.equals("x")?90:-90,Rotate.Y_AXIS));
                        aff.prepend(new Rotate(face.equals("xi")?45:-45,Rotate.X_AXIS));
                        aff.prepend(new Translate(d,0,0));
                        break;
            case "y":   
            case "yi":  aff.prepend(new Rotate(face.equals("yi")?90:-90,Rotate.X_AXIS));
                        aff.prepend(new Rotate(face.equals("yi")?45:-45,Rotate.Y_AXIS));
                        aff.prepend(new Translate(0,d,0));
                        break;
        }
        return aff;
    }
    
    public static PhongMaterial getMaterial(String face){
        PhongMaterial arrowMat = new PhongMaterial();
        arrowMat.setSpecularColor(Color.WHITESMOKE);
        Color color=Color.WHITE;
        switch(face){
            case "f": 
            case "fi": color=cuborubiks.CuboRubiks.colores[0].brighter();
                        break;
            case "b": 
            case "bi":  color=cuborubiks.CuboRubiks.colores[0].brighter();
                        break;
            case "r":  
            case "ri":  color=cuborubiks.CuboRubiks.colores[5].brighter();
                        break;
            case "l":  
            case "li":  color=cuborubiks.CuboRubiks.colores[5].brighter();
                        break;
            case "u":   
            case "ui":  color=cuborubiks.CuboRubiks.colores[1].brighter();
                        break;
            case "d": 
            case "di":  color=cuborubiks.CuboRubiks.colores[1].brighter();
                        break;
            case "z": 
            case "zi":  color=cuborubiks.CuboRubiks.colores[0].brighter();
                        break;
            case "x":  
            case "xi":  color=cuborubiks.CuboRubiks.colores[5].brighter();
                        break;
            case "y":   
            case "yi":  color=cuborubiks.CuboRubiks.colores[1].brighter();
                        break;
        }
        arrowMat.setDiffuseColor(color);
        return arrowMat;
    }
    
    public static Point3D getAxis(String face){
        Point3D p=new Point3D(0,0,0);
        switch(face.substring(0,1)){
            case "l":  
            case "m":  p=new Point3D(-1,0,0); 
                       break;
            case "r":  p=new Point3D(1,0,0); 
                       break;
            case "u":  p=new Point3D(0,1,0); 
                       break;
            case "e":  
            case "d":  p=new Point3D(0,-1,0); 
                       break;
            case "f":  
            case "s":  p=new Point3D(0,0,1); 
                       break;
            case "b":  p=new Point3D(0,0,-1); 
                       break;
            case "x":  p=new Point3D(1,0,0); 
                       break;
            case "y":  p=new Point3D(0,1,0); 
                       break;
            case "z":  p=new Point3D(0,0,1); 
                       break;
        }
        return p;
    }
    
    public static int getCenter(String face){
        int c=0;
        switch(face.substring(0,1)){
            case "l":  c=12; break;
            case "m":  c=13; break;
            case "r":  c=14; break;
            case "u":  c=10; break;
            case "e":  c=13; break;
            case "d":  c=16; break;
            case "f":  c=4;  break;
            case "s":  c=13; break;
            case "b":  c=22; break;
        }
        return c;
    }

}
