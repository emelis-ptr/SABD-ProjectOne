package entity;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;

import java.io.Serializable;

public class PuntiSomministrazioneTipologia implements Serializable {

    private final String area;                        //regione
    private final String denominazioneStruttura;     //presidio di somministrazione
    private final String tipologia;                   //Tipologia del presidio di somministrazione: ospedaliero o territoriale
    private final String codiceNUTS1;                //Classificazione europea delle unità territoriali NUTS: livello NUTS 1
    private final String codiceNUTS2;             //Classificazione europea delle unità territoriali NUTS: livello NUTS 2
    private final Integer codiceRegioneISTAT;       //Codice ISTAT della Regione
    private final String nomeRegione;                //Denominazione standard dell’area (dove necessario denominazione bilingue)

    public PuntiSomministrazioneTipologia(String area, String denominazioneStruttura, String tipologia, String codiceNUTS1, String codiceNuts2, Integer codiceRegioneISTAT, String nomeRegione) {
        this.area = area;
        this.denominazioneStruttura = denominazioneStruttura;
        this.tipologia = tipologia;
        this.codiceNUTS1 = codiceNUTS1;
        this.codiceNUTS2 = codiceNuts2;
        this.codiceRegioneISTAT = codiceRegioneISTAT;
        this.nomeRegione = nomeRegione;
    }

    public String getArea() {
        return area;
    }

    public String getDenominazioneStruttura() {
        return denominazioneStruttura;
    }

    public String getNomeRegione() {
        return nomeRegione;
    }

    public static JavaRDD<PuntiSomministrazioneTipologia> getInstance(JavaRDD<Row> dataset) {
        return
                dataset.map(
                        line -> new PuntiSomministrazioneTipologia(
                                line.getString(0),
                                line.getString(1),
                                line.getString(2),
                                line.getString(3),
                                line.getString(4),
                                Integer.parseInt(line.getString(5)),
                                line.getString(6)
                        ))
                        .filter(p -> p != null);
    }

    @Override
    public String toString() {
        return "PuntiSomministrazioneTipologia{" +
                ", area='" + area + '\'' +
                ", denominazione_struttura='" + denominazioneStruttura + '\'' +
                ", tipologia='" + tipologia + '\'' +
                ", codice_NUTS1='" + codiceNUTS1 + '\'' +
                ", getCodice_NUTS2='" + codiceNUTS2 + '\'' +
                ", codice_regione_ISTAT=" + codiceRegioneISTAT +
                ", nome_regione='" + nomeRegione + '\'' +
                '}';
    }
}
