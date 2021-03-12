package game_snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music {
    
    //Passamos o nome da música para o objeto som
    public URL som = getClass().getResource("soundSnake.wav");
    //Passamos esse objeto som para um objeto do AudioClip, chamado sound
    public AudioClip sound = Applet.newAudioClip(som);

    //Função para parar a música
    public void pararMusica() {
        sound.stop();
    }
}
