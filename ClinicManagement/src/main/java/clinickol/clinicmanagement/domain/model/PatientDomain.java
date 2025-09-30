package clinickol.clinicmanagement.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Modelo de dominio para Paciente (sin dependencias de frameworks)
 * Este es el núcleo del negocio
 */
public class PatientDomain {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String documento;
    private String tipoDocumento;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private String contactoEmergencia;
    private String telefonoEmergencia;
    private String alergias;
    private String medicamentosActuales;
    private String historialMedico;
    private String grupoSanguineo;
    private Double peso;
    private Double altura;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    // Constructor vacío
    public PatientDomain() {
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }
    
    // Constructor completo
    public PatientDomain(String nombre, String apellido, String email, String telefono,
                        String documento, String tipoDocumento, LocalDate fechaNacimiento,
                        String genero) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    // Lógica de negocio: calcular edad
    public int calcularEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
    
    // Lógica de negocio: calcular IMC
    public Double calcularIMC() {
        if (peso == null || altura == null || altura == 0) {
            return null;
        }
        return peso / (altura * altura);
    }
    
    // Lógica de negocio: obtener categoría de IMC
    public String getCategoriaIMC() {
        Double imc = calcularIMC();
        if (imc == null) {
            return "No calculable";
        }
        
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
    
    // Lógica de negocio: validar datos
    public boolean esValido() {
        return nombre != null && !nombre.isEmpty() &&
               apellido != null && !apellido.isEmpty() &&
               email != null && !email.isEmpty() &&
               documento != null && !documento.isEmpty() &&
               fechaNacimiento != null;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    public String getContactoEmergencia() {
        return contactoEmergencia;
    }
    
    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }
    
    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }
    
    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }
    
    public String getAlergias() {
        return alergias;
    }
    
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
    
    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }
    
    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }
    
    public String getHistorialMedico() {
        return historialMedico;
    }
    
    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }
    
    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }
    
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    
    public Double getPeso() {
        return peso;
    }
    
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    public Double getAltura() {
        return altura;
    }
    
    public void setAltura(Double altura) {
        this.altura = altura;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
