package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VoyageurController implements Initializable {

    @FXML
    private TableColumn<Voyageur, String> prenomColumn;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TableView<Voyageur> voyageursTable;

    @FXML
    private TextField txtDest;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtAge;

    @FXML
    private Button btnEffacer;

    @FXML
    private TableColumn<Voyageur, Double> numvolColumn;

    @FXML
    private TableColumn<Voyageur, Double> ageColumn;

    @FXML
    private TableColumn <Voyageur, String> destColumn;

    @FXML
    private TableColumn<Voyageur, String> nomColumn;

    @FXML
    private TableColumn<Voyageur, String> raisonColumn;

    @FXML
    private ComboBox<String> cboRaison;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNumvol;

    @FXML
    private Button btn;

    @FXML
    private TextField txtNom;
    
 // liste pour les raisons - cette liste permettra de donner les valeurs au comboBox

 	private ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("Tourisme",
 			"Affaires", "Familiales");
 
 	// Placer les voyageurs dans une observable list
 	public ObservableList<Voyageur> VoyageurData = FXCollections.observableArrayList();

 	// Creer une méthode pour accéder à la liste des étudiants
 	public ObservableList<Voyageur> getetudiantData() {
 		return VoyageurData;
 	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboRaison.setItems(list);
		
		// attribuer les valeurs aux colonnes du tableView
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		destColumn.setCellValueFactory(new PropertyValueFactory<>("Destination"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
		numvolColumn.setCellValueFactory(new PropertyValueFactory<>("Numvol"));
		raisonColumn.setCellValueFactory(new PropertyValueFactory<>("Raison"));
		
		voyageursTable.setItems(VoyageurData);
		
		showVoyageur(null);
	}
		
		// Ajouter un voyageur
		@FXML
		void ajouter() {
			Voyageur tmp = new Voyageur();
			tmp = new Voyageur();
			tmp.setNom(txtNom.getText());
			tmp.setPrenom(txtPrenom.getText());
			tmp.setDestination(txtDest.getText());
			tmp.setAge(Double.parseDouble(txtAge.getText()));
			tmp.setNumvol(Double.parseDouble(txtNumvol.getText()));
			tmp.setRaison(cboRaison.getValue());
			
			VoyageurData.add(tmp);
			
			clearFields();
		}
		
		// Effacer le contenu des champs
		@FXML
		void clearFields() {
			cboRaison.setValue(null);
			txtNom.setText("");
			txtPrenom.setText("");
			txtAge.setText("");
			txtDest.setText("");
			txtNumvol.setText("");
		}
		
		// Afficher les voyageurs

		public void showVoyageur(Voyageur voyageur) 
		{
			if (voyageur != null)
			{
				cboRaison.setValue(voyageur.getRaison());
				txtNom.setText(voyageur.getNom());
				txtPrenom.setText(voyageur.getPrenom());
				txtDest.setText(voyageur.getDestination());
				txtAge.setText(Double.toString(voyageur.getAge()));
				txtNumvol.setText(Double.toString(voyageur.getNumvol()));

			} 
			else
			{
				clearFields();
			}
		}
	}


