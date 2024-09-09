import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Eventos extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    // Componentes
    private JPanel panelPpal;
    private JLabel jlbCoord;
    private JLabel jlbTitulo;
    private JLabel jlbTitulo2;
    private JButton jbnLinea;
    private JButton jbnRectanguloCuadrado;
    private JButton jbnCirculoOvalo;
    private JButton jbnColorNegro;
    private JButton jbnColorRojo;
    private JButton jbnColorAzul;
    private JButton jbnBorrador;
    private JButton jbnEstiloSolido;
    private JButton jbnEstiloPunteado;
    private JButton jbnEstiloSaltado;
    private Image buffer;
    private Image temporal;

    // Variables de iconos
    private ImageIcon iconoNegro = new ImageIcon(getClass().getResource("/imagenes/black.png"));
    private ImageIcon iconoRojo = new ImageIcon(getClass().getResource("/imagenes/red.png"));
    private ImageIcon iconoAzul = new ImageIcon(getClass().getResource("/imagenes/blue.png"));
    private ImageIcon iconoBorrador = new ImageIcon(getClass().getResource("/imagenes/borrador.png"));
    private ImageIcon iconoEstiloSolido = new ImageIcon(getClass().getResource("/imagenes/solido.png"));
    private ImageIcon iconoEstiloPunteado = new ImageIcon(getClass().getResource("/imagenes/punteado.png"));
    private ImageIcon iconoEstiloSaltado = new ImageIcon(getClass().getResource("/imagenes/saltado.png"));

    // Variables globales
    private int figura = 0;
    private int posX = 50;
    private int x, y;
    private Color colorSeleccionado = Color.BLACK;
    private int estiloLinea = 0;  // 0 = sólido, 1 = punteado, 2 = saltado

    public Eventos() {
        // Características de la ventana
        setTitle("Eventos del mouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 800, 600);

        // Panel
        panelPpal = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPpal.setLayout(null);

        // Declaración de componentes
        jlbTitulo = new JLabel("Elige una figura:");
        jlbTitulo.setBounds(10, 0, 100, 20);
        jlbCoord = new JLabel("Coordenadas: x,y");
        jlbCoord.setBounds(625, 535, 200, 20);
        jbnLinea = new JButton("Línea");
        jbnRectanguloCuadrado = new JButton("Rectángulo/Cuadrado");
        jbnCirculoOvalo = new JButton("Círculo/Ovalo");
        jlbTitulo2 = new JLabel("Elige un color:");
        jlbTitulo2.setBounds(460, 0, 100, 20);
        jbnColorNegro = new JButton();
        jbnColorRojo = new JButton();
        jbnColorAzul = new JButton();
        jbnBorrador = new JButton();
        jbnEstiloSolido = new JButton();
        jbnEstiloPunteado = new JButton();
        jbnEstiloSaltado = new JButton();

        // Eventos de botones
        jbnLinea.addActionListener(e -> {
            figura = 1;
            repaint();
        });
        jbnLinea.setBounds(10, 20, 100, 30);

        jbnRectanguloCuadrado.addActionListener(e -> {
            figura = 2;
            repaint();
        });
        jbnRectanguloCuadrado.setBounds(120, 20, 170, 30);

        jbnCirculoOvalo.addActionListener(e -> {
            figura = 4;
            repaint();
        });
        jbnCirculoOvalo.setBounds(300, 20, 150, 30);

        jbnColorNegro.setIcon(iconoNegro);
        jbnColorNegro.setBounds(460, 20, 60, 30);
        jbnColorNegro.addActionListener(e -> {
            colorSeleccionado = Color.BLACK;
        });

        jbnColorRojo.setIcon(iconoRojo);
        jbnColorRojo.setBounds(530, 20, 60, 30);
        jbnColorRojo.addActionListener(e -> {
            colorSeleccionado = Color.RED;
        });

        jbnColorAzul.setIcon(iconoAzul);
        jbnColorAzul.setBounds(600, 20, 60, 30);
        jbnColorAzul.addActionListener(e -> {
            colorSeleccionado = Color.BLUE;
        });

        jbnBorrador.setIcon(iconoBorrador);
        jbnBorrador.setBounds(670, 20, 60, 30);
        jbnBorrador.addActionListener(this);

        jbnEstiloSolido.setIcon(iconoEstiloSolido);
        jbnEstiloSolido.setBounds(10, 60, 60, 30);
        jbnEstiloSolido.addActionListener(e -> {
            estiloLinea = 0;  // Línea sólida
            repaint();
        });

        jbnEstiloPunteado.setIcon(iconoEstiloPunteado);
        jbnEstiloPunteado.setBounds(80, 60, 60, 30);
        jbnEstiloPunteado.addActionListener(e -> {
            estiloLinea = 1;  // Línea punteada
            repaint();
        });

        jbnEstiloSaltado.setIcon(iconoEstiloSaltado);
        jbnEstiloSaltado.setBounds(150, 60, 60, 30);
        jbnEstiloSaltado.addActionListener(e -> {
            estiloLinea = 2;  // Línea saltada
            repaint();
        });

        // Eventos del panel
        panelPpal.addMouseListener(this);
        panelPpal.addMouseMotionListener(this);

        // Agregar componentes al panel
        panelPpal.add(jlbTitulo);
        panelPpal.add(jbnLinea);
        panelPpal.add(jbnRectanguloCuadrado);
        panelPpal.add(jbnCirculoOvalo);
        panelPpal.add(jlbTitulo2);
        panelPpal.add(jbnColorNegro);
        panelPpal.add(jbnColorRojo);
        panelPpal.add(jbnColorAzul);
        panelPpal.add(jbnBorrador);
        panelPpal.add(jbnEstiloSolido);
        panelPpal.add(jbnEstiloPunteado);
        panelPpal.add(jbnEstiloSaltado);
        panelPpal.add(jlbCoord);
        setContentPane(panelPpal);

        // Posicionamiento de la ventana
        setLocationRelativeTo(null);
        setVisible(true);
        buffer = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics2D g = (Graphics2D) temporal.getGraphics();
        g.setColor(colorSeleccionado);
        g.drawImage(buffer, 0, 0, panelPpal);

        if (figura == 1) {  // Dibujar línea
            dibujarLinea(g, x, y, e.getX(), e.getY());
        } else if (figura == 2) {  // Dibujar rectángulo/cuadrado
            dibujarRectanguloCuadrado(g, x, y, e.getX(), e.getY());
        } else if (figura == 4) {  // Dibujar círculo/óvalo
            dibujarCirculoOvalo(g, x, y, e.getX(), e.getY());
        }

        panelPpal.getGraphics().drawImage(temporal, 0, 0, this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.setColor(colorSeleccionado);

        if (figura == 1) {
            dibujarLinea(g, x, y, e.getX(), e.getY());
        } else if (figura == 2) {
            dibujarRectanguloCuadrado(g, x, y, e.getX(), e.getY());
        } else if (figura == 4) {
            dibujarCirculoOvalo(g, x, y, e.getX(), e.getY());
        }

        panelPpal.getGraphics().drawImage(temporal, 0, 0, this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        temporal = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        jlbCoord.setText("Coordenadas: " + e.getX() + "," + e.getY());
    }

    // Métodos de dibujo
    private void dibujarLinea(Graphics2D g, int x1, int y1, int x2, int y2) {
        setEstiloLinea(g);
        g.drawLine(x1, y1, x2, y2);
    }

    private void dibujarRectanguloCuadrado(Graphics2D g, int x1, int y1, int x2, int y2) {
        setEstiloLinea(g);
        int ancho = Math.abs(x2 - x1);
        int alto = Math.abs(y2 - y1);
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
    }

    private void dibujarCirculoOvalo(Graphics2D g, int x1, int y1, int x2, int y2) {
        setEstiloLinea(g);
        int ancho = Math.abs(x2 - x1);
        int alto = Math.abs(y2 - y1);
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
    }

    private void setEstiloLinea(Graphics2D g) {
        if (estiloLinea == 1) {  // Línea punteada
            float[] dashPattern = {2, 10};
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dashPattern, 0));
        } else if (estiloLinea == 2) {  // Línea saltada
            float[] dashPattern = {10, 10};
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dashPattern, 0));
        } else {  // Línea sólida
            g.setStroke(new BasicStroke(1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbnBorrador) {
            Graphics g = buffer.getGraphics();
            g.clearRect(0, 0, panelPpal.getWidth(), panelPpal.getHeight());

            panelPpal.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}