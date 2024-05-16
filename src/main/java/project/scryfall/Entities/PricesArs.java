
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
public class PricesArs {
    private Double usd_ars;
    private Double usd_foil_ars;
    private Double usd_etched_ars;
    private Double eur_ars;
    private Double eur_foil_ars;
    private Double tix_ars;

    //metodo para no crear mas de un constructor
    public static PricesArs newPricesArs(
            Double usd_ars,
            Double usd_foil_ars,
            Double usd_etched_ars,
            Double eur_ars,
            Double eur_foil_ars,
            Double tix_ars) {

        PricesArs ars = new PricesArs();
        ars.usd_ars = usd_ars;
        ars.usd_foil_ars = usd_foil_ars;
        ars.usd_etched_ars = usd_etched_ars;
        ars.eur_ars = eur_ars;
        ars.eur_foil_ars = eur_foil_ars;
        ars.tix_ars = tix_ars;
        return ars;

    }

}