package com.salvou.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Abre a página da agenda
    @GetMapping("/agenda")
    public String abrirAgenda(Model model) {
        List<Agenda> agendaNaoConcluida = agendaRepository.findByConcluidoFalse();
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("agendaNaoConcluida", agendaNaoConcluida);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "agenda";
    }

    // Salva um novo item na agenda
    @PostMapping("/agenda/salvar")
    public String salvarAgenda(
            @RequestParam String descricao,
            @RequestParam String tipo,
            @RequestParam String dataAgenda,
            @RequestParam(required = false) String observacoes,
            @RequestParam(required = false) Long clienteId,
            RedirectAttributes attributes) {

        try {
            Agenda novaAgenda = new Agenda();
            novaAgenda.setDescricao(descricao);
            novaAgenda.setTipo(tipo);
            novaAgenda.setDataAgenda(LocalDate.parse(dataAgenda));
            novaAgenda.setObservacoes(observacoes);

            if (clienteId != null && clienteId > 0) {
                Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
                novaAgenda.setCliente(cliente);
            }

            agendaRepository.save(novaAgenda);
            attributes.addFlashAttribute("mensagemSucesso", "Compromisso adicionado com sucesso!");

        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao adicionar compromisso: " + e.getMessage());
        }

        return "redirect:/agenda";
    }

    // Marca como concluído
    @PostMapping("/agenda/concluir/{id}")
    public String concluirAgenda(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            Agenda agenda = agendaRepository.findById(id).orElse(null);
            if (agenda != null) {
                agenda.setConcluido(true);
                agendaRepository.save(agenda);
                attributes.addFlashAttribute("mensagemSucesso", "Compromisso marcado como concluído!");
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao atualizar compromisso!");
        }
        return "redirect:/agenda";
    }

    // Deleta um item da agenda
    @PostMapping("/agenda/deletar/{id}")
    public String deletarAgenda(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            agendaRepository.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Compromisso removido!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao remover compromisso!");
        }
        return "redirect:/agenda";
    }

    // Listar todos os itens (próximos 7 dias)
    @GetMapping("/agenda/proximos")
    public String proximosItens(Model model) {
        LocalDate hoje = LocalDate.now();
        LocalDate daquiASetemanas = hoje.plusDays(7);
        List<Agenda> proximosItens = agendaRepository.findByDataAgendaBetween(hoje, daquiASetemanas);
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("agendaNaoConcluida", agendaRepository.findByConcluidoFalse());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("proximosItens", proximosItens);
        return "agenda";
    }
}
