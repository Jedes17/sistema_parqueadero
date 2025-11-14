
package modelo;

import java.util.Date;

public class EstadoVehicular {
    private int id;
    private int idReserva;
    private String observacionesEntrada;
    private String observacionesSalida;
    private String estadoGeneral;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String fotoEntrada;
    private String fotoSalida;
    private int registradoPor;
    private String placaVehiculo;
    private String tipoVehiculo;
    private String lugarReservado;
    private String horaEntrada;
    private String horaSalida;
    private String cedulaCliente;
    private String cedulaOperador;
    private String nuevoEstado;
    public EstadoVehicular(){}

    public EstadoVehicular(int id, int idReserva, String observacionesEntrada, String observacionesSalida, String estadoGeneral, Date fechaIngreso, Date fechaSalida, String fotoEntrada, String fotoSalida, int registradoPor, String placaVehiculo, String tipoVehiculo, String lugarReservado, String horaEntrada, String horaSalida, String cedulaCliente, String cedulaOperador,String nuevoEstado) {
        this.id = id;
        this.idReserva = idReserva;
        this.observacionesEntrada = observacionesEntrada;
        this.observacionesSalida = observacionesSalida;
        this.estadoGeneral = estadoGeneral;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.fotoEntrada = fotoEntrada;
        this.fotoSalida = fotoSalida;
        this.registradoPor = registradoPor;
        this.placaVehiculo = placaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.lugarReservado = lugarReservado;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cedulaCliente = cedulaCliente;
        this.cedulaOperador = cedulaOperador;
        this.nuevoEstado = nuevoEstado;
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

    public String getObservacionesEntrada() {
        return observacionesEntrada;
    }

    public void setObservacionesEntrada(String observacionesEntrada) {
        this.observacionesEntrada = observacionesEntrada;
    }

    public String getObservacionesSalida() {
        return observacionesSalida;
    }

    public void setObservacionesSalida(String observacionesSalida) {
        this.observacionesSalida = observacionesSalida;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFotoEntrada() {
        return fotoEntrada;
    }

    public void setFotoEntrada(String fotoEntrada) {
        this.fotoEntrada = fotoEntrada;
    }

    public String getFotoSalida() {
        return fotoSalida;
    }

    public void setFotoSalida(String fotoSalida) {
        this.fotoSalida = fotoSalida;
    }

    public int getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(int registradoPor) {
        this.registradoPor = registradoPor;
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

    public String getLugarReservado() {
        return lugarReservado;
    }

    public void setLugarReservado(String lugarReservado) {
        this.lugarReservado = lugarReservado;
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

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getCedulaOperador() {
        return cedulaOperador;
    }

    public void setCedulaOperador(String cedulaOperador) {
        this.cedulaOperador = cedulaOperador;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }
}