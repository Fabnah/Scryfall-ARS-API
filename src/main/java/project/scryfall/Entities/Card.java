
package project.scryfall.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Long mtgo_id;
    private UUID oracle_id;
    private String name;
    private Double cmc;
    private String artist;
    @Embedded
    private Prices prices;
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
    
}
