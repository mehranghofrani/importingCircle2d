/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Mactabi
 */
public class ButtonGroupPanel extends JPanel implements MouseMotionListener, MouseListener{
    
    private boolean moving;
    private Button[] buttons=new Button[6];
    private Button centerBtn;
    private boolean closed;
    private double buttonsRR;

    public ButtonGroupPanel(int width) {
        closed=true;
        setBackground(Color.red);
        buttonsRR=width/6;
//        setSize(width, (int)(3*Math.sqrt(3)*buttonsRR));
        setSize(width, (int)(6*buttonsRR));
        addMouseListener(this);
        addMouseMotionListener(this);
        System.out.println(getWidth()+" "+getHeight());
        init();
        
        
       
    }
    private void init(){
        centerBtn=new Button("+",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                if(closed)
                    open();
                else
                    close();
                
                System.out.println("moves again");
                
            
            }
        });
        buttons[0]=new Button("سلفی",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
        buttons[1]=new Button("از من بپرس",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
        buttons[2]=new Button("عکس",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
        buttons[3]=new Button("دوربین هوشمند",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
        buttons[4]=new Button("فیلم",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
        buttons[5]=new Button("کاتالوگ",getWidth()/2,getHeight()/2,buttonsRR,new actionListener() {
            @Override
            public void actionPerformed() {
                
            }
        });
    }
    public void close(){
        moving=true;
        for(int i=0;i<=5;i++){
            buttons[i].move(getWidth()/2,getHeight()/2);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {      
                int n=0;
                while(buttons[5].moving){
                    System.out.println("waiting to complete close"+n);
                    n++;
                }
                System.out.println("completed close");
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
                while(buttons[5].moving){
                    System.out.println("waiting to complete open"+n);
                    n++;
                }
                System.out.println("completed open");
                
                
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
        centerBtn.draw(g);
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        centerBtn.mouseClicked(e);
        for(int i=0;i<=5;i++){
            buttons[i].mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    
}
