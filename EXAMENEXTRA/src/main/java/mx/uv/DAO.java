package mx.uv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;
import java.sql.*;

// Data Access Object
public class DAO {
    // en el dao se establece la conexion a la BD
    private static Conexion c = new Conexion();

    // este metodo regresa un conjunto de usuarios que cumpla un criterio
    public static List<Estudiantes> dameEstudiantes() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Estudiantes> resultado = new ArrayList<>();

        conn = c.getConnection();

        try {
            String sql = "SELECT nombre from Estudiantes";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Estudiantes u = new Estudiantes(rs.getString("nombre"));
                resultado.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return resultado;
    }

    public static String agregarEstudiantes(Estudiantes u) {
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";

        conn = c.getConnection();
        try {
            String sql = "INSERT INTO Estudiantes (nombre) values (?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getNombre());
         
            if (stm.executeUpdate() > 0)
                msj = "Estudiante Agregado";
            else
                msj = "Estudiante No Agregado";

        } catch (Exception e) {
            System.out.println("Error en el DAO");
            System.out.println(e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    
}