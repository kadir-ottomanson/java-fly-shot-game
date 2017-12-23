/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Osmanoğlu
 */
public class Board extends JPanel implements ActionListener{

    private Timer timer;
    private final int genislik = 400;
    private final int yukseklik = 300;
    private int Delay=10;
    private double aci=0;
    private Varil varil;
    private Sinek sinek;
    private int vurulan=1,sayac=0,süre=0;
    private boolean yandin=false;
   
    
    Random r=new Random();
    public Board(){
        initBoard();
        
    }
    private void initBoard(){
        addKeyListener(new KAdapter());
        addMouseMotionListener(new MAdapter());
        addMouseListener(new MAdapter());
        this.setFocusable(true);
       
        this.setPreferredSize(new Dimension(genislik,yukseklik));
        this.setBackground(Color.black);
        
        varil=new Varil(genislik/2,yukseklik-26);
       
        sinek=new Sinek(r.nextInt(genislik-32),0,genislik,yukseklik);
        initTimer();
    }
    
    private void doDraw(Graphics g){
        Graphics2D k=(Graphics2D)g; 
        
        k.setPaint(Color.WHITE);
        k.drawString("Kalan Sinek: " + (10-vurulan), 5, 15);
        
        ArrayList<Top> top = varil.getTops();

        for (Top t : top) {
            if (t.isVisible()) {
                k.drawImage(t.getImage(),t.getX(), t.getY(), this);
            }
        }
        if (sinek.isVisible()) {
        k.drawImage(sinek.getImage(), sinek.getX(), sinek.getY(),this);
        }
        if (varil.isVisible()) {
         k.rotate(aci*Math.PI/180,(genislik+varil.getW())/2,yukseklik);
        k.drawImage(varil.getImage(), varil.getX(),varil.getY(),this);
    }
    }
    
    private void hareketTop() {

        ArrayList<Top> top = varil.getTops();

        for (int i = 0; i < top.size(); i++) {

            Top t = top.get(i);

            if (t.isVisible()) {
                t.move();
            } else {
                top.remove(i);
            }
        }
    }
    
    private void hareketSinek() {

        if(sinek.isVisible()){
        sinek.move();
    }
   
    }
     public void carpisma() throws Exception {

     ArrayList<Top> top = varil.getTops();

        for (Top t: top) {

            Rectangle r1 = t.getBounds();

                Rectangle r2 = sinek.getBounds();

                if (r1.intersects(r2)) {
                    t.setVisible(false);
                    sinek.sescal();
                    sinek.setVisible(false);
                    vurulan++;
                    if(vurulan<10){
                        sinek=new Sinek(r.nextInt(genislik-32),0,genislik,yukseklik);
                    }else{
                        yandin=true;
                    }
                
            }
        }
    }
     
     private void yandin(Graphics g){
        
             String msg = "Oyun Bitti";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        
        FontMetrics fm3 = getFontMetrics(small);
        int puan=0;
        if((double)süre/1000<100){
            puan=110-(int)(double)süre/1000;
        }
        String msg3 = "Puanınız : "+puan;
       
        String msg2 =  "Geçen Süre : "+(double)süre/1000+" saniye";
        FontMetrics fm2 = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (genislik - fm.stringWidth(msg)) / 2,
                yukseklik / 2);
         g.drawString(msg2, (genislik - fm2.stringWidth(msg2)) / 2,
                yukseklik / 2+fm.getHeight());
          g.drawString(msg3, (genislik - fm3.stringWidth(msg3)) / 2,
                yukseklik / 2+fm.getHeight()+fm2.getHeight());
         
     }
     private void durdur() {
        
        if (yandin) {
            
            timer.stop();
            süre=sayac*10;
        }
    }
       
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(yandin){
            yandin(g);
        }else
        doDraw(g);
    }
    
    private void initTimer(){
        timer=new Timer(Delay,this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        durdur();
        sayac++;
        hareketTop();
        hareketSinek();
        try {
            carpisma();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        repaint();
    }
    
    private class KAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    if(aci>-90)
                        aci-=5;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                    if(aci<90)
                        aci+=5;
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                    try {
                        varil.fire(aci);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
     private class MAdapter extends MouseAdapter {

       @Override
            public void mousePressed(MouseEvent e) {
                
                 if(e.getButton()==MouseEvent.BUTTON1)
                    try {
                        varil.fire(aci);
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                 double angle = Math.atan2(e.getY() - yukseklik, e.getX() - genislik/2);
                aci =Math.toDegrees(angle)+90;
            }
    }
    
}
