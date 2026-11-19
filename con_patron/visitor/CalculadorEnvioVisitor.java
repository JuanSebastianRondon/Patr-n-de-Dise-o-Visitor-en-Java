package con_patron.visitor;

import con_patron.model.ProductoFisico;
import con_patron.model.ProductoDigital;
import con_patron.model.Suscripcion;

/**
 * ConcreteVisitor que encapsula EXCLUSIVAMENTE la lógica de cálculo de envíos.
 * Toda la regla de negocio de envíos reside en un solo lugar (SRP).
 */
public class CalculadorEnvioVisitor implements CarritoVisitor {
    private double totalEnvio = 0.0;

    @Override
    public void visit(ProductoFisico producto) {
        double costo = producto.getPesoKg() * 5.0; // $5 por kg
        if (producto.isEsFragil()) {
            costo += 10.0; // Recargo por fragilidad
        }
        totalEnvio += costo;
    }

    @Override
    public void visit(ProductoDigital producto) {
        // Los productos digitales se entregan por descarga, no tienen costo de envío
        totalEnvio += 0.0;
    }

    @Override
    public void visit(Suscripcion suscripcion) {
        // Las suscripciones son servicios sin envío físico
        totalEnvio += 0.0;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }
}
