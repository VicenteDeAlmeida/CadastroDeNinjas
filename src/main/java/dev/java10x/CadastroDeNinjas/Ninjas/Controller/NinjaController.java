package dev.java10x.CadastroDeNinjas.Ninjas.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota.";
    }

    //Adicionar Ninja(Create)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja Criado!";
    }

    //Procurar Ninja por Id(Read)
    @GetMapping("todosID")
    public String mostrarTodosOsNinjasporId(){
        return "Mostrar Ninja por ID";
    }

    //Mostrar todos os ninjas (Read)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas(){
        return "Ninja Criado";
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
