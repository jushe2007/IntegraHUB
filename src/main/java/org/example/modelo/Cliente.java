package org.example.modelo;

public class Cliente {
    
    // asignacion de atruibuto
    private int id_Cliente = 0;
    private String direccion = "";
    private String tel1 = "";
    private String tel2 = "";
    private int id_Almacen3 = 0;

    // constructores (vacio / con atributos )
    public Cliente() {
    }

    public Cliente(int id_Cliente, String direccion, String tel1, String tel2, int id_Almacen3) {
        setId_Cliente(id_Cliente);
        setDireccion(direccion);
        setTel1(tel1);
        setTel2(tel2);
        setId_Almacen3(id_Almacen3);
    }

    // geters (formatos) y setters (condiciones para guardar)

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        if (id_Cliente > 0) {
            this.id_Cliente = id_Cliente;
        } else {
            System.out.println("El id del cliente es un requisito y debe ser mayor a 0");
        }
    }

    public String getDireccion() {
        String direccionFormato = "";
        if (this.direccion != null) {
            direccionFormato = this.direccion.toLowerCase();
        }
        return direccionFormato;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            System.out.println("La direccion es un requisito");
        } else {
            this.direccion = direccion;
        }
    }

    public String getTel1() {
        String tel1Formato = "";
        if (this.tel1 != null) {
            tel1Formato = this.tel1.toLowerCase();
        }
        return tel1Formato;
    }

    public void setTel1(String tel1) {
        if (tel1 == null || tel1.isBlank()) {
            System.out.println("El telefono es un requisito");
        } else if (tel1.matches("^[0-9+\\-() ]{1,15}$")) {
            this.tel1 = tel1;
        } else {
            System.out.println("El telefono 1 no tiene un formato valido de numeros o caracteres");
        }
    }

    public String getTel2() {
        String tel2Formato = "";
        if (this.tel2 != null) {
            tel2Formato = this.tel2.toLowerCase();
        }
        return tel2Formato;
    }

    public void setTel2(String tel2) {
        if (tel2 == null || tel2.isBlank()) {
            this.tel2 = tel2;
        } else if (tel2.matches("^[0-9+\\-() ]{1,15}$")) {
            this.tel2 = tel2;
        } else {
            System.out.println("El telefono 2 no tiene un formato valido de numeros o caracteres");
        }
    }

    public int getId_Almacen3() {
        return id_Almacen3;
    }

    public void setId_Almacen3(int id_Almacen3) {
        this.id_Almacen3 = id_Almacen3;
    }
}