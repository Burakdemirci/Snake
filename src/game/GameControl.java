package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import source.Anaconda;
import source.Animal;
import source.ForeignLanguages;
import source.Internships;
import source.Python;
import source.TrainingCertificates;




/**
 *
 * @author Burak DEMİRCİ
 *  141044091
 *  burakdemirci@gtu.edu.tr
 */
public class GameControl extends javax.swing.JPanel implements KeyListener, ActionListener{
    
    private final int borderX1=10,en=500,boy=450,borderY1=75;
    private boolean isLive=true;
    private Timer timer;
    private int delay=100;    
    private boolean up,down,left,right,enter,start,select,game;
    private int oldKey=0,setOld;
    private Asset head;
    private double score;
    private ArrayList<Asset> snake;
    private Asset job , food;
    private int selected =1,selectedItem=1;
    private int Size =5, jobSize=5, energy;
    private int dx,dy;
    private boolean flagDelay;
    private long startGameTime,elapsed,gameTime,oldGameTime;
    private long startFoodTime,foodTime,oldFoodTime;
    private  Animal realSnake;//Desingn pattern thing
    
    public GameControl() {        
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        start=true;
        timer = new Timer(delay,this);
        timer.start();
        startGameTime = System.nanoTime();
    }
    
    private void createLevel(){
        snake = new ArrayList<Asset>();
        head = new Asset(Size);
        head.setPosition(en/2+20,boy/2+20);
        snake.add(head);
        for(int i=0; i<10; i++){
        
            Asset a = new Asset(Size);
            a.setPosition(head.getX()+ (i*Size),head.getY());
            snake.add(a);
        }
        
        job = new Asset(jobSize);
        food = new Asset(jobSize);
        
        setFood();
        setJob();
        score =0;
        
    }
    
    private void setJob(){
        
        int x = (int)(Math.random()*(en-borderX1)+15);
        int y = (int)(Math.random()*(boy-borderY1)+78);
        x = x -(x%jobSize);
        y = y -(y%jobSize);
        
        job.setPosition(x, y);
    }
    private void setFood(){
        
        startFoodTime = System.nanoTime();
        int x = (int)(Math.random()*(en-borderX1)+15);
        int y = (int)(Math.random()*(boy-borderY1)+78);
        x = x -(x%jobSize);
        y = y -(y%jobSize);
        
        food.setPosition(x, y);
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
     
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP :    up=true; break;
            case KeyEvent.VK_DOWN :  down=true; break;
            case KeyEvent.VK_RIGHT : right=true; break;
            case KeyEvent.VK_LEFT :  left=true; break;
            case KeyEvent.VK_ENTER:  enter=true; break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP :    up=false; oldKey=1;   break;
            case KeyEvent.VK_DOWN :  down=false; oldKey=1; break;
            case KeyEvent.VK_RIGHT : right=false; oldKey=2;break;
            case KeyEvent.VK_LEFT :  left=false; oldKey=2; break;
            case KeyEvent.VK_ENTER:  enter=false; break;
        }
    }
    

    private void init() {
        isLive = true;
        flagDelay=true;
        Size *=selected;
        energy=101;
        if(selected==1)
            realSnake= new Python();
        else
            realSnake = new Anaconda();
        createLevel();
    }

    
   

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(start) 
            runStart();
        if(select)
            runSelect();
        if(game)
            runGame();
      
        repaint();
    }
    
    private void setLevel(){
       
        if(delay <=10)
           delay=10;
        else
           delay -= 10;
        timer.stop();
        
        timer.setDelay(delay);
        timer.start();
    }
    
    public void paint(Graphics grp){
        if(start) 
            startMenu(grp);
        if(select)
            selectItem(grp);
        if(game)
            drawGame(grp);
    }
    
    private void drawGame(Graphics grp){
        
        //Score area
        grp.setColor(Color.cyan);
        grp.fillRect(borderX1+1,5 ,en-1, 70);
        
        //Border 
        grp.setColor(Color.WHITE);
        grp.drawRect(borderX1, borderY1 ,en, boy);
        
        //Game Area
        grp.setColor(Color.black);
        grp.fillRect(borderX1+1,  borderY1+1,en-2, boy-2);
        
        //Snake
        if(selected==1)
            grp.setColor(Color.green);
        if(selected==2)
            grp.setColor(Color.cyan);
        for(Asset e: snake){
            e.paint(grp);
        }
        grp.setColor(Color.WHITE);
        head.paint(grp);
        
        //Job
        grp.setColor(Color.red);
        job.paint(grp);
        
        //Food
        grp.setColor(Color.yellow);
        food.paint(grp);
        
        // Score
        grp.setColor(Color.black);
        grp.drawString("Score : "+score, 430, 30);
        grp.drawString("Energy :"+energy,430, 55);
        grp.drawString("Food Time :"+(10-foodTime),300,30);
        grp.drawString("Multiplier :"+(realSnake.multiplier()),300,55);
        
        if(!isLive){
            grp.setColor(Color.WHITE);
            grp.drawString(" GAME OVER ! ", 200,200);
            if(energy<=0)
                grp.drawString(" R.I.P !", 200,240);
        }
    }
    private void runGame(){
        
        if (oldKey == 2 || oldKey == 0) {
            if (up && dy == 0) {
                dy = -Size;
                dx = 0;
            }
            if (down && dy == 0) {
                dy = Size;
                dx = 0;
            }
        }
        if (oldKey == 1 || oldKey == 0) {

            if (right && dx == 0 && oldKey != 0) {
                dx = Size;
                dy = 0;
            }
            if (left && dx == 0) {
                dx = -Size;
                dy = 0;
            }
        }
        if (dx != 0 || dy != 0) {
            for (int i = snake.size() - 1; i > 0; i--) {
                snake.get(i).setPosition(snake.get(i - 1).getX(), snake.get(i - 1).getY());
            }
            head.move(dx, dy);
        }
        if (oldKey != 0) {
            //Check snake collision itsef
            for (Asset a : snake) {

                if (a.isCollision(head)) {
                    isLive = false;
                    timer.stop();
                    break;
                }
            }
            //Check border is collision
            if (head.getX() <= borderX1 || head.getX() > en
                    || head.getY() <= borderY1 || head.getY() >= boy + 70) {

                isLive = false;
                timer.stop();
            }
            //Game over 
            if(energy<=0){
                isLive = false;
                timer.stop();
            }
        }

        //Finding Job
        if (job.isCollision(head)) {
            score += realSnake.multiplier();
            setJob();

            Asset a = new Asset(Size);
            a.setPosition(-100, -100);
            snake.add(a);
        }
       
        //Eat Something
        if(food.isCollision(head)){
            if(energy>=90)
                energy=100;
            else
                energy +=10; 
            setFood();
        }

        //Enegy
        elapsed = System.nanoTime() - startGameTime;
        oldGameTime=gameTime;
        gameTime= TimeUnit.SECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
        
        if(gameTime!=oldGameTime)
            energy--;
        
        //Food Thing
        elapsed =System.nanoTime()-startFoodTime;
        oldFoodTime=foodTime;
        foodTime=TimeUnit.SECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
        if(foodTime!=0 && foodTime % 10 ==0 && foodTime!=oldFoodTime)
        {
            setFood();
        } 
        // Level up and multiplayer buying
        if (score % 5 == 0 && score != 0) {
            if (flagDelay) {
                setOld=oldKey;
                setLevel();
                flagDelay = false;
                select=true;
                game=false;
            }
        } else {
            flagDelay = true;
        }
    }
    
    private void startMenu(Graphics grp){
        
        grp.setColor(Color.black);
        grp.fillRect(borderX1+1,  borderY1+1,en-2, boy-2);
        //Score area
        grp.setColor(Color.cyan);
        grp.fillRect(borderX1+1,5 ,en-1, 70);
        
        //MENU
        grp.setColor(Color.WHITE);
        grp.drawString(" SELECT SNAKE", 200 ,100);
        //Phyton
        grp.setColor(Color.GREEN);
        grp.fillRect(180,200,100,5);
        grp.setColor(Color.WHITE);
        grp.fillRect(280,200,5,5);
        
        //Anaconda
        grp.setColor(Color.cyan);
        grp.fillRect(180,300,100,10);
        grp.setColor(Color.WHITE);
        grp.fillRect(280,300,10,10);
        
        
        if(selected==1){
            grp.setColor(Color.WHITE);
            grp.drawRect(170,180,150,50);
        }
        if(selected ==2){
            grp.setColor(Color.WHITE);
            grp.drawRect(170,280,150,50);
        }
    }
    
    private void selectItem(Graphics grp){
        grp.setColor(Color.black);
        grp.fillRect(borderX1+1,  borderY1+1,en-2, boy-2);
        //Score area
        grp.setColor(Color.cyan);
        grp.fillRect(borderX1+1,5 ,en-1, 70);
        
        //Select Settings
        grp.drawString(" FOREIGN LANGUAGE  x2", 150,200);
        grp.drawString(" TRAINING CERTIFICATE  x3", 150,230);
        grp.drawString(" INTERNSHIP  x1.5",150,260);
        grp.setColor(Color.WHITE);
        if(selectedItem==1)
            grp.drawRect(150,185,200,20);
        if(selectedItem==2)
            grp.drawRect(150,185+30,200,20);
        if(selectedItem==3)
            grp.drawRect(150,185+60,200,20);
    }
    
   

    private void runStart() {
        if(up){
            selected=1;
        }
        if(down){
            selected=2;
        } 
        if(enter){
            oldKey=0;
            start=false;
            init();
            game=true;
        }
    }

    private void runSelect(){
        if(up){
            if(selectedItem>1)
                selectedItem--;  
        }
        if(down){
            if(selectedItem<3)
               selectedItem++; 
        }
        if(enter)
        {
            select=false;
            game=true;
            // Select Ability
            if(selectedItem==1)
                realSnake = new ForeignLanguages(realSnake);
            if(selectedItem==2)
                realSnake = new TrainingCertificates(realSnake);
            if(selectedItem==3)
                realSnake = new Internships(realSnake);
            
            oldKey=setOld;
        }    
    }
    
   
}
