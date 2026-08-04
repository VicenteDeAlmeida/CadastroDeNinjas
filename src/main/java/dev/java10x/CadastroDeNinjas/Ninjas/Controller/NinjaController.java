package dev.java10x.CadastroDeNinjas.Ninjas.Controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota.";
    }

    //Adicionar Ninja(Create)
    @PostMapping("/criar")
    public NinjaModel criarNovoNinja(@RequestBody NinjaModel ninjaModel){
        return ninjaService.criarNovoNinja(ninjaModel);
    }

    //Procurar Ninja por Id(Read)
    @GetMapping("listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id){
        return ninjaService.listarninjasPorId(id);
    }

    //Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    //Alterar dados dos ninja(Update)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId(){
        return "Mostrar ninja por ID";
    }

    //Deletar Ninja(Delete)
    @DeleteMapping("/deletarId")
    public String deletarNinjaPorID(){
        return "Ninja deletado por ID";
    }
}
