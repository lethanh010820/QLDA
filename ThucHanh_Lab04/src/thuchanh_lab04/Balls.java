/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuchanh_lab04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Balls extends Thread{
 private JPanel box;
    private static final int XSIZE=10;
    private static final int YSIZE=10;
    private int x=0;
    private int y=0;
    private int dx=2;
    private int dy=2;
    public Balls (JPanel p) {
        box=p;
    }
    
    public void draw() {
        //cho phep phat trien cac phan tu duoc ve
        Graphics g=box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);// dien
        g.dispose(); // dong
    }

    public void move() {
        //xóa hình cũ bằng cách vẽ đè lên
        Graphics g=box.getGraphics();
        g.setXORMode(Color.GREEN);// mau sac cua bong
        g.fillOval(x, y, XSIZE, YSIZE);
        x+=dx;
        y+=dy;
        Dimension d=box.getSize();// tinh toan kich co
        //kiểm tra có đúng các cạnh
        if (x<0) {// den diem cuoi ben phai thi quay lai
            x=0;
            dx=-dx;
        }
        if (x+XSIZE >= d.getWidth()) {// den diem cuoi ben phai thi quay lai
            x=d.width-XSIZE;
            dx=-dx;
        }
        if (y<0) {// den diem cuoi ben tren thi quay lai
            y=0;
            dy=-dy;
        }
        
        if (y+YSIZE >= d.getHeight()) {// den diem cuoi ben duoi thi quay lai
            y=d.height-YSIZE;
            dy=-dy;
        }
        g.fillOval(x, y, dx, XSIZE);// dien
        g.dispose();//dong
    }
    
    public void run() {
        draw();
        for (int i=0; i<5000; i++) {
            move();
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
