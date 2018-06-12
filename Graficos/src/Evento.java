import javax.management.Notification;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

public class Evento {

    private Vector ListenerLuna = new Vector();

    public Evento(){

    }

    public void MediaLuna (String Nuevo){
        LunaEvent event=new LunaEvent( this, Nuevo);
        notificarCambio(event);
    }
//llamada del oyente listenerLuna
    public synchronized void addBLunaListener(LunaListener listener){
        ListenerLuna.addElement(listener);

    }

    private void notificarCambio(LunaEvent event){
        Vector lista;
        synchronized(this){
            lista=(Vector)ListenerLuna.clone();
        }
        for(int i=0; i<lista.size(); i++){
            LunaListener listener=(LunaListener) lista.elementAt(i);
            listener.notificarSesion(event);
        }
    }

}

class LunaEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param objet The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    protected String c ;
    public LunaEvent(Object objet, String cuen) {
        super(objet);

        this.c= cuen;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

}
interface LunaListener extends EventListener{
    public void notificarSesion(EventObject o);
}



