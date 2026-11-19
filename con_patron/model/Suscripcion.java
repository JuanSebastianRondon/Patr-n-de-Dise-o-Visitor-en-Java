package con_patron.model;

import con_patron.visitor.CarritoVisitor;

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
    public double getPrecio() {
        return cuotaMensual * mesesContratados;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public int getMesesContratados() {
        return mesesContratados;
    }

    @Override
    public void accept(CarritoVisitor visitor) {
        visitor.visit(this);
    }
}
