import java.util.Scanner;

// esta clase es la que habla con el usuario: pregunta el nivel,
// pide numeros en bucle y se los pasa al Lanzador
public class Interfaz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lanzador lanzador = new Lanzador();

        // preguntamos el nivel al arrancar el programa (el nivel 3 no esta implementado)
        System.out.println("¿Qué nivel quieres usar? (1, 2 o 4):");
        String nivelTexto = sc.nextLine();

        int nivel;
        try {
            nivel = Integer.parseInt(nivelTexto.trim());
        } catch (NumberFormatException e) {
            // si escriben cualquier cosa que no sea un numero, usamos el nivel 1 por defecto
            System.out.println("Nivel no valido, se usara el nivel 1 por defecto.");
            nivel = 1;
        }

        // bucle principal, sigue pidiendo numeros hasta que se escriba "salir"
        while (true) {
            System.out.println("Introduce un número (o 'salir' para terminar):");
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

                System.out.println("Operación completada. Código de salida: " + codigo);

            } catch (Exception e) {
                // por si algo raro pasa al lanzar el proceso (por ejemplo que no exista el comando)
                System.out.println("Ha ocurrido un error al ejecutar el comando: " + e.getMessage());
            }
        }

        sc.close();
    }
}