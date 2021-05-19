package archiviojava3bit;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Si occupa dell'interfaccia utente con un menu.
 * Qui si può usare println
 * @author INSERISCI_NOME
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Creo il archivio
    Archivio1 archivio;
    archivio = new Archivio1();

    // Menu
    // System.in è la tasiera
    Scanner tastiera;
    tastiera = new Scanner(System.in);

    int scelta = 0;

    do {
      System.out.println("--- Gestione Negozio di Videogiochi ---");
      System.out.println("1) Visualizza elenco Videogiochi");
      System.out.println("2) Inserisci videogioco");
      System.out.println("3) Elimina videogioco");
      System.out.println("4) Cerca videogioco in base al prezzo");
      System.out.println("5) Cerca videogioco in base alla nome");
      System.out.println("6) Modifica videogioco");
      System.out.println("7) Esci");
      System.out.print("Scegli l'operazione (1-7): ");

      // Aspetto la scelta dell'utente
      scelta = Integer.parseInt(tastiera.nextLine()); //bloccante

      switch (scelta) {
        case 1:
          // Chiedo al archivio l'elenco di tutte le articolo
          ArrayList<Videogioco> elencoVideogiochi;
          elencoVideogiochi = archivio.elencoVideogiochi();

          visualizzaElencoVideogiochi(elencoVideogiochi);
          break;

        case 2:
          // Inserisci articolo
          // 1. Chiedo all'utente i dati del nuovo articolo da inserire         
          System.out.print("Inserisci il codice: ");
          String codice = tastiera.nextLine();
          System.out.print("Inserisci il nome: ");
          String nome = tastiera.nextLine();
          System.out.print("Inserisci La piattaforma: ");
          String piattaforma = tastiera.nextLine();
          System.out.print("Inserisci la quantita: ");
          int quantita = Integer.parseInt(tastiera.nextLine());
          System.out.print("Inserisci il prezzo: ");
          float prezzo = Float.parseFloat(tastiera.nextLine());

          // 2. Creo un oggetto articolo con i dati forniti dall'utente
          Videogioco videogioco = new Videogioco(codice,nome, piattaforma, quantita, prezzo);

          // 3. Aggiungo larticolo nel archivio
          if (archivio.aggiungi(videogioco)) {
            System.out.print("Videogioco inserito correttamente");
          } else {
            System.out.print("Esiste gia un videogioco con lo stesso codice !");
          }
          break;

        case 3:
          // 1. Chiedo all'utente il codice dell'articolo da eliminare         
          System.out.print("Inserisci il codice: ");
          codice = tastiera.nextLine();

          // 2. Elimino la articolo dal archivio         
          if (archivio.elimina(codice)) {
            System.out.println("Videogioco eliminato correttamente.");
          } else {
            System.out.println("Eliminazione non riuscita.");
          }
          break;

        case 4:
          System.out.print("Inserisci il prezzo minimo: ");
          float prezzoMinimo = Float.parseFloat(tastiera.nextLine());
          System.out.print("Inserisci il prezzo massimo: ");
          float prezzoMassimo = Float.parseFloat(tastiera.nextLine());

          elencoVideogiochi = archivio.cercaVideogioco(prezzoMinimo, prezzoMassimo);

          visualizzaElencoVideogiochi(elencoVideogiochi);
          
          break;

        case 5:
          System.out.print("Inserisci la descrizione: ");
          nome = tastiera.nextLine();

          elencoVideogiochi = archivio.cercaVideogiocoPerNome(nome);

          visualizzaElencoVideogiochi(elencoVideogiochi);
          break;
        case 6:
          System.out.print("Inserisci il codice: ");
          codice = tastiera.nextLine();
          System.out.print("Inserisci il nome: ");
          nome = tastiera.nextLine();
          System.out.print("Inserisci la piattaforma: ");
          piattaforma = tastiera.nextLine();          
          System.out.print("Inserisci la quantità: ");
          quantita = Integer.parseInt(tastiera.nextLine());
          System.out.print("Inserisci il prezzo: ");
          prezzo = Float.parseFloat(tastiera.nextLine());

          if (archivio.modificaVideogioco(codice, nome, piattaforma, quantita, prezzo)) {
            System.out.println("Modifica videogioco avvenuta correttamente.");
          } else {
            System.out.println("Non è stato possibile modificare la moto.");
          }
          break;
      }

    } while (scelta != 7);

  }

  private static void visualizzaElencoVideogiochi(ArrayList<Videogioco> elencoArticoli) {
    System.out.println("N°\tCodice\tNome\t\t\t\tPiattaforma\t\t\tQuantità \tPrezzo\t");
    for (int i = 0; i < elencoArticoli.size(); i++) {
      System.out.print(i + 1);
      System.out.print("\t|" + elencoArticoli.get(i).getCodice());
      System.out.print("\t|" + elencoArticoli.get(i).getNome());
      System.out.print("\t|" + elencoArticoli.get(i).getPiattaforma());
      System.out.print("\t|" + elencoArticoli.get(i).getQuantita());
      System.out.println("\t\t|" + elencoArticoli.get(i).getPrezzo());
    }

    System.out.println("\n");

  }
    
    }
    

