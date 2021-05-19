package archiviojava3bit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Contiene gli oggetti del programma.
 * Implementata tramite un ArrayList.
 * Ci permette di inserire gli oggetti,
 * effettuare ricerche e
 * aggiungere/rimuovere/modificare un oggetto.
 * @author Giorgio Balagna
 */
public class Archivio1 {

  private ArrayList<Videogioco> archivio;

  public Archivio1() {
    archivio = leggiDaFile();
  }

  // Metodo che restituisce l'elenco degli articoli
  public ArrayList<Videogioco> elencoVideogiochi() {
    return archivio;
  }
public boolean aggiungi(Videogioco videogioco) {
    // controllo che non ci sia già un articolo con lo stesso 
    // codice
    if (Archivio1.this.cercaVideogioco(videogioco.getCodice()) != null) {
      return false;
    }

    archivio.add(videogioco);
    salvaSuFile();
    return true;
  }

  private Videogioco cercaVideogioco(String codice) {
    for (int i = 0; i < archivio.size(); i++) {
      if (archivio.get(i).getCodice().equals(codice)) {
        return archivio.get(i);
      }
    }
    return null;
  }

  public boolean elimina(String codice) {
    Videogioco videogioco;
    // Ricerco l'articolo
    videogioco = Archivio1.this.cercaVideogioco(codice);

    // Se l'articolo c'è lo cancello
    if (videogioco != null) {
      // Cancello l'articolo dall'array list
      archivio.remove(videogioco);
      //Salvo tutto nel file
      salvaSuFile();
      return true;
    }
    return false;
  }

  public boolean modificaVideogioco(String codice,String nome, String piattaforma, int quantita, float prezzo) {

    // Cerco l'articolo in base codice.
    Videogioco videogioco;
    videogioco = Archivio1.this.cercaVideogioco(codice);
    // Se è presente nell'archivio modifico le sue caratteristiche
    if (videogioco != null) {
      videogioco.setNome(nome);
      videogioco.setPiattaforma(piattaforma);
      videogioco.setQuantita(quantita);
      videogioco.setPrezzo(prezzo);

      // Salvo l'archivio su file
      salvaSuFile();
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<Videogioco> cercaVideogioco(float prezzoMinimo, float prezzoMassimo) {

    // Parto con l'array list delle articolo trovate VUOTO
    ArrayList<Videogioco> videogiochiTrovati = new ArrayList(0);

    for (int i = 0; i < archivio.size(); i++) {
      if (archivio.get(i).getPrezzo() >= prezzoMinimo
              && archivio.get(i).getPrezzo() <= prezzoMassimo) {
        // Aggiungo l'articolo in posizione i nell'array list degli articoli trovati
        videogiochiTrovati.add(archivio.get(i));
      }
    }
    return videogiochiTrovati;
  }

  // Metodo per cercare le articolo in base al modello
  public ArrayList<Videogioco> cercaVideogiocoPerNome(String nome) {

    // Parto con l'array list degli articoli trovati VUOTO
    ArrayList<Videogioco> videogiochiTrovati = new ArrayList(0);

    // Converto la descrizione da cercare in minuscolo
    String nomeDaCercareMinuscolo = nome.toLowerCase();

    for (int i = 0; i < archivio.size(); i++) {
      // Converto la descrizione dell'articolo in posizione i in minucolo
      String nomeMinuscolo = archivio.get(i).getNome().toLowerCase();

      if (nomeMinuscolo.contains(nomeDaCercareMinuscolo)) {
        // Aggiungo l'rticolo in posizione i nell'ArrayList degli
        // articoli trovati.
        videogiochiTrovati.add(archivio.get(i));
      }
    }

    // Restituisco l'ArrayList delle articolo trovate.
    return videogiochiTrovati;
  }

  // Metodo che converte tutto l'arra list delle articolo in una stringa
  public String toString() {
    String s = "";

    for (int i = 0; i < archivio.size(); i++) {
      Videogioco videogioco;
      // estraggo la articolo in posizione i
      videogioco = archivio.get(i);

      // aggiungo la articolo alla stringa
      s += videogioco.toString();

      // dopo aver aggiunto un articolo dobbiamo inserire un ritorno a capo
      s += "\r\n";
    }
    return s;
  }

  // Metodo che salva i dati nel file archivio.txt
  public void salvaSuFile() {
    FileWriter out;
    try {
      out = new FileWriter("archivio.txt");

      // Ci facciamo dare dal metodo toString la stringa che rappresenta tutto
      //l'archivio
      String sArchivio;
      sArchivio = toString();

      // Scrivo nel file la stringa ricevuta
      out.write(sArchivio);

      // Chiudo il file
      out.close();

    } catch (IOException ex) {
      Logger.getLogger(Archivio1.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private ArrayList<Videogioco> leggiDaFile() {
    //creo un array list di articoli vuoto
    ArrayList<Videogioco>videogiochi;
    videogiochi = new ArrayList(0);

    FileReader fileReader;
    try {
      fileReader = new FileReader("archivio.txt");

      // Creo un oggetto BufferedReader, in quanto contiene il metodo
      // readLine(), passandogli l'oggetto FileReader creato prima
      BufferedReader in;
      in = new BufferedReader(fileReader);

      String linea;
      String campi[];
      while ((linea = in.readLine()) != null) {
        campi = linea.split(",");
        //campi[0] --> codice
        //campi[1] --> descrizione
        //campi[2] --> quantita 
        //campi[3] --> prezzo
        int quantita = Integer.parseInt(campi[3]);
        float prezzo = Float.parseFloat(campi[4]);

        // Adesso ho i dati necessari per costruite un oggetto
        // Articolo
        Videogioco videogioco = new Videogioco(campi[0], campi[1], campi[2], quantita, prezzo);

        // aggiungo l'articolo all'ArrayList
        videogiochi.add(videogioco);
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(Archivio1.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Archivio1.class.getName()).log(Level.SEVERE, null, ex);
    }
    return videogiochi;
  }
}  

