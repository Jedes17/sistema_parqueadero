
package modelo;

public class Factura {
    private int id;
    private int idReserva;
    private String fechaEmision;
    private double total;
    private String formaPago;
    private String observaciones;
    // Campos adicionales para los filtros
    private String fechaEntrada;
    private String fechaSalida;
    private String horaEntrada;
    private String horaSalida;
    private String placaVehiculo;
    private String tipoVehiculo;
    private double totalPagar;
    private String modeloVehiculo;
    private String identificacionCliente;
    private String nombreCompleto;
    private String correoElectronico;
    private String marca;
    private String color;
    private String estadoEntrada;
    private String estadoSalida;
    private double subtotal;
    
    public Factura() {}

    public Factura(int id, int idReserva, String fechaEmision, double total, String formaPago, String observaciones, String fechaEntrada, String fechaSalida, String horaEntrada, String horaSalida, String placaVehiculo, String tipoVehiculo, double totalPagar, String modeloVehiculo, String identificacionCliente, String nombreCompleto, String correoElectronico, String marca, String color, String estadoEntrada, String estadoSalida, double subtotal) {
        this.id = id;
        this.idReserva = idReserva;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.formaPago = formaPago;
        this.observaciones = observaciones;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.totalPagar = totalPagar;
        this.modeloVehiculo = modeloVehiculo;
        this.identificacionCliente = identificacionCliente;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.marca = marca;
        this.color = color;
        this.estadoEntrada = estadoEntrada;
        this.estadoSalida = estadoSalida;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(String estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public String getEstadoSalida() {
        return estadoSalida;
    }

    public void setEstadoSalida(String estadoSalida) {
        this.estadoSalida = estadoSalida;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
