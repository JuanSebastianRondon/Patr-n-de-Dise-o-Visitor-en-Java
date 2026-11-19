package con_patron.visitor;

import con_patron.model.ProductoFisico;
import con_patron.model.ProductoDigital;
import con_patron.model.Suscripcion;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteVisitor de ejemplo que demuestra el Principio Abierto/Cerrado (OCP).
 * Se agrega una funcionalidad completamente nueva (Exportar a JSON)
 * SIN TOCAR NINGUNA CLASE de ProductoFisico, ProductoDigital o Suscripcion.
 */
public class ExportadorJsonVisitor implements CarritoVisitor {
    private final List<String> elementosJson = new ArrayList<>();

    @Override
    public void visit(ProductoFisico producto) {
        elementosJson.add(String.format(
            "    {\n" +
            "      \"tipo\": \"fisico\",\n" +
            "      \"nombre\": \"%s\",\n" +
            "      \"precio\": %.2f,\n" +
            "      \"pesoKg\": %.2f,\n" +
            "      \"esFragil\": %b\n" +
            "    }",
            producto.getNombre(), producto.getPrecio(), producto.getPesoKg(), producto.isEsFragil()
        ));
    }

    @Override
    public void visit(ProductoDigital producto) {
        elementosJson.add(String.format(
            "    {\n" +
            "      \"tipo\": \"digital\",\n" +
            "      \"nombre\": \"%s\",\n" +
            "      \"precio\": %.2f,\n" +
            "      \"tamanoMb\": %d\n" +
            "    }",
            producto.getNombre(), producto.getPrecio(), producto.getTamanoMb()
        ));
    }

    @Override
    public void visit(Suscripcion suscripcion) {
        elementosJson.add(String.format(
            "    {\n" +
            "      \"tipo\": \"suscripcion\",\n" +
            "      \"nombre\": \"%s\",\n" +
            "      \"cuotaMensual\": %.2f,\n" +
            "      \"mesesContratados\": %d,\n" +
            "      \"precioTotal\": %.2f\n" +
            "    }",
            suscripcion.getNombre(), suscripcion.getCuotaMensual(), 
            suscripcion.getMesesContratados(), suscripcion.getPrecio()
        ));
    }

    public String getJson() {
        return "{\n  \"carrito\": [\n" + String.join(",\n", elementosJson) + "\n  ]\n}";
    }
}
