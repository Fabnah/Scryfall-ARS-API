
package project.scryfall.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prices {
    private String usd;
    private String usd_foil;
    private String usd_etched;
    private String eur;
    private String eur_foil;
    private String tix;


}
