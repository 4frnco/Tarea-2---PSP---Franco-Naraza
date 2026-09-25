import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lanzador {

    // lanza factor y devuelve el codigo de salida
    public int ejecutarNivel1(String numero) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("factor", numero);
        pb.redirectErrorStream(true);

        Process proceso = pb.start();

        BufferedReader lector = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()));

        String linea;

        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
        }

        return proceso.waitFor();
    }

    // lanza factor y pone ok o error delante de la salida
    public int ejecutarNivel2(String numero) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("factor", numero);

        Process proceso = pb.start();

        BufferedReader salida = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()));

        BufferedReader error = new BufferedReader(
                new InputStreamReader(proceso.getErrorStream()));

        String linea;

        while ((linea = salida.readLine()) != null) {
            System.out.println("[OK] " + linea);
        }

        while ((linea = error.readLine()) != null) {
            System.out.println("[ERROR] " + linea);
        }

        return proceso.waitFor();
    }

    // lanza factor y comprueba si el numero es primo
    public int ejecutarNivel4(String numero) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("factor", numero);
        pb.redirectErrorStream(true);

        Process proceso = pb.start();

        BufferedReader lector = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()));

        String linea = "";
        String ultimaLinea = "";

        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
            ultimaLinea = linea;
        }

        int codigo = proceso.waitFor();

        if (codigo == 0) {
            String[] partes = ultimaLinea.split(":");

            if (partes.length == 2) {
                String numeroOriginal = partes[0].trim();
                String factores = partes[1].trim();

                if (factores.equals(numeroOriginal)) {
                    System.out.println("¡" + numeroOriginal + " es primo!");
                } else {
                    System.out.println(numeroOriginal + " no es primo");
                }
            }
        }

        return codigo;
    }
}