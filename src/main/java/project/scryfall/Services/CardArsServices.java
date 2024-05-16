
package project.scryfall.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.scryfall.Entities.CardArs;
import project.scryfall.Repositories.IcardArsRepository;

@Service
public class CardArsServices implements ICardArsService{

    @Autowired
    IcardArsRepository repo;
    
    
    @Override
    public CardArs searchCard_Ars(String name_ars) {
        return repo.findByNameArs(name_ars);
    }

    @Override
    public void addCardArs(CardArs cardArs) {
        repo.save(cardArs);
    }

    @Override
    public void deleteCardArs(String name_ars) {
        repo.deleteById(repo.findByNameArs(name_ars).getMtgo_id_ars());
    }

    //obtenes el valor en pesos
    public Double getConvertedPrice(String price, int multiplier){
        return (price != null) ? Double.parseDouble(price)*multiplier:0.0;
    }

    @Override
    public List<CardArs> allCardsService() {
        return repo.findAll();
    }

    //servicio para obtener una lista con todas las cartas del color elegidos
    @Override
    public List<CardArs> ListFromAColor(String color) {
        ArrayList<CardArs> colorList = new ArrayList<>();
        
        for (CardArs card : allCardsService()) {
            if ((card.getColors().size() == 1) && (card.getColors().contains(color))) {
                colorList.add(card);
            }
        }
        colorList.sort((card1, card2) -> card1.getCmc_ars().compareTo(card2.getCmc_ars()));
        return colorList;
    }

    //servicio para obtener una lista con todas las cartas de los colores elegidos
    @Override
    public List<CardArs> MultiColorList(String color1, String color2) {
        ArrayList<CardArs> multiColor = new ArrayList<>();
        
        for (CardArs card : allCardsService()) {
            if ((card.getColors().contains(color1)) && (card.getColors().contains(color2))) {
                multiColor.add(card);
            }
        }
        
        multiColor.sort((card1, card2)->card1.getCmc_ars().compareTo(card2.getCmc_ars()));
        
        return multiColor;
    }

    @Override
    public List<CardArs> cmcList(Double cmc) {
        ArrayList<CardArs> listaCartas = new ArrayList<>();
        
        for (CardArs carta : listaCartas) {
            if (Objects.equals(carta.getCmc_ars(), cmc)) {
                listaCartas.add(carta);
            }
        }
        listaCartas.sort((card1, card2)->card1.getCmc_ars().compareTo(card2.getCmc_ars()));
        return listaCartas;
    }

    @Override
    public List<CardArs> cmcMinList(Double cmcMin) {
        ArrayList<CardArs> lista = new ArrayList<>();
        
        for (CardArs carta : lista) {
            if (carta.getCmc_ars() >= cmcMin) {
                lista.add(carta);
            }
        }
        lista.sort((card1, card2)->card1.getCmc_ars().compareTo(card2.getCmc_ars()));
        return lista;
        
    }
}
    
