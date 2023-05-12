package br.com.zup.edu.marketplace.controller;

import br.com.zup.edu.marketplace.model.Promocao;
import br.com.zup.edu.marketplace.service.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketplace")
public class MarketplaceController {

    @Autowired
    private PromocaoService service;

    @PostMapping("/promocao")
    @ResponseStatus(HttpStatus.CREATED)
    public Promocao cadastrarPromocao(@RequestBody Promocao promocao) {
        return service.salvarPromocao(promocao);
    }
}
