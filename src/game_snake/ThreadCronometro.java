package game_snake;

import javax.swing.JFrame;

public class ThreadCronometro extends Thread {

    public static int min = 0;
    public static int seg = 10;
    
    //Criação dos objetos
    Music m = new Music();
    JFrame frame = new JFrame();
    Screen screen = new Screen(frame, m);
    
    public ThreadCronometro() {
        
        //Passamos o objeto Screen para o frame
        //e delimitamos a região do Monitor onde a tela será exibida
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void run() {
        //Iniciamos a musica
        m.sound.loop();
        
        for (int i = 180; i > 0; i--) {
            screen.Time(min, seg);
            
            //Convertemos o tempo de segundos da Thread, para Minutos/Segundos
            if (seg == 0 && min > 0) {
                seg = 59;
                min--;
            } else if (min == 0 && seg == 0) {
                //Acabando o tempo, encerramos a música
                System.out.println("ACABOU");
                m.pararMusica();
            }
            //A cada passagem, subtraimos um segundo
            seg--;
            
            try {
                //Delay de um segundo para a Thread Cronometro
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.print("Erro!!!");
            }
        }
    }
}
