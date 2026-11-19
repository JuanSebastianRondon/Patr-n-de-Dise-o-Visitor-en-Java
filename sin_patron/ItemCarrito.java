package sin_patron;

/**
 * Interfaz sin el patrón Visitor.
 * 
 * PROBLEMA:
 * Cada vez que la empresa necesita una nueva operación sobre el carrito
 * (por ejemplo: exportar a JSON, calcular puntos de fidelidad, etc.),
 * nos vemos obligados a MODIFICAR esta interfaz y TODAS las clases que la implementan.
 * Esto viola directamente el Principio Abierto/Cerrado (OCP - Open/Closed Principle).
 */
public interface ItemCarrito {
    String getNombre();
    double getPrecioBase();

    // Métodos de operaciones mezclados con el modelo de datos (Violación de SRP)
    double calcularEnvio();
    double calcularTotalConImpuesto();
    String obtenerDetalleFactura();
}
