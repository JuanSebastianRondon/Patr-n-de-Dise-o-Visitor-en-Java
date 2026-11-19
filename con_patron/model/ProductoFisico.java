package con_patron.model;

import con_patron.visitor.CarritoVisitor;

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
    public double getPrecio() {
        return precio;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public boolean isEsFragil() {
        return esFragil;
    }

    /**
     * Doble Despacho: El elemento le pasa 'this' al visitante.
     * En tiempo de compilación se sabe que 'this' es de tipo ProductoFisico,
     * por lo que se invoca exactamente visitor.visit(ProductoFisico).
     */
    @Override
    public void accept(CarritoVisitor visitor) {
        visitor.visit(this);
    }
}
