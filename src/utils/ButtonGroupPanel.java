/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import pages.AskPage;
import pages.CatalogPage;
import pages.ImagePage;
import pages.NavPage;
import pages.SmartCamera;
import pages.VideoPage;
import pages.camera.uiComponents.pages.ImageCapturingPage;
import pages.camera.uiComponents.uiInterfaces.ActivityPage;

/**
 *
 * @author Mactabi
 */
public class ButtonGroupPanel extends JPanel implements MouseListener, MouseMotionListener{
    
    private boolean moving;
    private Button[] buttons=new Button[6];
    private Button centerBtn;
    private boolean closed;
    private double buttonsRR;
    boolean anyButtonPressed;

    public ButtonGroupPanel(int width) {
        closed=true;
        setOpaque(false);
//        setBackground(Color.red);
        buttonsRR=width/6;
//        setSize(width, (int)(3*Math.sqrt(3)*buttonsRR));
        setSize(width, (int)(6*buttonsRR));
        addMouseListener(this);
        addMouseMotionListener(this);
        init();
        
        
        
        
//        String fonts[] = 
//      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//
//    for ( int i = 0; i < fonts.length; i++ )
//    {
//      System.out.println(fonts[i]);
//    }
        
       
    }
    private void init(){
        centerBtn=new Button("+",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                if(!moving)
                if(closed)
                    open();
                else
                    close();
                
                
                
            
            }
        });
        buttons[0]=new Button("سلفی",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                centerBtn.listener.actionPerformed(centerBtn);
                importingcircle2d.ImportingCircle2d.getInstance().showPage(ImageCapturingPage.getInstance());
                NavPage.getInstance().setVisible(false);
                
                
            }
        });
        buttons[1]=new Button("از من بپرس",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                centerBtn.listener.actionPerformed(centerBtn);
                importingcircle2d.ImportingCircle2d.getInstance().showPage(AskPage.getInstance());
                
            }
        });
        buttons[2]=new Button("تصاویر",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                importingcircle2d.ImportingCircle2d.getInstance().showPage(ImagePage.getInstance());
                centerBtn.listener.actionPerformed(centerBtn);
            }
        });
        buttons[3]=new Button("دوربین هوشمند",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                centerBtn.listener.actionPerformed(centerBtn);
                importingcircle2d.ImportingCircle2d.getInstance().showPage(SmartCamera.getInstance());
            }
        });
        buttons[4]=new Button("فیلم",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                centerBtn.listener.actionPerformed(centerBtn);
                importingcircle2d.ImportingCircle2d.getInstance().showPage(VideoPage.getInstance());
            }
        });
        buttons[5]=new Button("کاتالوگ",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed(Button btn) {
                centerBtn.listener.actionPerformed(centerBtn);
                importingcircle2d.ImportingCircle2d.getInstance().showPage(CatalogPage.getInstance());
//                NavPage.getInstance().setVisible(false);
            }
        });
        
        int smallestFontSize=(int)buttons[0].fontSize;
        for(int i=0;i<6;i++){
            if(smallestFontSize>(int)buttons[i].fontSize)
                smallestFontSize=(int)buttons[i].fontSize;
        }
        
        
        for(int i=0;i<6;i++){
            buttons[i].setFirstColor(new Color(100, 100, 100,100));
            buttons[i].setSecondColor(new Color(100, 100, 100,255));
        }
        centerBtn.setFirstColor(new Color(100, 100, 100,100));
        centerBtn.setSecondColor(new Color(100, 100, 100,255));
        centerBtn.setFont("B Nazanin Outline");
        
        
        
        
        
        for(int i=0;i<6;i++){
            buttons[i].setVisible(false);
        }
        
        
        
        
        
        //should be last line for safe
        for(int i=0;i<6;i++){
            buttons[i].setFontSize(smallestFontSize);
        }
        centerBtn.setFontSize(smallestFontSize);
    }
    public void close(){
        moving=true;
        centerBtn.setText("+");
        for(int i=0;i<=5;i++){
            buttons[i].move(getWidth()/2,getHeight()/2);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {      
                int n=0;
                while(buttons[0].moving||buttons[1].moving||buttons[2].moving||
                        buttons[3].moving||buttons[4].moving||buttons[5].moving){
                    n++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonGroupPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("completed close"+n);
                for(int i=0;i<=5;i++){
                    buttons[i].setVisible(false);
                    
                }
                ButtonGroupPanel.this.moving=false;
                ButtonGroupPanel.this.closed=true;
            }
        },"closer").start();
    }
    public void open(){
        moving=true;
        centerBtn.setText("-");
        for(int i=0;i<=5;i++){
            buttons[i].setVisible(true);
        }
        for(int i=0;i<=5;i++){
            double deg=(i/6d)*(2*Math.PI)+Math.PI/6;
            buttons[i].move((int)(getWidth()/2+Math.cos(deg)*(2*buttonsRR)),
                    (int)(getHeight()/2-Math.sin(deg)*(2*buttonsRR)));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {                
                int n=0;
                while(buttons[0].moving||buttons[1].moving||buttons[2].moving||
                        buttons[3].moving||buttons[4].moving||buttons[5].moving){
                    n++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonGroupPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("completed open"+n);
                
                
                ButtonGroupPanel.this.moving=false;
                ButtonGroupPanel.this.closed=false;
            }
        },"opener").start();
        
        
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<=5;i++){
            buttons[i].draw(g);
        }
        //homeBtn last...so when closing paints on others
        centerBtn.draw(g);
        
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
        centerBtn.mouseClicked(e);
        for(int i=0;i<=5;i++){
            buttons[i].mouseClicked(e);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        centerBtn.mousePressed(e);
        if(centerBtn.pressed)
            anyButtonPressed=true;
        for(int i=0;i<=5;i++){
            buttons[i].mousePressed(e);
            if(buttons[i].pressed)
                anyButtonPressed=true;
        }
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        centerBtn.mouseReleased(e);
        for(int i=0;i<=5;i++){
            buttons[i].mouseReleased(e);
        }
        anyButtonPressed=false;
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!anyButtonPressed)
            importingcircle2d.ImportingCircle2d.getInstance().currentPage.dispatchEvent(e);
        centerBtn.mouseMoved(e);
        for(int i=0;i<=5;i++){
            buttons[i].mouseMoved(e);
        }
        repaint();
        
    }
    
    
    
    
}
