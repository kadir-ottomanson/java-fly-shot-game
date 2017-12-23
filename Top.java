/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Osmanoğlu
 */
public class Top extends Baglanti{
    private int hiz=2;
    private int B_WIDTH,B_H;
    private double yhiz;
    private double xhiz;
    public Top(int x, int y,int w,int h,double aci) {
        super(x, y);
        B_WIDTH=2*w;
        B_H=h+30;
        yhiz=Math.cos(aci*Math.PI/180)*hiz;
        xhiz=Math.sin(aci*Math.PI/180)*hiz;
        initTop();
        
    }
    
    private void initTop() {
        
        loadImage("C:\\Users\\Osmanoğlu\\Documents\\NetBeansProjects\\projeimage\\top.png");
        getImageDimensions();        
    }
    public void move() {
        
        x+=xhiz;
        y-=yhiz;
      
        if (x > B_WIDTH || y>B_H || x<0 || y<0)
            vis = false;
    }
    
    public void sescal() throws Exception{
     
         String gongFile = "C:\\Users\\Osmanoğlu\\Documents\\NetBeansProjects\\projeimage\\top1.wav";
    InputStream in = new FileInputStream(gongFile);

    AudioStream audioStream = new AudioStream(in);

    AudioPlayer.player.start(audioStream);
  
    }
    
}
