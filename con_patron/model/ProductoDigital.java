package con_patron.model;

import con_patron.visitor.CarritoVisitor;

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
    public double getPrecio() {
        return precio;
    }

    public int getTamanoMb() {
        return tamanoMb;
    }

    @Override
    public void accept(CarritoVisitor visitor) {
        visitor.visit(this);
    }
}
