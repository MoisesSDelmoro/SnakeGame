package game_snake;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame {
    
    //Caregamos a imagem em um objeto da ImagemIcon
    ImageIcon imagem = new ImageIcon(getClass().getResource("game_over.png"));
    //Criamos uma label, que será usada como uma tela de exibição
    JLabel label = new JLabel(imagem);
    
    public GameOver(){
        
        //Adicionamos essa imagem sobre a label
        add(label);
        
        //Definimos o tamanho e a região de exibição dessa label
        setSize(400,430);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}