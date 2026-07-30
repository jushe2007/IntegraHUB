package org.example.modelo;

public class Empleado extends Persona {

    // asignacion de atributos
    private int id_Empleado = 0;
    private String puesto = "";
    private String area = "";
    private String rfc = "";
    private String curp = "";
    private int id_Almacen1 = 0;

    // constructores (vacio / con atributos )
    public Empleado() {
    }

    public Empleado(String nombre, String direccion, String tel1, String tel2, int id_Empleado, String puesto, String area, String rfc, String curp, int id_Almacen1) {
        super(nombre, direccion, tel1, tel2);
        setId_Empleado(id_Empleado);
        setPuesto(puesto);
        setArea(area);
        setRfc(rfc);
        setCurp(curp);
        setId_Almacen1(id_Almacen1);
    }

    // geters (formatos) y setters (condiciones para guardar)

    public int getId_Empleado() {
        return id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        if (id_Empleado > 0) {
            this.id_Empleado = id_Empleado;
        } else {
            System.out.println("El id no debe ser menor a 0");
        }
    }

    public String getPuesto() {
        String puestoFormato = "";
        if (this.puesto != null) {
            puestoFormato = this.puesto.toLowerCase();
        }
        return puestoFormato;
    }

    public void setPuesto(String puesto) {
        if (puesto == null || puesto.isBlank()) {
            System.out.println("El puesto del empleado es un requisito");
        } else {
            this.puesto = puesto;
        }
    }

    public String getArea() {
        String areaFormato = "";
        if (this.area != null) {
            areaFormato = this.area.toLowerCase();
        }
        return areaFormato;
    }

    public void setArea(String area) {
        if (area == null || area.isBlank()) {
            System.out.println("El área del empleado es un requisito");
        } else {
            this.area = area;
        }
    }

    public String getRfc() {
        String rfcFormato = "";
        if (this.rfc != null) {
            rfcFormato = this.rfc.toLowerCase();
        }
        return rfcFormato;
    }

    public void setRfc(String rfc) {
        if (rfc == null || rfc.isBlank()) {
            System.out.println("El RFC del empleado es un requisito");
        } else {
            this.rfc = rfc;
        }
    }

    public String getCurp() {
        String curpFormato = "";
        if (this.curp != null) {
            curpFormato = this.curp.toLowerCase();
        }
        return curpFormato;
    }

    public void setCurp(String curp) {
        if (curp == null || curp.isBlank()) {
            System.out.println("La CURP del empleado es un requisito");
        } else {
            this.curp = curp;
        }
    }

    public int getId_Almacen1() {
        return id_Almacen1;
    }

    public void setId_Almacen1(int id_Almacen1) {
        if (id_Almacen1 > 0) {
            this.id_Almacen1 = id_Almacen1;
        } else {
            System.out.println("El id no debe ser menor a 0");
        }
    }
}