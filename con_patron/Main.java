package con_patron;

import con_patron.model.ItemCarrito;
import con_patron.model.ProductoDigital;
import con_patron.model.ProductoFisico;
import con_patron.model.Suscripcion;
import con_patron.visitor.CalculadorEnvioVisitor;
import con_patron.visitor.ExportadorJsonVisitor;
import con_patron.visitor.GeneradorFacturaVisitor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ItemCarrito> carrito = new ArrayList<>();
        carrito.add(new ProductoFisico("Monitor Gamer 27\"", 300.0, 5.0, true));
        carrito.add(new ProductoDigital("Curso Completo de Java", 49.99, 1200));
        carrito.add(new Suscripcion("Plataforma Streaming", 9.99, 12));

        System.out.println("==================================================");
        System.out.println("     SISTEMA CON PATRÓN VISITOR APLICADO");
        System.out.println("==================================================");

        // OPERACIÓN 1: Facturación con impuestos
        GeneradorFacturaVisitor facturaVisitor = new GeneradorFacturaVisitor();
        for (ItemCarrito item : carrito) {
            item.accept(facturaVisitor);
        }

        System.out.println("\n--- 1. Detalle de Factura (GeneradorFacturaVisitor) ---");
        for (String linea : facturaVisitor.getLineasDetalle()) {
            System.out.println(linea);
        }

        // OPERACIÓN 2: Cálculo de envíos
        CalculadorEnvioVisitor envioVisitor = new CalculadorEnvioVisitor();
        for (ItemCarrito item : carrito) {
            item.accept(envioVisitor);
        }

        System.out.println("\n--- 2. Resumen Logístico y Financiero ---");
        System.out.printf("Subtotal con impuestos: $%.2f%n", facturaVisitor.getTotalConImpuestos());
        System.out.printf("Costo total de envío:   $%.2f%n", envioVisitor.getTotalEnvio());
        System.out.printf("GRAN TOTAL A PAGAR:     $%.2f%n", 
                (facturaVisitor.getTotalConImpuestos() + envioVisitor.getTotalEnvio()));

        // OPERACIÓN 3: Nueva funcionalidad añadida sin tocar las entidades del modelo
        ExportadorJsonVisitor jsonVisitor = new ExportadorJsonVisitor();
        for (ItemCarrito item : carrito) {
            item.accept(jsonVisitor);
        }

        System.out.println("\n--- 3. Exportación a JSON (ExportadorJsonVisitor) ---");
        System.out.println(jsonVisitor.getJson());

        System.out.println("==================================================");
        System.out.println("VENTAJAS CLAVE OBSERVADAS:");
        System.out.println("✓ Cumple SRP: La lógica de envío, factura y JSON está en clases dedicadas.");
        System.out.println("✓ Cumple OCP: Para agregar ExportadorJsonVisitor NO modificamos ProductoFisico, Digital ni Suscripcion.");
        System.out.println("✓ Cero uso de 'instanceof' o 'castings' manuales gracias al Doble Despacho (Double Dispatch).");
        System.out.println("==================================================");
    }
}
