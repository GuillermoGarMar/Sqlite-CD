/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exsql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.*;

/**
 *
 * @author ggarciamartinez
 */
public class InterfaceSQLite {

    private String url;
    private Connection conn = null;

    public InterfaceSQLite() {
    }

    public InterfaceSQLite(String url) {
        this.url = "jdbc:sqlite:" + url;
    }

    /**
     * Metodo para conectarse a la base de datos con la url dada en el
     * constructor
     */
    public Boolean connect() {
        try {
            conn = DriverManager.getConnection(url);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    /**
     * Crea la tabla en la base de datos
     * @throws SQLException 
     */
    public void crearTb() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE puntos (id String primary key, name String, score String);");
            System.out.println("Table created");
            //st.execute("DROP TABLE ScoreTB;");
            //System.out.println("Table deleted");
        } catch (SQLException ex) {
            System.err.println("Tabla ya creada");
        }
    }

    /**
     * Recibe los parametros que vamos a insertar en la base de datos, devuelve
     * el numero de insercciones correctas
     *
     * @param id
     * @param nombre
     * @param puntos
     * @return
     */
    public int insertar(String id, String nombre, int puntos) {
        int cont = 0;
        try {
            PreparedStatement st = conn.prepareStatement("insert into puntos (id,nombre, puntos) values (?,?,?)");
            st.setString(1, id);
            st.setString(2, nombre);
            st.setInt(3, puntos);
            st.execute();
            cont += 1;
            return cont;
        } catch (SQLException ex) {
            return cont;
        }
    }

    /**
     * Recibe un id del elemento que se va actualizar y el nuevo valor por cada
     * parametro en la base de datos, devuelve el numero de actualizaciones
     * correctas
     *
     * @param id
     * @param puntos
     * @param nombreNuevo
     * @return
     */
    public int update(String id, int puntos, String nombreNuevo) {
        int cont = 0;
        try {
            String sql = "UPDATE puntos SET nombre = ? , "
                    + "puntos = ? "
                    + "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreNuevo);
            pstmt.setInt(2, puntos);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
            cont += 1;
            return cont;
        } catch (SQLException ex) {
            return cont;
        }
    }

    /**
     * Recibe el id del elemento a borrar en la base de datos, devuelve el
     * numero de eliminaciones correctas
     *
     * @param id
     * @return
     */
    public int borrar(String id) {
        int cont = 0;
        try {
            String sql = "DELETE FROM puntos WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            cont += 1;
            return cont;
        } catch (SQLException ex) {
            return cont;
        }
    }

}
