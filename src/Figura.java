import java.awt.*;
import java.awt.geom.AffineTransform;

public class Figura {
    int tipo;  // 1: Línea, 2: Rectángulo/Cuadrado, 4: Círculo/Ovalo
    int x1, y1, x2, y2;
    Color color;
    boolean relleno;
    double angulo;  // Ángulo de rotación en radianes
    int estiloLinea;  // 0: Sólido, 1: Punteado, 2: Saltado
    double escalado;
    double traslacionX;
    double traslacionY;

    public Figura(int tipo, int x1, int y1, int x2, int y2, Color color,
                  boolean relleno, double angulo, int estiloLinea,
                  double escalado, double traslacionX, double traslacionY) {
        this.tipo = tipo;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.relleno = relleno;
        this.angulo = angulo;
        this.estiloLinea = estiloLinea;
        this.escalado = escalado;
        this.traslacionX = traslacionX;
        this.traslacionY = traslacionY;
    }

    public void dibujar(Graphics2D g) {
        // Guardar la transformación original
        AffineTransform oldTransform = g.getTransform();

        // Aplicar la traslación
        g.translate(traslacionX, traslacionY);

        // Calcular el centro de la figura
        int centroX = (x1 + x2) / 2;
        int centroY = (y1 + y2) / 2;

        // Aplicar la rotación alrededor del centro de la figura
        g.rotate(angulo, centroX, centroY);
        g.scale(escalado, escalado);

        // Aplicar el color
        g.setColor(color);

        // Aplicar el estilo de línea
        setEstiloLinea(g);

        // Dibujar la figura
        switch (tipo) {
            case 1: // Línea
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2: // Rectángulo/Cuadrado
                int width = Math.abs(x2 - x1);
                int height = Math.abs(y2 - y1);
                if (relleno) {
                    g.fillRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
                } else {
                    g.drawRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
                }
                break;
            case 4: // Círculo/Ovalo
                int widthOval = Math.abs(x2 - x1);
                int heightOval = Math.abs(y2 - y1);
                if (relleno) {
                    g.fillOval(Math.min(x1, x2), Math.min(y1, y2), widthOval, heightOval);
                } else {
                    g.drawOval(Math.min(x1, x2), Math.min(y1, y2), widthOval, heightOval);
                }
                break;
        }

        // Restaurar la transformación original
        g.setTransform(oldTransform);
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

    public void aplicarSesgado(Graphics2D g, double sesgadoX, double sesgadoY) {
        // Guardar la transformación original
        AffineTransform oldTransform = g.getTransform();

        // Aplicar la traslación
        g.translate(traslacionX, traslacionY);

        // Calcular el centro de la figura
        int centroX = (x1 + x2) / 2;
        int centroY = (y1 + y2) / 2;

        // Aplicar la rotación alrededor del centro de la figura
        g.rotate(angulo, centroX, centroY);

        // Aplicar el sesgado
        g.shear(sesgadoX, sesgadoY);
        g.scale(escalado, escalado);

        // Aplicar el color
        g.setColor(color);

        // Aplicar el estilo de línea
        setEstiloLinea(g);

        // Dibujar la figura
        switch (tipo) {
            case 1: // Línea
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2: // Rectángulo/Cuadrado
                int width = Math.abs(x2 - x1);
                int height = Math.abs(y2 - y1);
                if (relleno) {
                    g.fillRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
                } else {
                    g.drawRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
                }
                break;
            case 4: // Círculo/Ovalo
                int widthOval = Math.abs(x2 - x1);
                int heightOval = Math.abs(y2 - y1);
                if (relleno) {
                    g.fillOval(Math.min(x1, x2), Math.min(y1, y2), widthOval, heightOval);
                } else {
                    g.drawOval(Math.min(x1, x2), Math.min(y1, y2), widthOval, heightOval);
                }
                break;
        }

        // Restaurar la transformación original
        g.setTransform(oldTransform);
    }
}