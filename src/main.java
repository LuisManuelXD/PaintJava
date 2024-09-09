import javax.swing.*;

public class main {
    public static void main(String[] args) {
        Eventos eventos = new Eventos();
        eventos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eventos.setResizable(false);
        eventos.setBounds(0,0,800,600);
        eventos.setVisible(true);
    }
}
