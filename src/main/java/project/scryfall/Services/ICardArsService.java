
package project.scryfall.Services;


import java.util.List;
import project.scryfall.Entities.CardArs;



public interface ICardArsService {
    
    //busca una carta por nombre
    public CardArs searchCard_Ars(String name);
            
    //agrega una carta con pesos en vez de dolares a la BD
    public void addCardArs(CardArs cardArs);
            
    //duh
    public void deleteCardArs(String name);
    
    //lista todas las cartas
    public List<CardArs> allCardsService();
      
    //lista por color
    public List<CardArs> ListFromAColor(String color);
    
    //lista por multicolor
    public List<CardArs> MultiColorList(String color1, String color2);
            
    //lista por cmc
    public List<CardArs> cmcList(Double cmc);
    
    //lista por cmc minimo
    public List<CardArs> cmcMinList(Double cmcMin);
    
}
