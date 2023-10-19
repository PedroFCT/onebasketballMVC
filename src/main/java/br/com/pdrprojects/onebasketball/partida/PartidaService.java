package br.com.pdrprojects.onebasketball.partida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    @Autowired
    PartidaRepository repository;

    public List<Partida> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var partida = repository.findById(id);
        if (partida.isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Partida partida) {
        repository.save(partida);
    }

}
