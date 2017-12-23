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
import javax.swing.ImageIcon;

public class Baglanti {
    
     protected Image image;
      protected int width;
    protected int height;
     protected double x;
    protected double y;
    protected boolean vis;
    
    public Baglanti(int x,int y){
        this.x=x;
        this.y=y;
        vis=true;
    }
     protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    public Image getImage() {
        return image;
    }
    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public int getW(){
        return width;
    }
    public int getH(){
        return height;
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, width, height);
    }
    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
    
}
