
package project.scryfall.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecioDolar {
    public String moneda;
    public String casa;
    public String nombre;
    public int compra;
    public int venta;
    public String fechaActualizacion;
    
    
}
