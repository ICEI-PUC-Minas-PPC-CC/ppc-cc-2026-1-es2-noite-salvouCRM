package com.salvou.crm;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.salvou.crm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/venda")
    public String novaVenda(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        // Adicionamos um objeto venda vazio para o formulário do Thymeleaf se basear
        model.addAttribute("venda", new Venda());
        return "venda";
    }

    @PostMapping("/venda/finalizar")
    @ResponseBody
    public ResponseEntity<String> finalizarVenda(@RequestBody Venda venda) {
        try {
            // 1. Buscar o cliente real no banco pelo ID enviado pelo JS
            Cliente clienteBanco = clienteRepository.findById(venda.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // 2. Buscar os serviços reais para calcular o total e criar o vínculo
            double totalCalculado = 0;
            List<Servico> servicosReais = new ArrayList<>();

            for (Servico s : venda.getServicos()) {
                Servico servicoBanco = servicoRepository.findById(s.getId()).orElse(null);
                if (servicoBanco != null) {
                    totalCalculado += servicoBanco.getPreco();
                    servicosReais.add(servicoBanco);
                }
            }

            // 3. Configurar a venda com os dados do banco
            venda.setCliente(clienteBanco); // Aqui o vínculo é criado!
            venda.setServicos(servicosReais);
            venda.setValorTotal(totalCalculado);
            venda.setDataVenda(LocalDateTime.now());

            // 4. Salvar a venda
            vendaRepository.save(venda);

            return ResponseEntity.ok("Venda salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao salvar: " + e.getMessage());
        }
    }

    @PostMapping("/servicos/rapido")
    @ResponseBody
    public Servico salvarServicoRapido(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

    @GetMapping("/teste")
    @ResponseBody
    public String teste() {
        return "O Controller de Vendas está funcionando!";
    }
}