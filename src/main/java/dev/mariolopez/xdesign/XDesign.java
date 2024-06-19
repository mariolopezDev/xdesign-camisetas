/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dev.mariolopez.xdesign;

import dev.mariolopez.xdesign.UI.*;
import javax.swing.JFrame;

/**
 *
 * @author mariolopez
 */
public class XDesign {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        UI_Navegacion navegacion = new UI_Navegacion();
        navegacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        navegacion.setVisible(true);

    }
}
