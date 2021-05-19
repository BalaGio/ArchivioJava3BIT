package archiviojava3bit;

/**
 * Classe utilizzata per l'oggetto da contenere nella collezione.
 * Contiene le varibili di istanza, il metodo costruttore, i setter/getter
 * e eventuali altri metodi
 * @author INSERISCI_NOME
 */
public class Videogioco {

  private String codice;
  private String nome;
  private String piattaforma;
  private int quantita;
  private float prezzo;

  public Videogioco(String codice, String nome, String piattaforma, int quantita, float prezzo) {
    this.codice = codice;
    this.piattaforma = piattaforma;
    this.nome = nome;
    this.quantita = quantita;
    this.prezzo = prezzo;
  }

  public String getCodice() {
    return codice;
  }
  
  public String getPiattaforma() {
    return piattaforma;
  }

    public String getNome() {
    return nome;
    }
  
  public int getQuantita() {
    return quantita;
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public void setPiattaforma(String piattaforma) {
    this.piattaforma = piattaforma;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setQuantita(int quantita) {
    this.quantita = quantita;
  }

  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }
   public String toString(){
    
    String s;
    s = codice;
    s += ",";
    s += nome;
    s += ", ";
    s += piattaforma;
    s += ",";
    s += Integer.toString(quantita);
    s += ",";
    s += Float.toString(prezzo);
    s += ",";
    return s;
  } 
 
}
