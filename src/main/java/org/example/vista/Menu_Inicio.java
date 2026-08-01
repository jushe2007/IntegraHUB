package org.example.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu_Inicio {
    private static final BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() throws IOException {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n--- MENÚ DE INICIO ---");
            System.out.println("1. INICIO DE SESION");
            System.out.println("2. ENTRAR (MODO CONSULTA)");
            System.out.println("3. MARCAR ENTRAR/SALIDA");
            System.out.println("4. SALIR");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(leer.readLine());
                switch (opcion) {
                    case 1: opcion =4; Menu_Sesion.menu() ; break;
                    //case 2: opcion =4; MenuProfesor.menu(); break;
                   // case 3: mostrarComunidadUniversitaria(); break;
                    case 4: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opción no válida"); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                opcion = 0;
            }
        }
    }
}
