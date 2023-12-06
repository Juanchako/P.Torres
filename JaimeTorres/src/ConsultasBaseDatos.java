import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasBaseDatos {

    ResultSet r;

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/JaimeTorres";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    // Método para obtener obras en Colombia por un artista específico
    public static List<Obra> obtenerObrasEnColombiaPorArtista(int idArtista) {
        List<Obra> obras = new ArrayList<>();
        String query = "SELECT ID, NOMBRE, PAIS FROM Obras WHERE ID_ARTISTA = ? AND PAIS = 'COLOMBIA';";

        try (Connection conn = conectar();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, idArtista);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String pais = resultSet.getString("PAIS");
                obras.add(new Obra(id, nombre, pais));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obras;
    }

    // Método para obtener museos por ciudad que empiezan con 'L'
    public static List<Museo> obtenerMuseosPorCiudadLetraL(String ciudad) {
        List<Museo> museos = new ArrayList<>();
        String query = "SELECT * FROM Museos WHERE CIUDAD = ? AND NOMBRE LIKE 'L%';";

        try (Connection conn = conectar();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, ciudad);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String pais = resultSet.getString("PAIS");
                String direccion = resultSet.getString("DIRECCION");
                museos.add(new Museo(id, nombre, pais, ciudad, direccion));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return museos;
    }

    public static void main(String[] args) {
        // Ejemplo de uso de los métodos
        int idArtistaConsulta = 1;  // Reemplazar con el ID del artista que deseas consultar
        List<Obra> obrasEnColombia = obtenerObrasEnColombiaPorArtista(idArtistaConsulta);
        System.out.println("Obras en Colombia por el artista:");
        for (Obra obra : obrasEnColombia) {
            System.out.println(obra);
        }

        String ciudadConsulta = "Bogotá";  // Reemplazar con la ciudad que deseas consultar
        List<Museo> museosLetraL = obtenerMuseosPorCiudadLetraL(ciudadConsulta);
        System.out.println("\nMuseos en la ciudad que empiezan con 'L':");
        for (Museo museo : museosLetraL) {
            System.out.println(museo);
        }
    }
}

class Obra {
    private int id;
    private String nombre;
    private String pais;

    public Obra(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Obra [ID=" + id + ", Nombre=" + nombre + ", País=" + pais + "]";
    }
}

class Museo {
    private int id;
    private String nombre;
    private String pais;
    private String ciudad;
    private String direccion;

    public Museo(int id, String nombre, String pais, String ciudad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Museo [ID=" + id + ", Nombre=" + nombre + ", País=" + pais +
                ", Ciudad=" + ciudad + ", Dirección=" + direccion + "]";
    }
}
