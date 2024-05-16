
package project.scryfall.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardArs {
    @Id
    private Long mtgo_id_ars;
    private UUID oracle_id;
    private String nameArs;
    private Double cmc_ars;
    private String artist;
    @Embedded
    private PricesArs pricesArs;
    private String lang;
    @Embedded
    private ImageUri image_uris;
    private String mana_cost;
    private String type_line;
    private ArrayList<String> colors;
    private boolean foil;
    private boolean nonfoil;
    private boolean oversized;
    private boolean promo;
    @JsonProperty("set")
    private String set_short;
    private String set_name;
    private String rarity;
    private String collector_number;
    private boolean full_art;
    private boolean textless;
    private int stock;
    
    
    
    //metodo que usamos para crear solo un constructor
    public static CardArs newCardArs(Long mtg_id_ars,
            UUID oracle_id,
            String nameArs, 
            Double cmc_ars, 
            String artist, 
            PricesArs pricesArs,
            String lang,
            ImageUri image_uris,
            String mana_cost,
            String type_line,
            ArrayList<String> colors,
            boolean foil,
            boolean nonfoil,
            boolean oversized,
            boolean promo,
            String set,
            String set_name,
            String rarity,
            String collector_number,
            boolean full_art,
            boolean textless,
            int stock){
        CardArs newCard = new CardArs();
        newCard.mtgo_id_ars = mtg_id_ars;
        newCard.oracle_id = oracle_id;
        newCard.nameArs = nameArs;
        newCard.cmc_ars = cmc_ars;
        newCard.artist = artist;
        newCard.pricesArs = pricesArs;
        newCard.lang = lang;
        newCard.image_uris = image_uris;
        newCard.mana_cost = mana_cost;
        newCard.type_line = type_line;
        newCard.colors = colors;
        newCard.foil = foil;
        newCard.nonfoil = nonfoil;
        newCard.oversized = oversized;
        newCard.promo = promo;
        newCard.set_short = set;
        newCard.set_name = set_name;
        newCard.rarity = rarity;
        newCard.collector_number = collector_number;
        newCard.full_art = full_art;
        newCard.textless = textless;
        newCard.stock = stock;
        return newCard;
    }
    
}
