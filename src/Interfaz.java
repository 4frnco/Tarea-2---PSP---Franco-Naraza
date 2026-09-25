import java.util.Scanner;

// esta clase es la que habla con el usuario: pregunta el nivel,
// pide números en bucle y se los pasa al Lanzador
public class Interfaz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lanzador lanzador = new Lanzador();

        // preguntamos el nivel al arrancar el programa (el nivel 3 no esta implementado)
        System.out.println("¿Qué nivel quieres usar franco? (1, 2 o 4):");
        String nivelTexto = sc.nextLine();

        int nivel;

        try {
            nivel = Integer.parseInt(nivelTexto.trim());

            if (nivel != 1 && nivel != 2 && nivel != 4) {
                System.out.println("Nivel no valido.");
                sc.close();
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Nivel no valido.");
            sc.close();
            return;
        }

        // bucle principal, sigue pidiendo numeros hasta que se escriba "salir"
        while (true) {
            System.out.println("franco, introduce un número (o 'salir' para terminar):");
            System.out.print("> ");
            String entrada = sc.nextLine();

            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa");
                break;
            }

            try {
                int codigo;

                // segun el nivel elegido llamamos a un metodo u otro del lanzador
                switch (nivel) {
                    case 2:
                        codigo = lanzador.ejecutarNivel2(entrada);
                        break;
                    case 4:
                        codigo = lanzador.ejecutarNivel4(entrada);
                        break;
                    default:
                        codigo = lanzador.ejecutarNivel1(entrada);
                        break;
                }

                System.out.println("Operación completada franco. Código de salida: " + codigo);

            } catch (Exception e) {
                // por si algo raro pasa al lanzar el proceso (por ejemplo que no exista el comando)
                System.out.println("franco ha ocurrido un error al ejecutar el comando: " + e.getMessage());
            }
        }

        sc.close();
    }
}