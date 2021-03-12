package game_snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 400, HEIGHT = 400;

    private Thread threadScreen;
    private boolean running = false;

    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private Apple apple;
    private ArrayList<Apple> apples;

    private int score = 0;
    public int min = 0;
    public int seg = 0;
    private Random r;
    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    private boolean right = true, left = false, up = false, down = false;
    private int ticks = 0;

    public JFrame j1 = new JFrame();
    public Music m1 = new Music();
    
    //Criação do construtor da Tela(Screen)
    public Screen(JFrame frame, Music m) {
        
        //Guardando os objetos criados na ThreadCronometro, nos objetos 
        //criados aqui na classe Screen
        j1 = frame;
        m1 = m;
        setFocusable(true);

        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, 430));

        r = new Random();

        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        start();
    }
    
    //Criação da função que receberá o tempo da Thread Cronometro
    public void Time(int aux_min, int aux_seg) {
        min = aux_min;
        seg = aux_seg;
        
        if(min==0 && seg==0){
            win();
            System.out.println("HERE");
        }
    }
    
    //Criação do primeiro ponto da cobra, iniciando sempre em 10,10
    //Criacão do primeiro ponto da maça, sempre aleatório
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        if (apples.size() == 0) {
            int xCoor = r.nextInt(39);
            int yCoor = r.nextInt(39);

            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }
        
        //Após a coletagem da maça, removemos ela da tela, incrementamos em 1 o 
        //comprimentro da cobra e incremetamos o Score
        for (int i = 0; i < apples.size(); i++) {
            if (xCoor == apples.get(i).getxCoor()
                    && yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                score++;
                i++;
            }
        }
        
        //Encerramos o jogo caso a cobra encoste nela mesma
        for (int i = 0; i < snake.size(); i++) {
            if (xCoor == snake.get(i).getxCoor()
                    && yCoor == snake.get(i).getyCoor()) {
                if (i != snake.size() - 1) {
                    stop();
                }
            }
        }
        
        //Encerramos o jogo caso a cobra encoste na borda da tela
        if (xCoor < 0 || xCoor > 39 || yCoor < 0 || yCoor > 39) {
            stop();
        }

        ticks++;

        if (ticks > 250000) {
            if (right) xCoor++;
            if (left) xCoor--;
            if (up) yCoor--;
            if (down) yCoor++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);

            if (snake.size() > size) snake.remove(0);
        }
    }

    public void paint(Graphics g) {
        
        //Barra de dados exibidos ao final da tela do Game, com o tempo restante
        //e o Score
        g.setColor(Color.BLACK);
        g.fillRect(0, 400, WIDTH, 50);
        
        //Inserção dos dados na barra feita acima
        g.setColor(Color.green);
        g.setFont(new Font("arial", Font.PLAIN, 22));
        g.drawString("Score:" + score, 300, 420);
        g.drawString(min + ": " + seg, 20, 420);
        
        //Tela onde a cobra será guiada
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //Sobreposição de uma grade(Funcionando com uma matriz), para guiar os
        //jogadores, de modo a facilitar a caminho a ser percorrido pela cobra
        g.setColor(Color.BLACK);
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        
        
        //Inserindo a cobra na tela, e pegando informações para saber 
        //onde ela está
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        //Inserindo a maçã na tela, e pegando informações para saber 
        //onde ela está
        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }
        
    }
    
    //Iniciamos a ThreadScreen
    public void start() {
        running = true;
        threadScreen = new Thread(this);
        threadScreen.start();
    }
    
    //Paramos a ThreadScreen
    public void stop() {
        
        //Paramos sua execução, fechamos a janela, paramos a música e abrimos 
        //a tela de Game Over
        running = false;
        j1.setVisible(false);
        m1.pararMusica();
        GameOver go = new GameOver();
        go = new GameOver();

        try {
            threadScreen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
     public void win() {
        
        //Paramos sua execução, fechamos a janela, paramos a música e abrimos 
        //a tela de Win
        running = false;
        j1.setVisible(false);
        m1.pararMusica();
        WinGame win = new WinGame();
        win = new WinGame();

        try {
            threadScreen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //Executamos as funções tick e repaint sem delay, para não termos erro 
    //na leitura dos dados inseridos pela usuário
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }
    
    //Leitura para sabermos qual tecla foi pressionada, e quais ações devem
    //ser realizadas
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            right = true;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        if (key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke){}
    
    @Override
    public void keyReleased(KeyEvent ke){}
}
