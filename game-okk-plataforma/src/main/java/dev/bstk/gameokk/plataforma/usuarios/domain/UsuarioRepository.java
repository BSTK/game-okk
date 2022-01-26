package dev.bstk.gameokk.plataforma.usuarios.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.apelido = :apelido")
    Usuario usuarioPorApelido(@Param("apelido") final String apelido);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.email = :email")
    boolean existeUsuarioComEmailJaCadastrado(@Param("email") final String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.apelido = :apelido")
    boolean existeUsuarioComApelidoJaCadastrado(@Param("apelido") final String apelido);

}
