package com.checktenerife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller extends PostgreConnection {

	private final static Logger log = LoggerFactory.getLogger(Controller.class);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Controller c = new Controller();
		c.Start();

		PostgreConnection con = new PostgreConnection();
		if (con != null) {
			log.info("Connection done!");
		} else {
			log.info("No connection!!");
		}

	}

	private final String fichero_entrada = "alojamientos.csv"; // fichero de entrada
	private final String fichero_salida = "gastos.csv"; // fichero de salida
	private final int NMAX = 1000; // constante para el tama�o inicial de la tabla
	static int nfil; // recorre cada fila
	static int ncol; // recorre cada columna
	static String datos[][]; // guarda cada campo del fichero de entrada csv

	public Controller() throws ClassNotFoundException, SQLException {
		super();
	}

	public void getDatos(String nombrefich) throws IOException {

		File f = new File(nombrefich);
		if (!f.exists()) {
			System.out.println("\t\t Error! El fichero con nombre " + f + " no existe!. ");
		}

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr); // buffer para la lectura del fichero

		datos = new String[NMAX][NMAX];

		nfil = 0;
		ncol = 0;
		String linea = "";
		br.readLine(); // lectura nula primera linea
		while ((linea = br.readLine()) != null) { // leo cada linea

			String[] atributos = linea.split(";"); // separo cada atributo	
			ncol = 0;
			while (ncol < atributos.length) { // recorro columna de la linea

				//System.out.println("Fila " + nfil + " column " + ncol);
				datos[nfil][ncol] = atributos[ncol]; // a�adimos atributo en la tabla		
				if (ncol == 3) { // comprobaci�n para el cargo realizado

				}
				ncol++;
			}
			nfil++;
		}

		br.close();
		fr.close();

	}

	public void setDatos(ArrayList<String> cargo, float total_costo[]) throws IOException {

		File f = new File(fichero_salida);
		if (!f.exists()) {
			f.createNewFile();
		}

		FileWriter fw = new FileWriter(f);
		for (int i = 0; i < cargo.size(); i++) {
			if (total_costo[i] != 0) {
				fw.write(cargo.get(i) + "; " + total_costo[i] + "\n");
			}
		}
		fw.close();
	}

	public void Start() {
		try {
			getDatos(fichero_entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
