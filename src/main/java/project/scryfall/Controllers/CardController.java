
package project.scryfall.Controllers;


import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import project.scryfall.Entities.Card;
import project.scryfall.Entities.CardListResponse;





@RestController
public class CardController {
    
    private final RestTemplate restTemplate;

    @Autowired
    public CardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    //retorna la carta nombrada en la base de datos de scryfall
    @GetMapping("/test/{cardName}")
    public Card getCard(@PathVariable String cardName){
        String url = "https://api.scryfall.com/cards/named?fuzzy=" + cardName;
        return restTemplate.getForObject(url, Card.class);
        
    }

    //retorna una lista con todas las versiones de la carta nombrada en la base de datos de scryfall
@GetMapping("/scryfall/cardList/{name}")
public CardListResponse getListCard(@PathVariable String name) {
    Card card = getCard(name);
    String oracleID = card.getOracle_id().toString();
    URI uri = UriComponentsBuilder.fromUriString("https://api.scryfall.com/cards/search")
            .queryParam("order", "released")
            .queryParam("q", "oracleid:" + oracleID)
            .queryParam("unique", "prints")
            .build()
            .toUri();
    return restTemplate.getForObject(uri, CardListResponse.class);
}


    //este endpoint testea el url de la carta en la base de datos de scryfall
    @GetMapping("/scryfall/test/{name}")
    public String testUrl(@PathVariable String name){
        Card card = getCard(name);
        String oracleID = card.getOracle_id().toString();
        String url = "https://api.scryfall.com/cards/search?order=released&q=oracleid%3A" + oracleID + "&unique=prints";
        return url;
    }
    
    
}
