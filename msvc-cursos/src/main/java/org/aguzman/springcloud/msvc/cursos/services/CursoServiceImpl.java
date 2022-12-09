package org.aguzman.springcloud.msvc.cursos.services;

import org.aguzman.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.aguzman.springcloud.msvc.cursos.models.Usuario;
import org.aguzman.springcloud.msvc.cursos.models.entity.Curso;
import org.aguzman.springcloud.msvc.cursos.models.entity.CursosUsuarios;
import org.aguzman.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioClientRest usuarioClient;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long asignarUsuario(Long usuarioId, Long cursoId) throws Exception {
        final Usuario usuario = usuarioClient.obtenerPorId(usuarioId);
        final Curso curso = porId(cursoId).orElseThrow(() -> new Exception("Curso no encontrado"));

        final CursosUsuarios cursoUsuario = new CursosUsuarios();
        cursoUsuario.setUsuarioId(usuario.getId());

        curso.getCursosUsuarios().add(cursoUsuario);

        guardar(curso);

        return usuarioId;
    }
}
