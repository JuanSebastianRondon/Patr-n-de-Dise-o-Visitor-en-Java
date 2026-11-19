package con_patron.model;

import con_patron.visitor.CarritoVisitor;

/**
 * Interfaz Element.
 * Solo define la información básica del producto y el método accept(Visitor).
 * 
 * BENEFICIO:
 * Esta interfaz ya NO contiene lógica de impuestos, ni de envíos, ni de serialización.
 * Las clases permanecen limpias y enfocadas únicamente en sus datos de dominio.
 */
public interface ItemCarrito {
    String getNombre();
    double getPrecio();
    
    // Método clave para el Doble Despacho (Double Dispatch)
    void accept(CarritoVisitor visitor);
}
