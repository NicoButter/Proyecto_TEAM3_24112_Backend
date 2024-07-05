package model;

public class Producto {
    private int id;
    private String nombre;
    private String tipo;
    private byte[] imagen;
    private double precio;

    public Producto(int id, String nombre, String tipo, byte[] imagen, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.precio = precio;
    }

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

    
}
