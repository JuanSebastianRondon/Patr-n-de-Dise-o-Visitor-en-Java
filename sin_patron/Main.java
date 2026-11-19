package sin_patron;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ItemCarrito> carrito = new ArrayList<>();
        carrito.add(new ProductoFisico("Monitor Gamer 27\"", 300.0, 5.0, true));
        carrito.add(new ProductoDigital("Curso Completo de Java", 49.99, 1200));
        carrito.add(new Suscripcion("Plataforma Streaming", 9.99, 12));

        System.out.println("==================================================");
        System.out.println("     SISTEMA SIN PATRÓN VISITOR");
        System.out.println("==================================================");

        double totalEnvio = 0.0;
        double totalProductosConImpuesto = 0.0;

        System.out.println("\n--- Detalle de Factura ---");
        for (ItemCarrito item : carrito) {
            System.out.println(item.obtenerDetalleFactura());
            totalEnvio += item.calcularEnvio();
            totalProductosConImpuesto += item.calcularTotalConImpuesto();
        }

        System.out.println("\n--- Resumen Final ---");
        System.out.printf("Subtotal con impuestos: $%.2f%n", totalProductosConImpuesto);
        System.out.printf("Costo total de envío:   $%.2f%n", totalEnvio);
        System.out.printf("GRAN TOTAL A PAGAR:     $%.2f%n", (totalProductosConImpuesto + totalEnvio));
        System.out.println("==================================================");

        System.out.println("\n¿Qué pasa si mañana nos piden exportar el carrito a JSON o calcular puntos de fidelidad?");
        System.out.println("-> Tenemos que abrir ItemCarrito, ProductoFisico, ProductoDigital y Suscripcion para agregar nuevos métodos.");
        System.out.println("-> Violamos SRP (las entidades mezclan datos con logística, impuestos y formateo).");
        System.out.println("-> Violamos OCP (modificamos clases existentes en vez de extenderlas).");
    }
}
