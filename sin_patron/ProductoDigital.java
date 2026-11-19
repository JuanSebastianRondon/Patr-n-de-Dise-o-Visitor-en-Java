package sin_patron;

public class ProductoDigital implements ItemCarrito {
    private String nombre;
    private double precio;
    private int tamanoMb;

    public ProductoDigital(String nombre, double precio, int tamanoMb) {
        this.nombre = nombre;
        this.precio = precio;
        this.tamanoMb = tamanoMb;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecioBase() {
        return precio;
    }

    public int getTamanoMb() {
        return tamanoMb;
    }

    // Los productos digitales no tienen costo de envío
    @Override
    public double calcularEnvio() {
        return 0.0;
    }

    // Lógica fiscal: 10% impuesto digital
    @Override
    public double calcularTotalConImpuesto() {
        return precio * 1.10;
    }

    @Override
    public String obtenerDetalleFactura() {
        return String.format("[Digital] %s (%d MB) - Precio: $%.2f + Impuesto Digital (10%%) = $%.2f | Envío: $0.00",
                nombre, tamanoMb, precio, calcularTotalConImpuesto());
    }
}
