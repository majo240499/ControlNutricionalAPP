/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import nutricionalappfx.dominio.PacienteDM;
import nutricionalappfx.dominio.pojo.Paciente;
import nutricionalappfx.modelo.dao.PacienteDAO;

/**
 * FXML Controller class
 *
 * @author Maria Jose
 */
public class FXMLAdminPacienteController implements Initializable {
    
    private int idNutriologo;

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnEdicion;
    @FXML
    private Button btnBaja;
    @FXML
    private TableView<Paciente> tvPacientes;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colFechaNacimiento;
    @FXML
    private TextField txBuscador;
    @FXML
    private TableColumn colPesoInicial;
    
    private ObservableList<Paciente> pacientes;
    private PacienteDM pacienteDM;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pacienteDM = new PacienteDM(new PacienteDAO());
        configurarColumnasTabla();
        cargarInformacionTabla();
    }
    
    private void configurarColumnasTabla() {
        colCodigo.setCellFactory(new PropertyValueFactory("codigo"));
        colNombre.setCellFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellFactory(new PropertyValueFactory("apellidoMaterno"));
        colFechaNacimiento.setCellFactory(new PropertyValueFactory("fechaNacimiento"));
        colPesoInicial.setCellFactory(new PropertyValueFactory("pesoInicial"));
        
    }
    
    private void cargarInformacionTabla() {
        pacientes = FXCollections.observableArrayList();
        List<Paciente> pacientesBD;
    }
    
    public void recibirInformacion(int idNutriologo) {
        this.idNutriologo = idNutriologo;
    }
    

    @FXML
    private void clicRegresar(ActionEvent event) {
    }

    @FXML
    private void handleBottonCerrarSesion(ActionEvent event) {
    }

    @FXML
    private void handleBottonRegistrar(ActionEvent event) {
    }

    @FXML
    private void handleBottonEditar(ActionEvent event) {
    }

    @FXML
    private void handleButtonEliminar(ActionEvent event) {
    }   
}
