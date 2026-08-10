package dev.java10x.CadastroDeNinjas.Ninjas.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarNovoNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNovoNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome()+ " ID: " + novoNinja.getId());
    }

    //Procurar Ninja por Id(Read)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){

        NinjaDTO ninja =  ninjaService.listarninjasPorId(id);
        if (ninja !=null){
            return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe nos nossos registros.");
        }
    }

    //Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
    public ResponseEntity<List <NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Alterar dados dos ninja(Update)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id,@RequestBody NinjaDTO ninjaAtualizadp){
        NinjaDTO ninja =  ninjaService.atualizarNinja(id,ninjaAtualizadp);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não encontrado.");
        }
    }

    //Deletar Ninja(Delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id){
        if (ninjaService.listarninjasPorId(id)!= null){
        ninjaService.deletarNinjaporId(id);
        return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso.");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id " + id + " não encontrado.");
        }
    }


}
