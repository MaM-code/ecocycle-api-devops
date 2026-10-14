
package com.ecocycle.ecocycle_api.ecocycle_api.repository;

import com.ecocycle.ecocycle_api.ecocycle_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}