package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
    @Autowired
    private NinjaRepository ninjaRepository;
    @Autowired
    private NinjaMapper ninjaMapper;

    //Listar todos os ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream().map(ninjaMapper::map).collect(Collectors.toList());
    }

    //Listar todos os ninjas por ID
    public NinjaDTO listarninjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    //Criar um novo ninja
    public NinjaDTO criarNovoNinja(NinjaDTO ninjaDto){
        NinjaModel ninja = ninjaMapper.map(ninjaDto);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }
    //Deletar ninja por id (delete tem que ser void)
    public void deletarNinjaporId(Long id){
        ninjaRepository.deleteById(id);
    }

    //Atualizar ninja por id
    public NinjaDTO atualizarNinja(Long id,NinjaDTO ninjaDTO){
       Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);

       if (ninjaExistente.isPresent()){
           NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
           ninjaAtualizado.setId(id);
           NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
           return ninjaMapper.map(ninjaSalvo);
       }
       return null;

    }


}

