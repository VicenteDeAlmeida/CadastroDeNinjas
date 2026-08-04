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
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaPorId(@PathVariable Long id,@RequestBody NinjaModel ninjaAtualizadp){
        return ninjaService.atualizarNinja(id,ninjaAtualizadp);
    }

    //Deletar Ninja(Delete)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorID(@PathVariable Long id){
        ninjaService.deletarNinjaporId(id);
    }


}
