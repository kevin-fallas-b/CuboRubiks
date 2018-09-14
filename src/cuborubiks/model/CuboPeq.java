/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuborubiks.model;

import cuborubiks.controller.PantPrincipalController;
import cuborubiks.util.Xform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import javafx.scene.transform.Rotate;

/**
 *
 * @author kevin
 */
/*
Clase cubo que representa un cubo pequeno*/
public class CuboPeq {

    private Double posX; //guardo la posicio para mas adelante tener facilidad a la hora de hace un movimiento
    private Double posY;
    private Double posZ;
    private Color colorLado[] = new Color[6];
    final int sizeCubo = 50;//variable local que no cambia pero la uso para ser mas ordenado
    private Xform cubeXform000 = new Xform();

    /*
     vector que contiene el color de cada cara del cubo, recordar que cada pieza, sin importar si es centro o no tiene 6 lados
        0=frente
        1=arriba
        2=abajo
        3=izquierda
        4=derecha
        5=atras
     */
    public CuboPeq() {

    }

    public CuboPeq(Integer posicion, Double posX, Double posY, Double posZ) {
        // esta funcion me agrega un cubo a la pantalla, lo que hace es llamar funciones internas que
        // fueron importadas
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        Node cube;
        if (posicion < 9) {
            cube = createInterno(new Color[]{
                Color.RED, // Front
                Color.ORANGE, // Back
                Color.YELLOW, // Up 
                Color.WHITE, // Down 
                Color.BLUE, // Left 
                Color.GREEN // Right
            });
        } else {
            cube = createInterno(new Color[]{
                Color.WHITE, // Front
                Color.BLUE, // Back
                Color.WHITE, // Up 
                Color.GREEN, // Down 
                Color.BLACK, // Left 
                Color.YELLOW // Right
            });

        }

        cubeXform000.setScale(sizeCubo * 1.00);//tammano del cubo
        cubeXform000.setTranslate(posX * sizeCubo, posY * sizeCubo, posZ * sizeCubo);//posicion del cubo
        cubeXform000.getChildren().add(cube);
        PantPrincipalController.world.getChildren().add(cubeXform000);//agregar cubo a pantalla
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public Double getPosZ() {
        return posZ;
    }

    public void setPosZ(Double posZ) {
        this.posZ = posZ;
    }

    //creacion del objeto 3d
    /**
     * Creates a new unit cube, where the sides have the colors from the given
     * array. The array must have at least 6 elements, where the colors will be
     * used as follows: 0 = Front 1 = Bottom 2 = Up 3 = Down 4 = Left 5 = Right
     *
     * @param colors The colors
     * @return The color cube
     */
    public Node createInterno(Color colors[]) {//se llamaba create, para mantenerme con OOP lo voy a hacer interno
        Point3D points[] = new Point3D[8];
        for (int i = 0; i < 8; i++) {
            double x = (i & 0x1) == 0 ? 0.0 : 1.0;
            double y = (i & 0x2) == 0 ? 0.0 : 1.0;
            double z = (i & 0x4) == 0 ? 0.0 : 1.0;
            points[i] = new Point3D(x, y, z);
        }

        // F = Front
        // B = Back 
        // U = Up   
        // D = Down 
        // L = Left 
        // R = Right
        TriangleMesh meshF = createPlane(
                points[0], points[1], points[3], points[2], new Point3D(0, 0, -1));
        TriangleMesh meshB = createPlane(
                points[6], points[7], points[5], points[4], new Point3D(0, 0, 1));

        TriangleMesh meshU = createPlane(
                points[2], points[3], points[7], points[6], new Point3D(0, 1, 0));

        TriangleMesh meshD = createPlane(
                points[4], points[5], points[1], points[0], new Point3D(0, -1, 0));

        TriangleMesh meshL = createPlane(
                points[4], points[0], points[2], points[6], new Point3D(-1, 0, 0));
        TriangleMesh meshR = createPlane(
                points[1], points[5], points[7], points[3], new Point3D(1, 0, 0));

        Group group = new Group();
        group.getChildren().add(createPlane(meshF, colors[0]));
        group.getChildren().add(createPlane(meshB, colors[1]));
        group.getChildren().add(createPlane(meshU, colors[2]));
        group.getChildren().add(createPlane(meshD, colors[3]));
        group.getChildren().add(createPlane(meshL, colors[4]));
        group.getChildren().add(createPlane(meshR, colors[5]));

        return group;
    }

    private static TriangleMesh createPlane(Point3D p0, Point3D p1, Point3D p2, Point3D p3, Point3D normal) {
        TriangleMesh mesh = new TriangleMesh();
        mesh.setVertexFormat(VertexFormat.POINT_NORMAL_TEXCOORD);
        mesh.getPoints().addAll(new float[]{
            (float) p0.getX(), (float) p0.getY(), (float) p0.getZ(),
            (float) p1.getX(), (float) p1.getY(), (float) p1.getZ(),
            (float) p2.getX(), (float) p2.getY(), (float) p2.getZ(),
            (float) p3.getX(), (float) p3.getY(), (float) p3.getZ(),});
        mesh.getNormals().addAll(new float[]{
            (float) normal.getX(), (float) normal.getY(), (float) normal.getZ(),
            (float) normal.getX(), (float) normal.getY(), (float) normal.getZ(),
            (float) normal.getX(), (float) normal.getY(), (float) normal.getZ(),
            (float) normal.getX(), (float) normal.getY(), (float) normal.getZ(),});
        mesh.getTexCoords().addAll(new float[]{
            0, 0,
            0, 0,
            0, 0,
            0, 0,});
        mesh.getFaces().addAll(new int[]{
            3, 3, 3,
            1, 1, 1,
            0, 0, 0,
            3, 3, 3,
            2, 2, 2,
            1, 1, 1,});
        return mesh;
    }

    private static Node createPlane(Mesh mesh, Color color) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color.brighter());

        MeshView meshView = new MeshView(mesh);
        meshView.setMaterial(material);
        return meshView;
    }

    public void moverCuboPeq(Double posX, Double posY, Double posZ) {
        for (double i = this.posX; i > posX; i -= 0.01) {
            cubeXform000.setTranslate(i * sizeCubo, posY * sizeCubo, posZ * sizeCubo);
            System.out.println("i: " + i);
        }
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public void rotarCuboPeq() {
        System.out.println("pos X: "+posX);
        //cubeXform000.setRotationAxis(new Point3D(posX*sizeCubo,posY*sizeCubo,posZ*sizeCubo));
        //cubeXform000.setr
        cubeXform000.setRc(cubeXform000.getAngle() + 90.00,0.00,1.00,-0.00);
        System.out.println("pos X: "+posX);
    }
}
