package io_driver;

import io_driver.panel.MyPanel;

import javax.swing.*;

/**
 * @author zzmax.
 * @date 2023/2/3 14:31
 */
public class KeyboardCollector extends JFrame {

    public KeyboardCollector() {

        MyPanel mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);//注册监听

        this.setSize(300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
