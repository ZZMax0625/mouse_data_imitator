package io_driver.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author zzmax.
 * @date 2023/2/3 14:51
 */
public class MyPanel extends JPanel implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("键被按下"+e.getKeyChar());
//        if(e.getKeyCode()==KeyEvent.VK_DOWN){//点击向下的键
//
//        }else if(e.getKeyCode()==KeyEvent.VK_UP){//点击向上的键
//
//        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){//点击向左的键
//
//        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){//点击向右的键
//
//        }
        System.out.println("keyPressed");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("keyReleased");
    }
}

