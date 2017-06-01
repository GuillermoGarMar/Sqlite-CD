/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exsql;

import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author ggarciamartinez
 */
public class ExSql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
 String ruta = JOptionPane.showInputDialog("Introduce la ruta de la base de datos");
        InterfaceSQLite bd = new InterfaceSQLite(ruta);
        Boolean seguir = true;

        if (bd.connect()) {
            System.out.println("Conectado");
            bd.crearTb();
            String option = JOptionPane.showInputDialog("Seleccione un opcion: "
                    + "\n a. Insertar"
                    + "\n b. Borrar"
                    + "\n c. Modificar"
                    + "\n d. Salir"
            );
            switch (option) {
                case "a":
                    System.out.println(bd.insertar("1a", "Guille", 1200) + " registros insertados correctamente");
                    break;
                case "b":
                    System.out.println(bd.borrar("1a") + " registros borrados correctamente");
                    break;
                case "c":
                    System.out.println(bd.update("1a", 2000, "Juan") + " registros borrados correctamente");
                    break;
                case "d":
                    seguir = false;
                    break;
            }
            while (seguir == true);
        } else {
            System.err.println("No se ha podido conectar a la base de datos");
        }
}
    
}
