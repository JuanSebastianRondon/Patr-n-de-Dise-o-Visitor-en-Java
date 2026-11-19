package sin_patron;

public class Suscripcion implements ItemCarrito {
    private String nombre;
    private double cuotaMensual;
    private int mesesContratados;

    public Suscripcion(String nombre, double cuotaMensual, int mesesContratados) {
        this.nombre = nombre;
        this.cuotaMensual = cuotaMensual;
        this.mesesContratados = mesesContratados;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecioBase() {
        return cuotaMensual * mesesContratados;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public int getMesesContratados() {
        return mesesContratados;
    }

    // Una suscripción no tiene costo de envío
    @Override
    public double calcularEnvio() {
        return 0.0;
    }

    // Exento de impuestos
    @Override
    public double calcularTotalConImpuesto() {
        return getPrecioBase();
    }

    @Override
    public String obtenerDetalleFactura() {
        return String.format("[Suscripción] %s (%d meses a $%.2f/mes) - Total: $%.2f (Exento de impuestos) | Envío: $0.00",
                nombre, mesesContratados, cuotaMensual, getPrecioBase());
    }
}
