package mainprogram;

import geometry.base.Object3;


import geometry.shape.*;


import graphics.ColorARGB;


import graphics.Renderer;

import physics.PhysicalObject;
import world.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MainPanel extends JPanel {
    long usedBytes;

    int a=1000;
    private final static int WIDTH=1280;
    private final static float ASPECT=10.0f/16.0f;
    private final static int HEIGHT=(int)(WIDTH*ASPECT);




    Cube cube=new Cube(0, 0, a, a, ColorARGB.OCHRE);
    Cube cube1=new Cube(0, 0, -a, a, ColorARGB.VIOLET);
    Square square=new Square(0,300, 0, a, Plane.XZ, Rotation.CW, ColorARGB.BLACK);




    //MatrixF m=MatrixF.scalingMatrix(1.5f, 1, 1);
    TriangleSphere sphere =new TriangleSphere(0, a+100, 0, 250, 10, 10, ColorARGB.SKY_BLUE);
    Object3 object6=new Object3(sphere);

    Object3 object1=new Object3(cube);
    Object3 object2=new Object3(cube1);
    Object3 object3=new Object3(cube.moved(0,1000,0));
    Object3 object4=new Object3(cube1.moved(0,a,0));
    Object3 object5=new Object3(cube1.moved(a,0,0));

    Object3 object7=new Object3(square);


    PhysicalObject p1=new PhysicalObject(object1,10);
    PhysicalObject p2=new PhysicalObject(object2,10);
    PhysicalObject p3=new PhysicalObject(object3,10);
    PhysicalObject p4=new PhysicalObject(object4,10);
    PhysicalObject p5=new PhysicalObject(object5,10);
    PhysicalObject p6=new PhysicalObject(object6,10);
    PhysicalObject p7=new PhysicalObject(object7, 10);


    Space space=new Space(p6, p1);


    Renderer renderer =new Renderer(WIDTH,HEIGHT);
    Robot robot;


    float dx=0;
    float dy=0;
    float alpha=0;
    float beta=0;


    public MainPanel() {

        space.camera().setSpeed(10);
        //canvas.setBackground(ColorARGB.WHITE);

        //canvas.drawCircle(WIDTH/2,HEIGHT/2, 100, ColorARGB.BLACK);


        this.setupKeyBindings();


        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));


        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                if(!isEnter){
                    int centerX, centerY;
                    centerX= getLocationOnScreen().x+WIDTH/2;
                    centerY= getLocationOnScreen().y+HEIGHT/2;
                    dx= (e.getXOnScreen()-centerX);
                    dy=(e.getYOnScreen()-centerY);
                    alpha=dy/(2*HEIGHT);
                    beta=dx/(2*WIDTH);
                    space.camera().rotate(alpha,beta,0);
                    robot.mouseMove(centerX, centerY);
                }
            }
        });
        this.setFocusable(true);


        Timer timerPaint =new Timer(16, e ->{

            //usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            //System.out.println(usedBytes/1048576);
            repaint();

        });


        Timer timerLogic=new Timer(16, e ->{
            logic();
        });


        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        timerPaint.start();
        timerLogic.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //canvas.fillCircle(500,500, 100, ColorARGB.BEIGE);
        //g.drawImage(canvas.buffer().toImage(), 0,0, this);
        renderer.render(space);
        g.drawImage(renderer.buffer().toImage(), 0,0, this);
        renderer.clear();
    }
    private void logic() {
        //System.out.println(space.camera().center());
        if(isPlus){
            space.camera().addSpeed(1);
        }
        if(isMinus){
            space.camera().addSpeed(-1);
        }
        if(isRight){

            space.camera().moveRight();
        }
        if(isLeft){

            space.camera().moveLeft();
        }
        if(isUp){

            space.camera().moveUp();
        }
        if(isDown){

            space.camera().moveDown();
        }
        if(isForward){

            space.camera().moveForward();
        }
        if(isBack){

            space.camera().moveBack();
        }


    }
    private boolean isUp=false;
    private boolean isDown=false;
    private boolean isLeft=false;
    private boolean isRight=false;
    private boolean isForward=false;
    private boolean isBack=false;
    private boolean isPlus=false;
    private boolean isMinus=false;
    private boolean isEnter=false;
    private void setupKeyBindings() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(e -> {
            // Определяем, нажата клавиша (true) или отпущена (false)
            boolean state = (e.getID() == KeyEvent.KEY_PRESSED);
            boolean isTransition = (e.getID() == KeyEvent.KEY_PRESSED || e.getID() == KeyEvent.KEY_RELEASED);

            if (isTransition) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        isForward = state;
                        break;
                    case KeyEvent.VK_S:
                        isBack = state;
                        break;
                    case KeyEvent.VK_A:
                        isLeft = state;
                        break;
                    case KeyEvent.VK_D:
                        isRight = state;
                        break;
                    case KeyEvent.VK_CONTROL:
                        isDown = state;
                        break;
                    case KeyEvent.VK_SPACE:
                        isUp = state;
                        break;
                    case KeyEvent.VK_PAGE_UP:
                        isPlus =state;
                        break;
                    case KeyEvent.VK_PAGE_DOWN:
                        isMinus =state;
                        break;
                    case KeyEvent.VK_ENTER:
                        isEnter =state;
                        break;
                }
            }
            return false;
        });
    }
}
