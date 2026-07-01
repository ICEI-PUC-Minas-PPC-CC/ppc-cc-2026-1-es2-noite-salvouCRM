package com.salvou.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository repository;



    // 1. Abre a página do formulário
    @GetMapping("/novo")
    public String abrirFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro-cliente";
    }

    // 2. Recebe os dados do formulário e salva no banco
    @PostMapping("/salvar")
    public String salvar(Cliente cliente, RedirectAttributes attributes) {
        // Verifica se o CPF já existe
        if (repository.existsByCpf(cliente.getCpf())) {
            attributes.addFlashAttribute("mensagemErro", "Este CPF já está cadastrado!");
            return "redirect:/novo";
        }

        repository.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/rapido")
    @ResponseBody
    public ResponseEntity<?> salvarClienteRapido(@RequestBody Cliente cliente) {
        // 1. Verifica se o CPF já existe
        if (repository.existsByCpf(cliente.getCpf())) {
            return ResponseEntity.badRequest().body("Erro: Já existe um cliente com este CPF.");
        }

        // 2. Salva e retorna sucesso
        Cliente salvo = repository.save(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = repository.findAll();

      
        model.addAttribute("clientes", clientes);
        return "lista-clientes";
    }

    @GetMapping("/clientes/perfil/{id}")
    public String mostrarPerfil(@PathVariable Long id, Model model) {
        Cliente cliente = repository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "perfil-cliente"; 
    }

}