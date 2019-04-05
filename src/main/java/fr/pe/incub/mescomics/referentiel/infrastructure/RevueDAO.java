package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import fr.pe.incub.mescomics.referentiel.domaine.exception.ParutionRevueTermineeException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Glossary
/**
 * Une parution sortant régulièrement (généralement mensuellement) et relatant les aventures de un ou plusieurs héros.
 */
@Entity
public class RevueDAO {
    @Id
    @GeneratedValue
    public long id;

    public final String titre;
    private int nombreDeNumeros;
    public final String nomDeLEditeur;
    private boolean serieEnCours;

    public RevueDAO(String titre, int nombreDeNumeros, String editeur, boolean serieEnCours) {
        this.titre = titre;
        this.nombreDeNumeros = nombreDeNumeros;
        this.nomDeLEditeur = editeur;
        this.serieEnCours = serieEnCours;
    }

    public int recupereLeNombreDeNumeros() {
        return nombreDeNumeros;
    }

    public void modifieLeNombreDeNumeros(int nombreDeNumeros) throws ParutionRevueTermineeException {
        if (serieEnCours) {
            this.nombreDeNumeros = nombreDeNumeros;
        }
        else {
            throw new ParutionRevueTermineeException(titre);
        }
    }

    public boolean encoreDesNumerosAParaitre() {
        return serieEnCours;
    }

    public void termineLaSerie() {
        serieEnCours = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RevueDAO revue = (RevueDAO) o;
        return nombreDeNumeros == revue.nombreDeNumeros &&
                Objects.equals(titre, revue.titre) &&
                Objects.equals(nomDeLEditeur, revue.nomDeLEditeur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titre, nombreDeNumeros, nomDeLEditeur);
    }
}