package project.scryfall.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import project.scryfall.Entities.Card;
import project.scryfall.Entities.CardArs;
import project.scryfall.Entities.ImageUri;
import project.scryfall.Entities.PrecioDolar;
import project.scryfall.Entities.PricesArs;
import project.scryfall.POJO.cardRequest;
import project.scryfall.Services.CardArsServices;

@RestController
public class CardArsController {
    private final RestTemplate restTemplate;
    private final CardArsServices serv;
    private final CardController cc;

    @Autowired
    public CardArsController(RestTemplate restTemplate, CardArsServices serv, CardController cc) {
        this.restTemplate = restTemplate;
        this.serv = serv;
        this.cc = cc;
    }

    //retorna el precio del dolar
    @GetMapping("/test/dolar")
    public int priceDolarBlue() {
        String url = "https://dolarapi.com/v1/dolares/blue";
        PrecioDolar precio = restTemplate.getForObject(url, PrecioDolar.class);
        if (precio != null) {
            return precio.getVenta();
        } else {
            throw new RuntimeException("No se pudo obtener el precio de venta del dólar blue.");
        }
    }

    //agrega una carta ya pesificada a la base de datos de la app
    @PostMapping("/cardArs/add")
    public void addCardArs(@RequestBody cardRequest cardReq) {
        String cardName = cardReq.getCardName().get("cardName");
        boolean foil = cardReq.isFoil();
        boolean nonfoil = cardReq.isNonfoil();

        Card card = cc.getCard(cardName);
        

        Double eur_ars = serv.getConvertedPrice(card.getPrices().getEur(), priceDolarBlue());
        Double eur_foil_ars = serv.getConvertedPrice(card.getPrices().getEur_foil(), priceDolarBlue());
        Double tix_ars = serv.getConvertedPrice(card.getPrices().getTix(), priceDolarBlue());
        Double usd_ars = serv.getConvertedPrice(card.getPrices().getUsd(), priceDolarBlue());
        Double usd_etched_ars = serv.getConvertedPrice(card.getPrices().getUsd_etched(), priceDolarBlue());
        Double usd_foil_ars = serv.getConvertedPrice(card.getPrices().getUsd_foil(), priceDolarBlue());

        //usamos el metodo newPricesArs que creamos en la entidad PriceArs
        PricesArs PriceControl = PricesArs.newPricesArs(usd_ars,
                usd_foil_ars,
                usd_etched_ars,
                eur_ars,
                eur_foil_ars,
                tix_ars);

        //llamamos el metodo desde la clase imageuri
        ImageUri imageUri = ImageUri.addNewImageUri(card.getImage_uris().getSmall(), 
                card.getImage_uris().getNormal(), 
                card.getImage_uris().getLarge(), 
                card.getImage_uris().getPng(), 
                card.getImage_uris().getArt_crop(), 
                card.getImage_uris().getBorder_crop());
        
        //usamos el metodo newCardArs que creamos en la entidad cardArs
        CardArs cardArsObject = CardArs.newCardArs(card.getMtgo_id(), card.getOracle_id(),
                card.getName(), card.getCmc(), card.getArtist(), 
                PriceControl, card.getLang(), imageUri, 
                card.getMana_cost(), card.getType_line(), card.getColors(), 
                 foil, nonfoil, 
                card.isOversized(), card.isPromo(), card.getSet_short(), card.getSet_name(), 
                card.getRarity(), card.getCollector_number(), card.isFull_art(), card.isTextless(),
                1);
                
                
                
        serv.addCardArs(cardArsObject);
    }

    
    //busca una carta en la DB de la app
    @GetMapping("/cardArs/{name}")
    public CardArs readCardArs(@PathVariable String name) {
        return serv.searchCard_Ars(name);
    }
    //elimina una carta de la DB de la app
    @DeleteMapping("/cardArs/{name}")
    public void deleteCards(@PathVariable String name) {
        serv.deleteCardArs(name);
    }
    //retorna unalista con todas las cartas de la DB de la app
    @GetMapping("/cardArs/all")
    public List<CardArs> allCards(){
        return serv.allCardsService();
    } 
    //retorna una lista de todas las cartas que posean el color elegido
    @GetMapping("/cardArs/color/{color}")
    public List<CardArs> colorList(@PathVariable String color){
        return serv.ListFromAColor(color);
    }
    //retorna una lista con todas las cartas que posean los colores elegidos
    @GetMapping("/cardArs/color/{color1}/{color2}")
    public List<CardArs> multiColor(@PathVariable String color1, 
                                    @PathVariable String color2){
        return serv.MultiColorList(color1, color2);
    }
    
    //retorna una lista con todas las cartas con el cmc elegido
    @GetMapping("/cardArs/lista/{cmc}")
    public List<CardArs> listaCMC(@PathVariable Double cmc){
        return serv.cmcList(cmc);
    }
    
    //retorna una lista con todas las cartas con el cmc elegido o mas
    @GetMapping("/cardArs/listaMinimo/{cmc}")
    public List<CardArs> ListaMinCmc(@PathVariable Double cmc){
        return serv.cmcMinList(cmc);
    }
    
    
    //agrega una carta al stock de la DB
    @PutMapping("/cardArs/add/{name}")
    public void addCardInStock(@PathVariable String name){
        CardArs card = serv.searchCard_Ars(name);
        if (card != null) {
            card.setStock(card.getStock()+1);
        }
    }
    //remueve una carta del stock de la DB
    @PutMapping("/cardArs/remove/{name}")
    public void removeCardInStock(@PathVariable String name){
        CardArs card = serv.searchCard_Ars(name);
        if (card != null) {
            card.setStock(card.getStock()-1);
        }
    }
    
    
    
    
}
