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
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Services extends HomePage {

	private static final long serialVersionUID = 1L;

	static int nfil, ncol;
	private static Accommodation ac;
	private static List<Accommodation> list = new ArrayList<Accommodation>();
	private static List<Accommodation> origList = new ArrayList<Accommodation>();
	private final static Logger log = LoggerFactory.getLogger(Services.class);

	String selected = null;

	PropertyListView<Accommodation> listView;

	public Services() throws IOException {

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

		//		log.info("Size of List " + list.size());
		//		log.info("Size of origList " + origList.size());
		if (!list.isEmpty()) {
			list.clear();
		}
		if (!origList.isEmpty()) {
			origList.clear();
		}
		readFile();
		origList.addAll(list);
		displayForm();
		listView = displayAccommodation("rows", list);
		add(listView);
	}

	public PropertyListView<Accommodation> displayAccommodation(String id, List<Accommodation> list) {

		PropertyListView<Accommodation> listView = new PropertyListView<Accommodation>(id, list) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final ListItem<Accommodation> item) {
				final Accommodation ac = item.getModelObject();
				item.add(new Label("name", new PropertyModel<Accommodation>(ac, "name")));
				item.add(new Label("type", ac.getType()));
				item.add(new Label("category", ac.getCategory()));
				if (ac.getPlace() != null) {
					item.add(new Label("placeTag", "Situación: "));
					item.add(new Label("place", ac.getPlace()));
				} else {
					item.add(new Label("placeTag").setVisible(false));
					item.add(new Label("place").setVisible(false));
				}
				item.add(new Label("address", ac.getAddress()));
				if (ac.getPostalCode() != null) {
					item.add(new Label("postalcodeTag", ", "));
					item.add(new Label("postalCode", ac.getPostalCode()));
				} else {
					item.add(new Label("postalcodeTag").setVisible(false));
					item.add(new Label("postalCode").setVisible(false));
				}
				if (ac.getxCoordinate() != null || ac.getyCoordinate() != null) {
					item.add(new Label("xCoordinate", ac.getxCoordinate()));
					item.add(new Label("yCoordinate", ac.getyCoordinate()));
				} else {
					item.add(new Label("xCoordinate").setVisible(false));
					item.add(new Label("yCoordinate").setVisible(false));
				}
				if (ac.getAltitude() != null) {
					item.add(new Label("altitude", ac.getAltitude()));
				} else {
					item.add(new Label("altitude").setVisible(false));
				}
				item.add(new Label("municipality", ac.getMunicipality()));
				item.add(new Label("availability", ac.getAvailability()));
				item.add(new Label("totalPlaces", ac.getTotalPlaces()));
				if (ac.getPhone() != null) {
					item.add(new Label("phoneTag", "Teléfono: "));
					item.add(new Label("phone", ac.getPhone()));
				} else {
					item.add(new Label("phoneTag").setVisible(false));
					item.add(new Label("phone").setVisible(false));
				}
				if (ac.getFax() != null) {
					item.add(new Label("faxTag", "Fax: "));
					item.add(new Label("fax", ac.getFax()));
				} else {
					item.add(new Label("faxTag").setVisible(false));
					item.add(new Label("fax").setVisible(false));
				}
				if (ac.getEmail() != null) {
					item.add(new Label("emailTag", "Email: "));
					item.add(new ExternalLink("email", "mailto:" + ac.getEmail(), ac.getEmail()));
				} else {
					item.add(new Label("emailTag").setVisible(false));
					item.add(new Label("email").setVisible(false));
				}
				if (ac.getLink() != null) {
					item.add(new Label("websiteTag", "Web: "));
					item.add(new ExternalLink("link", "http://" + ac.getLink(), ac.getLink()));
				} else {
					item.add(new Label("websiteTag").setVisible(false));
					item.add(new Label("link").setVisible(false));
				}
				item.add(new ExternalLink("googlelink", "http://www.google.com/search?q=" + ac.getName().replace(" ", "+")));
			}
		};
		return listView;
	}

	public void displayForm() {

		final Form<Accommodation> formTypes = new Form<Accommodation>("formTypes"); // (1)
		add(formTypes);
		final Form<Accommodation> formCategories = new Form<Accommodation>("formCategories"); // (1)
		add(formCategories);
		final Form<Accommodation> formPlaces = new Form<Accommodation>("formPlaces"); // (1)
		add(formPlaces);

		List<String> types = new ArrayList<String>();
		for (Accommodation ac : list) {
			String type = ac.getType();
			if (!types.contains(type)) {
				types.add(type);
			}
		}

		List<String> categories = new ArrayList<String>();
		for (Accommodation ac : list) {
			String category = ac.getCategory();
			if (!categories.contains(category)) {
				categories.add(category);
			}
		}

		List<String> places = new ArrayList<String>();
		for (Accommodation ac : list) {
			String place = ac.getPlace();
			if (!places.contains(place)) {
				places.add(place);
			}
		}

		formTypes.add(new DropDownChoice<String>("types", new Model<String>(), types) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSelectionChanged(String newSelection) {
				selected = newSelection;
				list.clear();
				for (Accommodation ac : origList) {
					if (ac.getType().equals(selected)) {
						list.add(ac);
					}
				}
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}

		});

		formCategories.add(new DropDownChoice<String>("categories", new Model<String>(), categories) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSelectionChanged(String newSelection) {
				selected = newSelection;
				list.clear();
				for (Accommodation ac : origList) {
					if (ac.getCategory().equals(selected)) {
						list.add(ac);
					}
				}
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}

		});

		formPlaces.add(new DropDownChoice<String>("places", new Model<String>(), places) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSelectionChanged(String newSelection) {
				selected = newSelection;
				list.clear();
				for (Accommodation ac : origList) {
					if (ac.getPlace() != " ") {
						if (ac.getPlace().equals(selected)) {
							list.add(ac);
						}
					}
				}
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}

		});
	}

	private void readFile() throws IOException {

		File f = new File("/Users/David/Dropbox/bigdata_shared/remote/bigdata/alojamientos.csv");
		if (!f.exists()) {
			log.info("Error file does not exist");
		} else {
			FileReader fr = null;
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			nfil = 0;
			ncol = 0;
			String linea = "";
			br.readLine();
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
						if (atributos[ncol].equals("")) {
							ac.setPlace(null);
						} else {
							ac.setPlace(atributos[ncol]);
						}
						break;
					case 4:
						ac.setAddress(atributos[ncol]);
						break;
					case 5:
						if (!atributos[ncol].contains("0")) {
							if (atributos[ncol].contains("-")) {
								ac.setAddress(atributos[4] + " " + Integer.valueOf(atributos[ncol].split("-")[0]));
							} else {
								ac.setAddress(atributos[4] + " " + Integer.valueOf(atributos[ncol]));
							}
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
						if (atributos[ncol].equals("0")) {
							ac.setxCoordinate(null);
						} else {
							if (atributos[ncol].contains(",")) {
								ac.setxCoordinate(Float.valueOf(atributos[ncol].replace(",", ".")));
							} else {
								ac.setxCoordinate(Float.valueOf(atributos[ncol]));
							}
						}
						break;
					case 8:
						if (atributos[ncol].equals("0")) {
							ac.setyCoordinate(null);
						} else {
							if (atributos[ncol].contains(",")) {
								ac.setyCoordinate(Float.valueOf(atributos[ncol].replace(",", ".")));
							} else {
								ac.setyCoordinate(Float.valueOf(atributos[ncol]));
							}
						}
						break;
					case 9:
						if (atributos[ncol].equals("0")) {
							ac.setAltitude(null);
						} else {
							ac.setAltitude(Float.valueOf(atributos[ncol]));
						}
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
						if (atributos[ncol].equals("")) {
							ac.setPhone(null);
						} else {
							ac.setPhone(atributos[ncol]);
						}
						break;
					case 14:
						if (atributos[ncol].equals("")) {
							ac.setFax(null);
						} else {
							ac.setFax(atributos[ncol]);
						}
						break;
					case 15:
						if (atributos[ncol].equals("")) {
							ac.setEmail(null);
						} else {
							ac.setEmail(atributos[ncol]);
						}
						break;
					case 16:
						ac.setLink(atributos[ncol]);
						break;
					}
					ncol++;
				}
				list.add(ac);
				log.info("fila ");
				if (nfil == 2) {
					break;
				}
				nfil++;
			}
			br.close();
			fr.close();
		}
	}
}