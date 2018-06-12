import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.util.EventObject;
import java.util.jar.JarOutputStream;

public class Trasportador extends JPanel{


        boolean banderaMO = false;
        boolean banderaMOT= false;
        boolean banderaMOTR = false;
        double ang;
        double angGrad=0.0;
        private int contClick=0;
        private Color colorFondo;
        private int coordXOrig = 100, coordYOrig=100;
        private double XOrigin=-1, YOrigin=-1;
        private double coordXFin =0;
        private double coordYFin =0;
        private double coordXFin2=0;
        private double coordYFin2 =0;

        ConexionDB BaseD = new ConexionDB();
        Connection DBcon= null;

        Point p1,p2,p3;

    public Trasportador()
    {
        colorFondo= Color.WHITE;
        this.setPreferredSize(new Dimension(800,600));

        //Crear Objeto de la clase Control Mouse
        ControlMouse oyenteRaton = new ControlMouse();
        //Crear Oyentes
        this.addMouseListener(oyenteRaton);
        this.addMouseMotionListener(oyenteRaton);



        DBcon= BaseD.Conectar();
        if(DBcon !=null){

        }

    }
//componente
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(colorFondo);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);


        }


    public class ControlMouse extends MouseAdapter implements MouseMotionListener
    {
        @Override
        public void mouseClicked(MouseEvent e){
          //  el clic dispara el evento
            //evento creado
            Evento et = new Evento();
            //llamada del evento
            et.addBLunaListener(new LunaListener() {
                @Override
                //genera el evento
                public void notificarSesion(EventObject o) {
                    //llamada de hilo
                    hilo elHilo = new hilo();
                    elHilo.run();
                }
            });

            Graphics g= getGraphics();
            contClick+=1;
            if((e.getButton()==MouseEvent.BUTTON1 && contClick==1)){
                banderaMO=true;
                XOrigin=e.getX();
                YOrigin=e.getY();
                p1= new Point((int) XOrigin,(int) YOrigin);

                //JOptionPane.showMessageDialog(null,"coordenada1 x: "+XOrigin+" coordenada1 y: "+ YOrigin);

                JOptionPane.showMessageDialog(null,""+p1);

            }

            if((e.getButton()==MouseEvent.BUTTON1 && contClick==2))
            {
                banderaMOT=true;
                coordXFin=e.getX();
                coordYFin=e.getY();
                p2 = new Point((int)coordXFin,(int)coordYFin);

                //JOptionPane.showMessageDialog(null,"coordenada2 x: "+coordXFin+" coordenada2 y: "+ coordYFin);
                JOptionPane.showMessageDialog(null,""+p2);

            }

            //tercer punto (punto final)
            if((e.getButton()==MouseEvent.BUTTON1 && contClick==3))
            {
                banderaMOTR=true;
                coordXFin2=e.getX();
                coordYFin2=e.getY();
                p3= new Point((int)coordXFin2,(int)coordYFin2);
                //JOptionPane.showMessageDialog(null,"coordenada3 x: "+coordXFin2+" coordenada3 y: "+ coordYFin2);
                JOptionPane.showMessageDialog(null,""+p3);
            }

            //Validar si las 3 banderas(clicks) han sido correctas
            if(banderaMO==true && banderaMOT==true && banderaMOTR==true)
            {
                //ejecuto el evento creado
                et.MediaLuna("");
                //llamada de dibujado de las lineas 1 y 2(vectores )
                linea1(g,XOrigin,YOrigin,coordXFin,coordYFin);
                linea2(g,XOrigin,YOrigin,coordXFin2,coordYFin2);
                angulo(g,p1,p2,p3);

                JOptionPane.showMessageDialog(null,"Angulo Generado: "+angGrad);


                //datos que se envian a la base de datos
                int numRegs= BaseD.insertarDB( XOrigin,  YOrigin,coordXFin, coordYFin, coordXFin2, coordYFin2, angGrad);


            }

        }
    }

    public void linea1(Graphics g, double centroX, double centroY, double inicioX, double incicioY)
    {
        g.setColor(Color.BLACK);
        g.drawLine((int)XOrigin,(int)YOrigin,(int)coordXFin,(int)coordYFin);

    }
    public void linea2 (Graphics g, double centroX, double centroY, double FinX, double FinY)
    {

        g.setColor(Color.BLACK);
        g.drawLine((int)XOrigin,(int)YOrigin,(int)coordXFin2,(int)coordYFin2);

    }
    //creacion del hilo
    public class hilo extends Thread{
        public void run(){
            if(XOrigin == coordXFin && XOrigin == coordXFin2 && YOrigin == coordYFin && coordYFin2 == YOrigin){
                JOptionPane.showMessageDialog(null,"Se dibujará un puntito ");
            }
        }
    }

    public double angulo(Graphics g, Point a, Point b, Point c)
    {
        //primer vector
        Point pi= new Point(b.x-a.x,b.y-a.y);
        //segundo vector
        Point pj=new Point(c.x-a.x,c.y-a.y);

        //calcular angulo
        double ang_pi= Math.atan2((double)pi.x,(double)pi.y);
        double ang_pj=Math.atan2((double)pj.x,(double)pj.y);

        //hallar diferencia
        ang=ang_pj-ang_pi;
        angGrad=Math.toDegrees(ang);
        if(angGrad<0.0)
        {
            angGrad*=-1;
        }

        if (ang<0.0)
            return ang+(2.0*Math.PI);

        else

            return ang;

    }


 }