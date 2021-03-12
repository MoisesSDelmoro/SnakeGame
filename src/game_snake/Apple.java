package game_snake;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {

    private int xCoor, yCoor, width, height;
    
    //Criação do ponto em que a maçã será exibida, sendo xCoor e yCoor valores
    //aleatórios e tileSize, limitando os valores máximos de X e Y
    public Apple(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    
    //Pinta um ponto criado acima
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }
    
    //Pega o valor de X criado na classe Screen
    public int getxCoor() {
        return xCoor;
    }
    
    //Seta esse valor, com o xCoor dessa classe
    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    //Pega o valor de Y criado na classe Screen
    public int getyCoor() {
        return yCoor;
    }
    
    //Seta esse valor, com o yCoor dessa classe
    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}
