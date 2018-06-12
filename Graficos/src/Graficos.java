import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Graficos extends JFrame{
    Trasportador Trasportador;
    Container container;
    public Graficos()
    {
        super("Transportador");
        container = getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.LINE_AXIS));



        Trasportador = new Trasportador();
        container.add(Trasportador);
       //  g.fillOval(210,90,60,50);//pintamos del color del ambiente sobre el circulo ya dibujado para que paresca 1/2 luna
    }
    public static void main(String [] args)
    {
        Graficos frame = new Graficos();
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        frame.setSize(new Dimension(800,600));
        frame.setVisible(true);
    }

}