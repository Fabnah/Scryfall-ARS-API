
package project.scryfall.Entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardListResponse {
    private String object;
    private Integer total_cards;
    private boolean has_more;
    private List<Card> data;
    
    
    
    
    
    
    
    
    
    
    
}


