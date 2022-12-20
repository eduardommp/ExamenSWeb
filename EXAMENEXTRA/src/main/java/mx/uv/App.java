package mx.uv;

import static spark.Spark.*;
import com.google.gson.*;



public class App 
{
    public static Gson gson = new Gson();
    public static void main( String[] args )
    {
        

        port(80);
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            System.out.println(accessControlRequestHeaders);
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            System.out.println(accessControlRequestMethod); 
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        
        


        post("/agregarEstudiantes", (req, res) -> {
            System.out.print("Ingresé al post en App.java");
            String datosEstudiantes = req.body();
            Estudiantes u = gson.fromJson(datosEstudiantes, Estudiantes.class);  
            return DAO.agregarEstudiantes(u);
        });

    }
}
