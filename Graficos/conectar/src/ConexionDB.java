import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {
    Connection link;
    public String usuario="root";
    public String password="123";
    public String db="transportador";
    public String url ="jdbc:mysql://localhost/" + db
            + "?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";

    public Connection Conectar(){
        link = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            link=DriverManager.getConnection(this.url,this.usuario,this.password);

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return link;
    }

    public int insertarDB(double XOrigin, double YOrigin,double coordXFin,double coordYFin,double coordXFin2,double coordYFin2,double angGrad)
    {
        int numRegs=0;
        try{
            PreparedStatement stInsertar = link.prepareStatement( "INSERT INTO resultados (CentroX,CentroY,P_InicialX,P_InicialY,P_FinalX,P_FinalY,Angulo)"
                    +"VALUES (?,?,?,?,?,?,?)");
            stInsertar.setString(1, String.valueOf(XOrigin));
            stInsertar.setString(2, String.valueOf(YOrigin));
            stInsertar.setString(3, String.valueOf(coordXFin));
            stInsertar.setString(4, String.valueOf(coordYFin));
            stInsertar.setString(5, String.valueOf(coordXFin2));
            stInsertar.setString(6, String.valueOf(coordYFin2));
            stInsertar.setString(7, String.valueOf(angGrad));


            numRegs=stInsertar.executeUpdate();
        }
        catch (SQLException error){
            error.printStackTrace();
        }
        return numRegs;
    }

}

