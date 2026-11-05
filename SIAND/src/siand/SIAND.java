package SIAND;

import java.util.Scanner;

public class SIAND {

    public static void main(String[] args) {
        AuthService auth = new AuthService();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n1. Registrarse  2. Iniciar sesión  3. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Usuario: ");
                    String user = sc.nextLine();
                    System.out.print("Correo: ");
                    String email = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();
                    try {
                        auth.registrar(user, email, pass);
                        System.out.println("Registro exitoso");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Usuario: ");
                    String u = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String p = sc.nextLine();
                    Usuario logueado = auth.login(u, p);
                    System.out.println(logueado != null
                            ? "Bienvenido, " + logueado.getUsername()
                            : "Usuario o contraseña incorrectos");
                    break;

                case "3":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
        sc.close();
    }
}