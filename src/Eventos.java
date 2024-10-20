import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Eventos extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
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
    private JCheckBox jcbRelleno;
    private JLabel jlbGrados;
    private JSpinner jspGrados;
    private JButton jbnSesgado;
    private JLabel jlbSesgadoX;
    private JLabel jlbSesgadoY;
    private JSpinner jspSesgadoX;
    private JSpinner jspSesgadoY;
    private JButton jbnTranslacion;
    private JLabel jlbSTranslacionX;
    private JLabel jlbSTranslacionY;
    private JSpinner jspTranslacionX;
    private JSpinner jspTranslacionY;
    private JLabel jlbEscalado;
    private JSpinner jspEscalado;
    private Image buffer;
    private Image temporal;

    private ImageIcon iconoNegro = new ImageIcon(getClass().getResource("/imagenes/black.png"));
    private ImageIcon iconoRojo = new ImageIcon(getClass().getResource("/imagenes/red.png"));
    private ImageIcon iconoAzul = new ImageIcon(getClass().getResource("/imagenes/blue.png"));
    private ImageIcon iconoBorrador = new ImageIcon(getClass().getResource("/imagenes/borrador.png"));
    private ImageIcon iconoEstiloSolido = new ImageIcon(getClass().getResource("/imagenes/solido.png"));
    private ImageIcon iconoEstiloPunteado = new ImageIcon(getClass().getResource("/imagenes/punteado.png"));
    private ImageIcon iconoEstiloSaltado = new ImageIcon(getClass().getResource("/imagenes/saltado.png"));

    private List<Figura> figuras = new ArrayList<>();
    private int figura = 0;
    private int x, y;
    private Color colorSeleccionado = Color.BLACK;
    private int estiloLinea = 0;
    boolean sesgado = false;
    boolean traslacion = false;
    private double sesgadoX = 0.0;
    private double sesgadoY = 0.0;
    private double escalado = 1.0;
    private double traslacionX = 0.0;
    private double traslacionY = 0.0;

    public Eventos() {
        setTitle("Eventos del mouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 800, 600);
        setLayout(null);

        // Ajustar los componentes directamente en el JFrame
        jlbTitulo = new JLabel("Elige una figura:");
        jlbTitulo.setBounds(10, 0, 100, 20);
        add(jlbTitulo);

        jbnLinea = new JButton("Línea");
        jbnLinea.setBounds(10, 20, 100, 30);
        jbnLinea.addActionListener(e -> {
            figura = 1;
            repaint();
        });
        add(jbnLinea);

        jbnRectanguloCuadrado = new JButton("Rectángulo/Cuadrado");
        jbnRectanguloCuadrado.setBounds(120, 20, 170, 30);
        jbnRectanguloCuadrado.addActionListener(e -> {
            figura = 2;
            repaint();
        });
        add(jbnRectanguloCuadrado);

        jbnCirculoOvalo = new JButton("Círculo/Ovalo");
        jbnCirculoOvalo.setBounds(300, 20, 150, 30);
        jbnCirculoOvalo.addActionListener(e -> {
            figura = 4;
            repaint();
        });
        add(jbnCirculoOvalo);

        jlbTitulo2 = new JLabel("Elige un color:");
        jlbTitulo2.setBounds(460, 0, 100, 20);
        add(jlbTitulo2);

        jbnColorNegro = new JButton();
        jbnColorNegro.setIcon(iconoNegro);
        jbnColorNegro.setBounds(460, 20, 60, 30);
        jbnColorNegro.addActionListener(e -> colorSeleccionado = Color.BLACK);
        add(jbnColorNegro);

        jbnColorRojo = new JButton();
        jbnColorRojo.setIcon(iconoRojo);
        jbnColorRojo.setBounds(530, 20, 60, 30);
        jbnColorRojo.addActionListener(e -> colorSeleccionado = Color.RED);
        add(jbnColorRojo);

        jbnColorAzul = new JButton();
        jbnColorAzul.setIcon(iconoAzul);
        jbnColorAzul.setBounds(600, 20, 60, 30);
        jbnColorAzul.addActionListener(e -> colorSeleccionado = Color.BLUE);
        add(jbnColorAzul);

        jbnBorrador = new JButton();
        jbnBorrador.setIcon(iconoBorrador);
        jbnBorrador.setBounds(670, 20, 60, 30);
        jbnBorrador.addActionListener(this);
        add(jbnBorrador);

        jbnEstiloSolido = new JButton();
        jbnEstiloSolido.setIcon(iconoEstiloSolido);
        jbnEstiloSolido.setBounds(10, 60, 60, 30);
        jbnEstiloSolido.addActionListener(e -> {
            estiloLinea = 0;
            repaint();
        });
        add(jbnEstiloSolido);

        jbnEstiloPunteado = new JButton();
        jbnEstiloPunteado.setIcon(iconoEstiloPunteado);
        jbnEstiloPunteado.setBounds(80, 60, 60, 30);
        jbnEstiloPunteado.addActionListener(e -> {
            estiloLinea = 1;
            repaint();
        });
        add(jbnEstiloPunteado);

        jbnEstiloSaltado = new JButton();
        jbnEstiloSaltado.setIcon(iconoEstiloSaltado);
        jbnEstiloSaltado.setBounds(150, 60, 60, 30);
        jbnEstiloSaltado.addActionListener(e -> {
            estiloLinea = 2;
            repaint();
        });
        add(jbnEstiloSaltado);

        jcbRelleno = new JCheckBox("Relleno");
        jcbRelleno.setBounds(220, 60, 70, 30);
        jcbRelleno.addActionListener(e -> {
        });
        add(jcbRelleno);

        jlbGrados = new JLabel("Grados:");
        jlbGrados.setBounds(300, 60, 70, 30);
        add(jlbGrados);

        jspGrados = new JSpinner();
        jspGrados.setBounds(350, 60, 60, 30);
        jspGrados.setModel(new SpinnerNumberModel(0, 0, 360, 1));
        add(jspGrados);

        jbnSesgado = new JButton("Sesgado");
        jbnSesgado.setBounds(420, 60, 90, 30);
        jbnSesgado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sesgado) {
                    jlbSesgadoX.setVisible(true);
                    jlbSesgadoY.setVisible(true);
                    jspSesgadoX.setVisible(true);
                    jspSesgadoY.setVisible(true);
                    sesgado = true;
                } else {
                    jlbSesgadoX.setVisible(false);
                    jlbSesgadoY.setVisible(false);
                    jspSesgadoX.setVisible(false);
                    jspSesgadoY.setVisible(false);
                    sesgado = false;
                }
            }
        });
        add(jbnSesgado);

        jlbSesgadoX = new JLabel("X:");
        jlbSesgadoX.setBounds(520, 60, 50, 30);
        jlbSesgadoX.setVisible(false);
        add(jlbSesgadoX);

        jspSesgadoX = new JSpinner();
        jspSesgadoX.setBounds(540, 60, 60, 30);
        jspSesgadoX.setVisible(false);
        jspSesgadoX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) jspSesgadoX.getValue();
                sesgadoX = (double) valor / 100;
                repaint();
            }
        });
        add(jspSesgadoX);

        jlbSesgadoY = new JLabel("Y:");
        jlbSesgadoY.setBounds(610, 60, 50, 30);
        jlbSesgadoY.setVisible(false);
        add(jlbSesgadoY);

        jspSesgadoY = new JSpinner();
        jspSesgadoY.setBounds(630, 60, 60, 30);
        jspSesgadoY.setVisible(false);
        jspSesgadoY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) jspSesgadoY.getValue();
                sesgadoY = (double) valor / 100;
                repaint();
            }
        });
        add(jspSesgadoY);

        jbnTranslacion = new JButton("Traslación");
        jbnTranslacion.setBounds(710, 60, 100, 30);
        jbnTranslacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!traslacion) {
                    jlbSTranslacionX.setVisible(true);
                    jspTranslacionX.setVisible(true);
                    jlbSTranslacionY.setVisible(true);
                    jspTranslacionY.setVisible(true);
                    traslacion = true;
                } else {
                    jlbSTranslacionX.setVisible(false);
                    jspTranslacionX.setVisible(false);
                    jlbSTranslacionY.setVisible(false);
                    jspTranslacionY.setVisible(false);
                    traslacion = false;
                }
            }
        });
        add(jbnTranslacion);

        jlbSTranslacionX = new JLabel(":X");
        jlbSTranslacionX.setBounds(600, 60, 50, 30);
        jlbSTranslacionX.setVisible(false);
        add(jlbSTranslacionX);

        jspTranslacionX = new JSpinner();
        jspTranslacionX.setBounds(530, 60, 60, 30);
        jspTranslacionX.setVisible(false);
        jspTranslacionX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                traslacionX = (int) jspTranslacionX.getValue();
                repaint();
            }
        });
        add(jspTranslacionX);

        jlbSTranslacionY = new JLabel(":Y");
        jlbSTranslacionY.setBounds(690, 60, 50, 30);
        jlbSTranslacionY.setVisible(false);
        add(jlbSTranslacionY);

        jspTranslacionY = new JSpinner();
        jspTranslacionY.setBounds(620, 60, 60, 30);
        jspTranslacionY.setVisible(false);
        jspTranslacionY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                traslacionY = (int) jspTranslacionY.getValue();
                repaint();
            }
        });
        add(jspTranslacionY);

        jlbEscalado = new JLabel("Escalado:");
        jlbEscalado.setBounds(740, 0, 80, 20);
        add(jlbEscalado);

        jspEscalado = new JSpinner(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
        jspEscalado.setBounds(740, 20, 60, 30);
        jspEscalado.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                escalado = (double) jspEscalado.getValue();
                repaint();
            }
        });
        add(jspEscalado);

        jlbCoord = new JLabel("Coordenadas: x,y");
        jlbCoord.setBounds(610, 535, 200, 30);
        add(jlbCoord);

        // Panel para dibujar
        panelPpal = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2dBuffer = (Graphics2D) buffer.getGraphics();
                g2dBuffer.clearRect(0, 0, panelPpal.getWidth(), panelPpal.getHeight());
                g2dBuffer.scale(escalado, escalado);
                g2dBuffer.translate(traslacionX, traslacionY);

                for (Figura figura : figuras) {
                    figura.aplicarSesgado(g2dBuffer, sesgadoX, sesgadoY);
                }

                g.drawImage(buffer, 0, 0, this);
            }
        };
        panelPpal.setBounds(0, 90, 900, 500);
        panelPpal.addMouseListener(this);
        panelPpal.addMouseMotionListener(this);
        add(panelPpal);

        setVisible(true);
        buffer = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics2D g = (Graphics2D) temporal.getGraphics();
        g.setColor(colorSeleccionado);
        g.drawImage(buffer, 0, 0, panelPpal);

        // Obtener el ángulo de rotación en radianes desde el spinner
        int grados = (int) jspGrados.getValue();
        double angulo = Math.toRadians(grados);

        // Guardar la transformación original
        AffineTransform oldTransform = g.getTransform();

        // Calcular el centro de la figura
        int centroX = (x + e.getX()) / 2;
        int centroY = (y + e.getY()) / 2;

        // Aplicar la rotación alrededor del centro de la figura
        g.rotate(angulo, centroX, centroY);

        // Dibujar la figura con rotación
        if (figura == 1) {  // Dibujar línea
            dibujarLinea(g, x, y, e.getX(), e.getY());
        } else if (figura == 2) {  // Dibujar rectángulo/cuadrado
            dibujarRectanguloCuadrado(g, x, y, e.getX(), e.getY());
        } else if (figura == 4) {  // Dibujar círculo/óvalo
            dibujarCirculoOvalo(g, x, y, e.getX(), e.getY());
        }

        // Restaurar la transformación original para futuras operaciones gráficas
        g.setTransform(oldTransform);

        // Dibujar la imagen temporal en el panel principal
        panelPpal.getGraphics().drawImage(temporal, 0, 0, this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.setColor(colorSeleccionado);

        int grados = (int) jspGrados.getValue();
        double angulo = Math.toRadians(grados);

        // Crear y almacenar la figura
        Figura figuraNueva = new Figura(figura, x, y, e.getX(), e.getY(),
                colorSeleccionado, jcbRelleno.isSelected(), angulo,
                estiloLinea, escalado, traslacionX, traslacionY);
        figuras.add(figuraNueva);

        // Dibujar la nueva figura
        figuraNueva.dibujar(g);

        repaint();
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
        if (jcbRelleno.isSelected()) {
            g.fillRect(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
        } else {
            g.drawRect(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
        }
    }

    private void dibujarCirculoOvalo(Graphics2D g, int x1, int y1, int x2, int y2) {
        setEstiloLinea(g);
        int ancho = Math.abs(x2 - x1);
        int alto = Math.abs(y2 - y1);
        if (jcbRelleno.isSelected()) {
            g.fillOval(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
        } else {
            g.drawOval(Math.min(x1, x2), Math.min(y1, y2), ancho, alto);
        }
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
            jspGrados.setValue(0);
            jspSesgadoX.setValue(0);
            jspSesgadoY.setValue(0);
            figuras.clear();

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