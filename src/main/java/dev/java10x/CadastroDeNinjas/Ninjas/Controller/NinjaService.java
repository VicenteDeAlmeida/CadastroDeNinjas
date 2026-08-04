package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    @Autowired
    private NinjaRepository ninjaRepository;

    //Listar todos os ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    //Listar todos os ninjas por ID
    public NinjaModel listarninjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    //Criar um novo ninja
    public NinjaModel criarNovoNinja(NinjaModel ninjaModel){
        return ninjaRepository.save(ninjaModel);
    }
    //Deletar ninja por id (delete tem que ser void)
    public void deletarNinjaporId(Long id){
        ninjaRepository.deleteById(id);
    }


}

