package SIAND;

public class AuthService {

    private final UsuarioRepositorio repo = new UsuarioRepositorio();

    public void registrar(String username, String email, String password) {
        username = (username == null) ? "" : username.trim();
        email = (email == null) ? "" : email.trim().toLowerCase();
        password = (password == null) ? "" : password;

        if (username.isEmpty())
            throw new IllegalArgumentException("El usuario es obligatorio");
        if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$"))
            throw new IllegalArgumentException("Correo inválido");
        if (password.length() < 6)
            throw new IllegalArgumentException("La contraseña debe tener mínimo 6 caracteres");

        // El ";" es nuestro separador, si lo permitimos se rompe el archivo
        if (username.contains(";") || email.contains(";") || password.contains(";"))
            throw new IllegalArgumentException("No se permite el carácter ;");

        if (repo.buscarPorUsername(username) != null)
            throw new IllegalStateException("Ese usuario ya existe");
        if (repo.existeEmail(email))
            throw new IllegalStateException("Ese correo ya está registrado");

        repo.guardar(new Usuario(username, email, password));
    }

    // Devuelve el Usuario si las credenciales son correctas, o null si no
    public Usuario login(String username, String password) {
        if (username == null || password == null) return null;
        Usuario u = repo.buscarPorUsername(username.trim());
        if (u == null || !u.getPassword().equals(password)) return null;
        return u;
    }
}