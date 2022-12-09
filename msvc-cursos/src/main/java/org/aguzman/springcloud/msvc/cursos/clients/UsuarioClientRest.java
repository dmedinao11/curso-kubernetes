package org.aguzman.springcloud.msvc.cursos.clients;

import org.aguzman.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios", url = "${msvc.usuarios.url}")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    Usuario obtenerPorId(@PathVariable Long id);
}
