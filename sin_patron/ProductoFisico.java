package sin_patron;

public class ProductoFisico implements ItemCarrito {
    private String nombre;
    private double precio;
    private double pesoKg;
    private boolean esFragil;

    public ProductoFisico(String nombre, double precio, double pesoKg, boolean esFragil) {
        this.nombre = nombre;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.esFragil = esFragil;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecioBase() {
        return precio;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public boolean isEsFragil() {
        return esFragil;
    }

    // PROBLEMA: Lógica logística dentro de la entidad de producto
    @Override
    public double calcularEnvio() {
        double costo = pesoKg * 5.0; // $5 por kg
        if (esFragil) {
            costo += 10.0; // $10 adicional por fragilidad
        }
        return costo;
    }

    // PROBLEMA: Lógica fiscal/tributaria dentro de la entidad de producto
    @Override
    public double calcularTotalConImpuesto() {
        return precio * 1.19; // 19% IVA
    }

    @Override
    public String obtenerDetalleFactura() {
        return String.format("[Físico] %s - Precio: $%.2f + IVA (19%%) = $%.2f | Envío: $%.2f",
                nombre, precio, calcularTotalConImpuesto(), calcularEnvio());
    }
}
