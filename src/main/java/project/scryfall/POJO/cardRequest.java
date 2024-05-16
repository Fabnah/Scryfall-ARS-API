
package project.scryfall.POJO;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class cardRequest {
    private Map<String, String> cardName;
    private boolean foil;
    private boolean nonfoil;
    

    
}
