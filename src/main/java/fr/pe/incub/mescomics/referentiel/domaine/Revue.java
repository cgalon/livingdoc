package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.livingdoc.glossaire.Glossary;
import fr.pe.incub.mescomics.referentiel.domaine.exception.ParutionRevueTermineeException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@Glossary
/**
 * Une parution sortant régulièrement (généralement mensuellement) et relatant les aventures de un ou plusieurs héros.
 */
@ApiModel(description = "Les informations d'une revue de référence.")
public class Revue {
    @ApiModelProperty(notes = "Titre de la revue")
    public final String titre;
    @ApiModelProperty(notes = "Nombre de numéros existants pour cette revue")
    private int nombreDeNumeros;
    @ApiModelProperty(notes = "Nom de l'éditeur de la revue")
    public final String nomDeLEditeur;
    @ApiModelProperty(notes = "Est-ce que la série continue de paraître?")
    private boolean serieEnCours;

    public Revue(String titre, int nombreDeNumeros, String editeur, boolean serieEnCours) {
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
        Revue revue = (Revue) o;
        return nombreDeNumeros == revue.nombreDeNumeros &&
                Objects.equals(titre, revue.titre) &&
                Objects.equals(nomDeLEditeur, revue.nomDeLEditeur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titre, nombreDeNumeros, nomDeLEditeur);
    }
}