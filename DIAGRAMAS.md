# Diagramas de Clases: Sin Patrón vs Con Patrón Visitor

Este documento contiene los diagramas de clases UML (en formato Mermaid) para contrastar visualmente la arquitectura de ambos enfoques.

---

## 1. Diagrama de Clases: Versión SIN Patrón (`sin_patron`)

En este diseño, la interfaz `ItemCarrito` y cada clase concreta asumen la responsabilidad de calcular costos de envío, impuestos y formatear texto de facturación.

```mermaid
classDiagram
    direction TB

    class ItemCarrito {
        <<interface>>
        +getNombre() String
        +getPrecioBase() double
        +calcularEnvio() double
        +calcularTotalConImpuesto() double
        +obtenerDetalleFactura() String
    }

    class ProductoFisico {
        -String nombre
        -double precio
        -double pesoKg
        -boolean esFragil
        +getNombre() String
        +getPrecioBase() double
        +getPesoKg() double
        +isEsFragil() boolean
        +calcularEnvio() double
        +calcularTotalConImpuesto() double
        +obtenerDetalleFactura() String
    }

    class ProductoDigital {
        -String nombre
        -double precio
        -int tamanoMb
        +getNombre() String
        +getPrecioBase() double
        +getTamanoMb() int
        +calcularEnvio() double
        +calcularTotalConImpuesto() double
        +obtenerDetalleFactura() String
    }

    class Suscripcion {
        -String nombre
        -double cuotaMensual
        -int mesesContratados
        +getNombre() String
        +getPrecioBase() double
        +getCuotaMensual() double
        +getMesesContratados() int
        +calcularEnvio() double
        +calcularTotalConImpuesto() double
        +obtenerDetalleFactura() String
    }

    class Main {
        +main(String[] args)$ void
    }

    ItemCarrito <|.. ProductoFisico : implementa
    ItemCarrito <|.. ProductoDigital : implementa
    ItemCarrito <|.. Suscripcion : implementa
    Main ..> ItemCarrito : gestiona lista
```

> **Problema visual:** Si se desea agregar una nueva operación (por ejemplo: `exportarAJson()`), hay que modificar **4 clases**: la interfaz y las 3 clases hijas.

---

## 2. Diagrama de Clases: Versión CON Patrón Visitor (`con_patron`)

Aquí la estructura de datos (`model`) se desacopla totalmente de los algoritmos y operaciones (`visitor`). La comunicación se realiza mediante el método `accept(CarritoVisitor)` y el despacho doble (*Double Dispatch*).

```mermaid
classDiagram
    direction TB

    %% Jerarquía de Elementos (Model)
    class ItemCarrito {
        <<interface>>
        +getNombre() String
        +getPrecio() double
        +accept(CarritoVisitor visitor) void
    }

    class ProductoFisico {
        -String nombre
        -double precio
        -double pesoKg
        -boolean esFragil
        +getNombre() String
        +getPrecio() double
        +getPesoKg() double
        +isEsFragil() boolean
        +accept(CarritoVisitor visitor) void
    }

    class ProductoDigital {
        -String nombre
        -double precio
        -int tamanoMb
        +getNombre() String
        +getPrecio() double
        +getTamanoMb() int
        +accept(CarritoVisitor visitor) void
    }

    class Suscripcion {
        -String nombre
        -double cuotaMensual
        -int mesesContratados
        +getNombre() String
        +getPrecio() double
        +getCuotaMensual() double
        +getMesesContratados() int
        +accept(CarritoVisitor visitor) void
    }

    %% Jerarquía de Visitantes (Visitor)
    class CarritoVisitor {
        <<interface>>
        +visit(ProductoFisico producto) void
        +visit(ProductoDigital producto) void
        +visit(Suscripcion suscripcion) void
    }

    class CalculadorEnvioVisitor {
        -double totalEnvio
        +visit(ProductoFisico producto) void
        +visit(ProductoDigital producto) void
        +visit(Suscripcion suscripcion) void
        +getTotalEnvio() double
    }

    class GeneradorFacturaVisitor {
        -double totalConImpuestos
        -List~String~ lineasDetalle
        +visit(ProductoFisico producto) void
        +visit(ProductoDigital producto) void
        +visit(Suscripcion suscripcion) void
        +getTotalConImpuestos() double
        +getLineasDetalle() List~String~
    }

    class ExportadorJsonVisitor {
        -List~String~ elementosJson
        +visit(ProductoFisico producto) void
        +visit(ProductoDigital producto) void
        +visit(Suscripcion suscripcion) void
        +getJson() String
    }

    class Main {
        +main(String[] args)$ void
    }

    %% Relaciones de Implementación Elementos
    ItemCarrito <|.. ProductoFisico : implementa
    ItemCarrito <|.. ProductoDigital : implementa
    ItemCarrito <|.. Suscripcion : implementa

    %% Relaciones de Implementación Visitors
    CarritoVisitor <|.. CalculadorEnvioVisitor : implementa
    CarritoVisitor <|.. GeneradorFacturaVisitor : implementa
    CarritoVisitor <|.. ExportadorJsonVisitor : implementa

    %% Relaciones de Uso (Doble Despacho)
    ItemCarrito ..> CarritoVisitor : accept(visitor)
    CarritoVisitor ..> ProductoFisico : visit(this)
    CarritoVisitor ..> ProductoDigital : visit(this)
    CarritoVisitor ..> Suscripcion : visit(this)

    Main ..> ItemCarrito : recorre
    Main ..> CarritoVisitor : instancia visitantes
```

> **Ventaja visual:** Si se desea agregar una nueva operación (por ejemplo: `CalcularPuntosFidelidadVisitor`), **ninguna clase del modelo se toca**. Únicamente se crea una nueva clase que implementa `CarritoVisitor`.
