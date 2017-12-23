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
public class Sinek extends Baglanti{
    
      private double yhiz;
    private double xhiz;
    private int w,h;
    public Sinek(int x, int y,int w,int h) {
        super(x, y);
        this.w=w;
        this.h=h;
        double aci=Math.random()*66+25;
        double hiz=Math.random()*1+1;
        xhiz=Math.cos(aci*Math.PI/180)*hiz;
        yhiz=Math.sin(aci*Math.PI/180)*hiz;
        
        initSinek();
    }

    private void initSinek() {

        loadImage("C:\\Users\\Osmanoğlu\\Documents\\NetBeansProjects\\projeimage\\sinek.png");
        getImageDimensions();
    }

    public void move() {
        x+=xhiz;
        y+=yhiz;
        if(x+width>w || x<0){
            xhiz=-xhiz;
        }
        if(y+height>h || y<0){
            yhiz=-yhiz;
        }
    }
    public void sescal() throws Exception{
        String gongFile = "C:\\Users\\Osmanoğlu\\Documents\\NetBeansProjects\\projeimage\\sinek.wav";
    InputStream in = new FileInputStream(gongFile);

    AudioStream audioStream = new AudioStream(in);

    AudioPlayer.player.start(audioStream);
    }
}
