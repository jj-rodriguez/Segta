/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta;


import javax.swing.JFrame;
import segta.formularios.login;

/**
 *
 * @author MODERNIZACION3
 */
public class SegTA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame=new login();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
