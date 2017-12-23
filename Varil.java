/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev2;

/**
 *
 * @author Osmanoğlu
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Varil extends Baglanti{
   
     private ArrayList<Top> top;
    private int w,h;
    public Varil(int x,int y){
        super(x,y);
        this.w=x;
        this.h=y;
       initVaril();
    }
     private void initVaril() {
         top = new ArrayList<>();
        loadImage("C:\\Users\\Osmanoğlu\\Documents\\NetBeansProjects\\projeimage\\varil.png");
        getImageDimensions();
    }
    public void fire(double aci) throws Exception {
        Top t=new Top((int)x+width/3,(int)y+20,w,h,aci);
        t.sescal();
        top.add(t);
    }
    public ArrayList getTops() {
        return top;
    }
}
