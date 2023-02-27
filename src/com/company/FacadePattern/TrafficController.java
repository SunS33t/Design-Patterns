package com.company.FacadePattern;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

class Colors{
    public static final String ANSI_DEFAULT = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
}

class Icons{
    public static final String carIcon = "\uD83D\uDE97";
    public static final String trafficLightIcon = "\uD83D\uDED1";
    public static final String carImgPath = "src/com/company/Assets/Car_Icon.png";
}

class Car {
    private int position = 0;
    private int speed = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    public void stop() {
        this.speed = 0;
    }
    public void go() {
        this.speed = 1;
    }
}

class TrafficLight {
    private final String[] colors = {Colors.ANSI_GREEN, Colors.ANSI_YELLOW, Colors.ANSI_RED};
    private int currentColor = 2;

    public String getCurrentColor(){
        return colors[currentColor];
    }

    public void switchColor(){
        currentColor++;
        if(currentColor == colors.length){
            currentColor = 0;
        }
    }

    public int getNextDelay(){
        switch (currentColor){
            case 0: {
                return 3000;
            }
            case 1: {
                return 2000;
            }
            case 2: {
                return 6000;
            }
        }
        return 0;
    }
}

public class TrafficController{
    TrafficLight trafficLight;
    Car car;
    boolean stop = false;
    public TrafficController(){
        car = new Car();
        trafficLight = new TrafficLight();
    }

    public void start(){
        stop = false;
        startTrafficLight();
        car.go();
        ConsoleTrafficController ctc = new ConsoleTrafficController(car,trafficLight,30,14);
        startConsoleTrafficController(ctc);
        new SwingTrafficController(car,trafficLight);
    }

    public void stop(){
        car.stop();
        stop = true;
    }
    private void startTrafficLight(){
        setTimeout(()->{
            trafficLight.switchColor();
            if(!stop)
                startTrafficLight();
        },trafficLight.getNextDelay());
    }
    private void startConsoleTrafficController(ConsoleTrafficController ctc){
        setTimeout(()->{
            try {
                if(!stop)
                    ctc.drawScene();
                startConsoleTrafficController(ctc);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 500);
    }
    private static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}

class SwingTrafficController extends JFrame{

    class TrafficLightImagePanel extends JPanel implements ActionListener{
        private final int x;
        private final int y;
        private final TrafficLight trafficLight;
        private String curColor;
        public TrafficLightImagePanel(TrafficLight trafficLight, int coordinate){
            this.trafficLight = trafficLight;
            curColor = trafficLight.getCurrentColor();
            this.x = coordinate;
            this.y = 10;
            Timer t = new Timer(50,this);
            t.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;

            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g1.setColor(Color.BLACK);
            g1.setStroke(new BasicStroke(6));

            g1.drawOval(x,y,50,50);
            g1.drawOval(x,y + 60,50,50);
            g1.drawOval(x,y + 120,50,50);

            g1.drawRect(x-5,y - 5,60,180);
            g1.drawLine(x+25,y+180,x+25,y+350);

            int currentCoordinates = y;
            switch (trafficLight.getCurrentColor()){
                case Colors.ANSI_RED:{
                    g1.setColor(Color.RED);
                    currentCoordinates = y;
                    break;
                }
                case Colors.ANSI_YELLOW:{
                    g1.setColor(Color.YELLOW);
                    currentCoordinates = y + 60;
                    break;
                }
                case Colors.ANSI_GREEN:{
                    g1.setColor(Color.GREEN);
                    currentCoordinates = y + 120;
                    break;
                }
            }
            g1.fillOval(x,currentCoordinates,50,50);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!Objects.equals(trafficLight.getCurrentColor(), curColor)) {
                curColor = trafficLight.getCurrentColor();
                repaint();
            }
        }
    }
     class CarImagePanel extends JPanel implements ActionListener{
        private final Image image;
        private int x,y;
        private Timer timer;
        private final Car car;
        private double speedMultiplier = 5;
        public CarImagePanel(Image image,Car car){
            this.car = car;
            this.image = image;
            this.x = 0;
            this.y = 0;
            this.timer = new Timer(50, this); // 10ms interval
            this.timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(x + car.getSpeed() * speedMultiplier > 1250){
                x = 0;
            }
            else{
                x += car.getSpeed() * speedMultiplier;
            }
            repaint();
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, x, y, this);
        }
    }
    public SwingTrafficController( Car car, TrafficLight trafficLight){
        JFrame frame = new JFrame("Car and Traffic Light");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350,600);

        Image image = new ImageIcon(Icons.carImgPath).getImage();

        CarImagePanel carPanel = new CarImagePanel(image, car);
        TrafficLightImagePanel trafficLightPanel = new TrafficLightImagePanel(trafficLight,700);

        carPanel.setDoubleBuffered(true);
        trafficLightPanel.setDoubleBuffered(true);

        carPanel.setBounds(0, 400, 1350, 105);
        carPanel.setBorder(new LineBorder(Color.BLACK));

        frame.add(carPanel);
        frame.add(trafficLightPanel);
        frame.setVisible(true);
    }
}
class ConsoleTrafficController {
    private final Car car;
    private final TrafficLight trafficLight;
    private final int trackSize;
    private final int trafficLightPosition;

    public ConsoleTrafficController(Car car, TrafficLight trafficLight, int size, int trafficLightPosition) {
        this.trackSize = size;
        this.trafficLightPosition = trafficLightPosition;
        this.car = car;
        this.trafficLight = trafficLight;
    }

    public void drawScene() throws InterruptedException {
        car.setPosition(car.getPosition() + car.getSpeed() > trackSize ? 0 : car.getPosition() + car.getSpeed());
        if(car.getPosition() == trafficLightPosition - 1 && trafficLight.getCurrentColor() == Colors.ANSI_RED){
            car.stop();
        }
        else{
            car.go();
        }
        System.out.print("\r");
        drawTrack();
    }

    private void drawTrack(){
        for(int i = 0; i < trackSize; i++){
            if(i == car.getPosition()){
                System.out.print(Colors.ANSI_BLUE + Icons.carIcon + Colors.ANSI_DEFAULT);
            }
            else if(i == trafficLightPosition){
                System.out.print(trafficLight.getCurrentColor() + Icons.trafficLightIcon + Colors.ANSI_DEFAULT);
            }
            System.out.print("_");
        }
    }
}