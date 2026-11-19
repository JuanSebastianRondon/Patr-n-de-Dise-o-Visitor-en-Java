package con_patron.visitor;

import con_patron.model.ProductoFisico;
import con_patron.model.ProductoDigital;
import con_patron.model.Suscripcion;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteVisitor que encapsula la lógica fiscal y de facturación.
 */
public class GeneradorFacturaVisitor implements CarritoVisitor {
    private double totalConImpuestos = 0.0;
    private final List<String> lineasDetalle = new ArrayList<>();

    @Override
    public void visit(ProductoFisico producto) {
        double subtotal = producto.getPrecio() * 1.19; // 19% IVA
        totalConImpuestos += subtotal;
        lineasDetalle.add(String.format(" [Físico]      %-28s Base: $%7.2f | IVA (19%%): $%6.2f | Total: $%7.2f",
                producto.getNombre(), producto.getPrecio(), producto.getPrecio() * 0.19, subtotal));
    }

    @Override
    public void visit(ProductoDigital producto) {
        double subtotal = producto.getPrecio() * 1.10; // 10% impuesto digital
        totalConImpuestos += subtotal;
        lineasDetalle.add(String.format(" [Digital]     %-28s Base: $%7.2f | Imp (10%%): $%6.2f | Total: $%7.2f",
                producto.getNombre(), producto.getPrecio(), producto.getPrecio() * 0.10, subtotal));
    }

    @Override
    public void visit(Suscripcion suscripcion) {
        double subtotal = suscripcion.getPrecio(); // Exento de impuestos
        totalConImpuestos += subtotal;
        lineasDetalle.add(String.format(" [Suscripción] %-28s Base: $%7.2f | Exento              | Total: $%7.2f (%d meses)",
                suscripcion.getNombre(), subtotal, subtotal, suscripcion.getMesesContratados()));
    }

    public double getTotalConImpuestos() {
        return totalConImpuestos;
    }

    public List<String> getLineasDetalle() {
        return lineasDetalle;
    }
}
