
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class Asset {
    private int x ,y,size;
    
    public Asset(int sizeSnake){
        size=sizeSnake;
    }
    public int getX(){
        return x;
    }
     public int getY(){
        return y;
    }
    public void setX(int newX){
        x=newX;
    }
    
    public void setY(int newY){
        y=newY;
    }
    
    public void setPosition(int nx , int ny){
        x=nx;
        y=ny;
    }
    
    public void move(int nx , int ny){
        x +=nx;
        y +=ny;
    }
    
    public Rectangle getBound(){
        return new Rectangle(x,y,size,size);
        
    }
    public boolean isCollision(Asset o){
        if(o == this )
            return false;
        return getBound().intersects(o.getBound());
    } 
    
    public void paint(Graphics grp){
        grp.fillRect(x, y, size, size);
    }
}
