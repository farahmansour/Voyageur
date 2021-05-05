package application;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private Button btnModifier;

    @FXML
    private TextField txtNom;
    
 // liste pour les raisons - cette liste permettra de donner les valeurs au comboBox

 	private ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("Tourisme",
 			"Affaires", "Familiales");
 
 	// Placer les voyageurs dans une observable list
 	public ObservableList<Voyageur> VoyageurData = FXCollections.observableArrayList();

 	// Creer une méthode pour accéder à la liste des étudiants
 	public ObservableList<Voyageur> getvoyageurData() {
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
		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnClear.setDisable(true);
		
		showVoyageur(null);
		// Mettre à jour l'affichage d'un étudiant séléctionné
				voyageursTable.getSelectionModel().selectedItemProperty()
						.addListener((observable, oldValue, newValue) -> showVoyageur(newValue));
	}
	
	@FXML
	public void verifNum() 
	// méthode pour trouver des inputs non numériques
	{
		txtAge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^[0-9](\\.[0-9]+)?$")) {
				txtAge.setText(newValue.replaceAll("[^\\d*\\.]", "")); 

	// si le input est non numérique, ca le remplace
			}
		});
		txtNumvol.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^[0-9](\\.[0-9]+)?$")) {
				txtNumvol.setText(newValue.replaceAll("[^\\d*\\.]", "")); 
				
			}
		});
	}

		// Ajouter un voyageur
		@FXML
		void ajouter() {
			
			//Verifier si un champ n'est pas vide
			if (noEmptyInput()) {
			Voyageur tmp = new Voyageur();
			tmp = new Voyageur();
			tmp.setNom(txtNom.getText());
			tmp.setPrenom(txtPrenom.getText());
			tmp.setDestination(txtDest.getText());
			tmp.setAge(Double.parseDouble(txtAge.getText()));
			tmp.setNumvol(Double.parseDouble(txtNumvol.getText()));
			tmp.setRaison(cboRaison.getValue());
			
			VoyageurData.add(tmp);
			btnModifier.setDisable(false);
			btnEffacer.setDisable(false);
			btnClear.setDisable(false);
			clearFields();
			}
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
		// Mise à jour d'un voyageur
		@FXML
		public void updateVoyageur() {
			// Vérifier si un champ n'est pas vide
			if (noEmptyInput()) {
				Voyageur voyageur = voyageursTable.getSelectionModel().getSelectedItem();

				voyageur.setNom(txtNom.getText());
				voyageur.setPrenom(txtPrenom.getText());
				voyageur.setDestination(txtDest.getText());
				voyageur.setAge(Double.parseDouble(txtAge.getText()));
				voyageur.setNumvol(Double.parseDouble(txtNumvol.getText()));
				voyageur.setRaison(cboRaison.getValue());
				voyageursTable.refresh();
			}
		}

		// Effacer un voyageur
		@FXML
		public void deleteVoyageur() {
			int selectedIndex = voyageursTable.getSelectionModel().getSelectedIndex();
			if (selectedIndex >= 0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Effacer");
				alert.setContentText("confirmer la suprimassion!");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK)
					voyageursTable.getItems().remove(selectedIndex);
			}

		}

		// vérifier champs vides
		private boolean noEmptyInput() {
			String errorMessage = "";
			if (txtNom.getText().trim().equals("")) {
				errorMessage += "Le champ nom ne doit pas etre vide! \n";
			}
			if (txtPrenom.getText() == null || txtPrenom.getText().length() == 0) {
				errorMessage += "Le champ prenom ne doit pas etre vide! \n";
			}
			if (txtDest.getText() == null || txtDest.getText().length() == 0) {
				errorMessage += "Le champ déstination ne doit pas etre vide! \n";
			}
			if (txtAge.getText() == null || txtAge.getText().length() == 0) {
				errorMessage += "Le champ age doit pas etre vide! \n";
			}
			if (txtNumvol.getText() == null || txtNumvol.getText().length() == 0) {
				errorMessage += "Le champ numéro de vol doit pas etre vide! \n";
			}
			if (cboRaison.getValue() == null) {
				errorMessage += "Le champ raison doit pas etre vide! \n";
			}
			if (errorMessage.length() == 0) {
				return true;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Champs manquants");
				alert.setHeaderText("Compléter les champs manquants");
				alert.setContentText(errorMessage);
				alert.showAndWait();
				return false;
			}
		}


//SAUVEGARDE DE DONNÉES

	// Récuperer le chemin (path) des fichiers si ca existe

	public File getVoyageurFilePath() 
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);

		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}

	}

	// Attribuer un chemin de fichiers

	public void setVoyageurFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
		} else {
			prefs.remove("filePath");
		}
	}

	// Prendre les données de type XML et les convertir en données de type javaFx
	public void loadVoyageurDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(VoyageurListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			VoyageurListWrapper wrapper = (VoyageurListWrapper) um.unmarshal(file);
			VoyageurData.clear();
			VoyageurData.addAll(wrapper.getVoyageur());
			setVoyageurFilePath(file);
			
			// Donner le titre du fichier chargé
			Stage pStage = (Stage) voyageursTable.getScene().getWindow();
			pStage.setTitle(file.getName());

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("les données n'ont pas été trouvées");
			alert.setContentText("Les données ne pouvaient pas etre trouvées dans le fichier : \n" + file.getPath());
			alert.showAndWait();
		}
	}

	// Prendre les données de type JavaFx et les convertir en type XML
	public void saveVoyageurDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(VoyageurListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			VoyageurListWrapper wrapper = new VoyageurListWrapper();
			wrapper.setVoyageur(VoyageurData);

			m.marshal(wrapper, file);

			// Sauvegarde dans le registre
			setVoyageurFilePath(file);
		
			// Donner le titre du fichier sauvegardé
			Stage pStage = (Stage) voyageursTable.getScene().getWindow();
			pStage.setTitle(file.getName());

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Données non sauvegardées");
			alert.setContentText(
					"Les données ne pouvaient pas etre sauvegarrdées dans le fichier :\n" + file.getPath());
			alert.showAndWait();

		}
	}
	// Commencer un nouveau
		@FXML
		private void handleNew() {
			getvoyageurData().clear();
			setVoyageurFilePath(null);
		}
		// Le FileChooser permat è l'usageer de choisir le fichier à ouvrir

		@FXML
		private void handleOpen() {
			FileChooser fileChooser = new FileChooser();

			// Permettre un filtre sur l'exstention du fichier à chercher
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
			fileChooser.getExtensionFilters().add(extFilter);

			// montrer le dialogue
			File file = fileChooser.showOpenDialog(null);

			if (file != null) {
				loadVoyageurDataFromFile(file);
			}

		}

		// Sauvergarde le fichier correspondant au voyageur actif s'il n y'a pas de
		// fichier, le menu sauvegarder sous va s'afficher
		@FXML
		private void handleSave() {
			File voyageurFile = getVoyageurFilePath();
			if (voyageurFile != null) {
				saveVoyageurDataToFile(voyageurFile);

			} else {
				handleSaveAs();

			}
		}

		// Ouvrir le FileChooser pour chemin
		@FXML
		private void handleSaveAs() {
			FileChooser fileChooser = new FileChooser();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", ".xml");
			fileChooser.getExtensionFilters().add(extFilter);

			// Sauvegarde
			File file = fileChooser.showSaveDialog(null);

			if (file != null) {
				// Verification de l'exstention
				if (!file.getPath().endsWith(".xml")) {
					file = new File(file.getPath() + ".xml");
				}
				saveVoyageurDataToFile(file);
			}
		}
}



