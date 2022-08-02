
package Controlador;

import Modelo.Conexión;
import Modelo.DatosModeloDB;
import Modelo.DatosSucursalPuestoTrabajo;
import Modelo.Sucursal;
import Vistas.AddUserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControllerSucursalPuestoTrabajo implements ActionListener{
    private final AddUserForm view;
    Conexión conexion = new Conexión();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<DatosSucursalPuestoTrabajo> list;
    DatosModeloDB model = new DatosModeloDB();

    public ControllerSucursalPuestoTrabajo(AddUserForm view) {
        this.view = view;
        this.getListaSucursales();
        Sucursal sucursal = (Sucursal) view.cbSucursal.getSelectedItem();
        getListaPuestosTrabajo(sucursal.getIdSucursal());
        events();
    }
    
    public final void events(){
        view.cbSucursal.addActionListener(this);
    }
    
    public final void getListaSucursales(){
        list = model.getSucursales();
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                int idSucursal  = list.get(i).getIdSucursal();
                String nombreSucursal = list.get(i).getNombreSucursal();
                System.out.println(idSucursal + " - " + nombreSucursal);
                view.cbSucursal.addItem(new Sucursal(idSucursal, nombreSucursal));
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen sucursales aún.", "Sucursales", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    public final void getListaPuestosTrabajo(int idSucursal){
        list = model.getPuestosTrabajo(idSucursal);
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                DefaultComboBoxModel model = (DefaultComboBoxModel)view.cbOcupacion.getModel();
                Object [] puestoTrabajo = new Object[2];
                puestoTrabajo[0]  = list.get(i).getIdPuestoTrabajo();
                puestoTrabajo[1] = list.get(i).getNombrePuestoTrabajo();
                model.addElement(puestoTrabajo[1]);
                view.cbOcupacion.setModel(model);
                System.out.println(model);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen puestos de trabajo aún.", "Sucursales", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object evento = ae.getSource();
        if(evento.equals(view.cbSucursal)){
            view.cbOcupacion.removeAllItems();
            Sucursal sucursal = (Sucursal)view.cbSucursal.getSelectedItem();
            getListaPuestosTrabajo(sucursal.getIdSucursal());
        }
    }
}
    

