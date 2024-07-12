package model;

import java.math.BigDecimal;

public class Oferta {
    private int id;
    private String nombre;
    private String descripcion;
    private String tipoDescuento;
    private BigDecimal valorDescuento;

    // Constructor vacío
    public Oferta() {}

    public Oferta(int id, String nombre, String descripcion, String tipoDescuento, BigDecimal valorDescuento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoDescuento = tipoDescuento;
		this.valorDescuento = valorDescuento;
	}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }
    
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
    public String toString() {
        return "Oferta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Descripcion=" + descripcion +
                ", tipoDescuento='" + tipoDescuento + '\'' +
                ", valorDescuento=" + valorDescuento +
                '}';
    }
}

