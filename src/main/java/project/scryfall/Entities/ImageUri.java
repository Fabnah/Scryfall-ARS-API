
package project.scryfall.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageUri {
    private String small;
    private String normal;
    private String large;
    private String png;
    private String art_crop;
    private String border_crop;
    
    
    public static ImageUri addNewImageUri(String small,
            String normal,
            String large,
            String png,
            String art_crop,
            String border_crop){
        ImageUri uri = new ImageUri();
        uri.small = small;
        uri.normal = normal;
        uri.large = large;
        uri.png = png;
        uri.art_crop = art_crop;
        uri.border_crop = border_crop;
        
        return uri;
        
    }
}
