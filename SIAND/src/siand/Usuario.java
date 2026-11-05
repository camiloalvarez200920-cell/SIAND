package SIAND;

public class Usuario {
    private final String username;
    private final String email;
    private final String password;
    
    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public String getUSername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    // usuario -> linea del txt
    public String toLinea() {
        return username + ";" + email + ";" + password;
    }
    
    // linea del txt -> usuario ( null si la linea esta mal formada)
    public static Usuario desdeLinea(String linea) {
        String[] p = linea.split(";", 3);
        if (p.length != 3) return null;
        return new Usuario(p[0], p[1], p[2]);
    }
}