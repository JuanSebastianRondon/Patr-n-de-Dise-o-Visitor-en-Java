package con_patron.visitor;

import con_patron.model.ProductoFisico;
import con_patron.model.ProductoDigital;
import con_patron.model.Suscripcion;

/**
 * Interfaz Visitor.
 * Declara un método visit para cada tipo de elemento concreto en la estructura.
 */
public interface CarritoVisitor {
    void visit(ProductoFisico producto);
    void visit(ProductoDigital producto);
    void visit(Suscripcion suscripcion);
}
