
package Principal;
import Controlador.ListarModeloDB;
import Modelo.Conexión;
import Modelo.DatosModeloDB;
import Vistas.*;

public class Main {

    public static void main(String[] args) {
        //1. Creamos la instancia de la clase conexion
        Conexión conexion = new Conexión();
        conexion.getConnection();
        ListarModeloDB listas = new ListarModeloDB();
        listas.getListaGerentes();
        listas.getListaOperarios();
        listas.getListaDomiciliarios();
        //2. Creamos una instancia de la vista Login
        Login login = new Login();
        //3. Mediante la instancia de la vista llamamos el método que la hace visible
        login.setVisible(true);
    }
}
