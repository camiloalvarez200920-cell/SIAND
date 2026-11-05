package SIAND;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioRepositorio {

    private final Path archivo = Paths.get("usuarios.txt");

    public UsuarioRepositorio() {
        try {
            if (!Files.exists(archivo)) {
                Files.createFile(archivo);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear usuarios.txt", e);
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            for (String linea : Files.readAllLines(archivo)) {
                Usuario u = Usuario.desdeLinea(linea);
                if (u != null) usuarios.add(u);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer usuarios.txt", e);
        }
        return usuarios;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario u : listar()) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    public boolean existeEmail(String email) {
        for (Usuario u : listar()) {
            if (u.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public void guardar(Usuario usuario) {
        try {
            Files.write(archivo, Arrays.asList(usuario.toLinea()),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el usuario", e);
        }
    }
}