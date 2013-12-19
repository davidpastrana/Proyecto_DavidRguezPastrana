package com.checktenerife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Services extends HomePage {

	private final String in = "/Users/David/Dropbox/bigdata_shared/remote/bigdata/alojamientos.csv"; // fichero de entrada
	static int nfil, ncol;
	private static Accommodation ac;
	private static List<Accommodation> list = new ArrayList<Accommodation>();
	private final static Logger log = LoggerFactory.getLogger(Services.class);

	public Services() {

		//		//Connection to database
		//		PostgreConnection con = new PostgreConnection();
		//		if (con != null) {
		//			log.info("Connection done!");
		//
		//			String sql = "INSERT INTO USUARIOS (NOMBRE,APELLIDOS,EMAIL,TELEFONO,USUARIO,PASSWORD) VALUES (?,?,?,?,?,?)";
		//			PreparedStatement st = con.getConnection().prepareStatement(sql);
		//
		//			Accommodation ac;
		//			int id = 0;
		//			st.setString(1, ac.getName());
		//			st.setString(2, ac.getType());
		//			st.setString(3, nuevo.getEmail());
		//			st.setString(4, nuevo.getTelefono());
		//			st.setString(5, nuevo.getUsuario());
		//			st.setString(6, nuevo.getPassword());
		//			id = st.executeUpdate();
		//			st.close();
		//
		//		} else {
		//			log.info("No connection!!");
		//		}

		//Read Excel file
		try {
			readAccommodationFile(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		displayForm();

		//Display data
		displayAccommodation();
	}

	public void displayAccommodation() {
		//		Form form = null;
		//		List<String> types = Arrays.asList("apple", "strawberry", "watermelon");
		//		form.add(new DropDownChoice<String>("types", new Model(), types));

		PropertyListView<Accommodation> listView = new PropertyListView<Accommodation>("rows", list) {
			private static final long serialVersionUID = 1L;

			public void onClick() {

			}

			@Override
			public void populateItem(final ListItem<Accommodation> item) {
				final Accommodation ac = item.getModelObject();
				item.add(new Label("name", new PropertyModel<Accommodation>(ac, "name")));
				item.add(new Label("type", ac.getType()));
				item.add(new Label("category", ac.getCategory()));
				item.add(new Label("place", ac.getPlace()));
				item.add(new Label("address", ac.getAddress()));
				item.add(new Label("number", ac.getNumber()));
				item.add(new Label("postalCode", ac.getPostalCode()));
				item.add(new Label("xCoordinate", ac.getxCoordinate()));
				item.add(new Label("yCoordinate", ac.getyCoordinate()));
				item.add(new Label("altitude", ac.getAltitude()));
				item.add(new Label("municipality", ac.getMunicipality()));
				item.add(new Label("availability", ac.getAvailability()));
				item.add(new Label("totalPlaces", ac.getTotalPlaces()));
				item.add(new Label("phone", ac.getPhone()));
				item.add(new Label("fax", ac.getFax()));
				item.add(new Label("email", ac.getEmail()));
				String fullName = ac.getName();
				String googleQuery = "http://www.google.com/search?q=" + fullName.replace(" ", "+");
				item.add(new Label("link", ac.getLink()));
				item.add(new Label("googlelink", googleQuery));
			}
		};
		add(listView);
	}

	public void displayForm() {

		Form<Accommodation> form = new Form<Accommodation>("form"); // (1)
		add(form);
		List<String> types = new ArrayList<String>();
		for (Accommodation ac : list) {
			log.info("Tipo " + ac.getType());
			String type = ac.getType();
			if (!types.contains(type)) {
				types.add(type);
			}
		}
		form.add(new DropDownChoice<String>("types", new Model<String>(), types));
		//		addLocationForm.add(nameLabel);
		//		TextField<String> nameField = new TextField<String>("name"); // (3)
		//		addLocationForm.add(nameField);
		//		Button submitButton = new Button("submitButton") { // (4)
		//			@Override
		//			public void onSubmit() {
		//				System.out.println("OnSubmit, name = " + name);
		//			}
		//		};
		//		addLocationForm.add(submitButton);

	}

	public void readAccommodationFile(String in) throws IOException {

		File f = new File(in);
		if (!f.exists()) {
			log.info("Error file does not exist");
		} else {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			nfil = 0;
			ncol = 0;
			String linea = "";
			br.readLine(); //first line depreciated
			while ((linea = br.readLine()) != null) {
				ncol = 0;
				String[] atributos = linea.split(";");
				ac = new Accommodation();
				while (ncol < atributos.length) {

					switch (ncol) {
					case 0:
						ac.setName(atributos[ncol]);
						break;
					case 1:
						ac.setType(atributos[ncol]);
						break;
					case 2:
						ac.setCategory(atributos[ncol]);
						break;
					case 3:
						ac.setPlace(atributos[ncol]);
						break;
					case 4:
						ac.setAddress(atributos[ncol]);
						break;
					case 5:
						if (atributos[ncol].contains("-")) {
							ac.setNumber(Integer.valueOf(atributos[ncol].split("-")[0]));
						} else {
							ac.setNumber(Integer.valueOf(atributos[ncol]));
						}
						break;
					case 6:
						if (atributos[ncol].contains("NULL")) {
							ac.setPostalCode(null);
						} else {
							ac.setPostalCode(Integer.valueOf(atributos[ncol]));
						}
						break;
					case 7:
						if (atributos[ncol].contains(",")) {
							ac.setxCoordinate(Float.valueOf(atributos[ncol].replace(",", ".")));
						} else {
							ac.setxCoordinate(Float.valueOf(atributos[ncol]));
						}
						break;
					case 8:
						if (atributos[ncol].contains(",")) {
							ac.setyCoordinate(Float.valueOf(atributos[ncol].replace(",", ".")));
						} else {
							ac.setyCoordinate(Float.valueOf(atributos[ncol]));
						}
						break;
					case 9:
						ac.setAltitude(Float.valueOf(atributos[ncol]));
						break;
					case 10:
						ac.setMunicipality(atributos[ncol]);
						break;
					case 11:
						ac.setAvailability(Integer.valueOf(atributos[ncol]));
						break;
					case 12:
						ac.setTotalPlaces(Integer.valueOf(atributos[ncol]));
						break;
					case 13:
						ac.setPhone(atributos[ncol]);
						break;
					case 14:
						ac.setFax(atributos[ncol]);
						break;
					case 15:
						ac.setEmail(atributos[ncol]);
						break;
					case 16:
						ac.setLink(atributos[ncol]);
						break;
					}
					ncol++;
				}
				list.add(ac);
				nfil++;
			}
			br.close();
			fr.close();
		}
	}
}