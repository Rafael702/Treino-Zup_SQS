package br.com.zup.edu.marketplace.service;

import br.com.zup.edu.marketplace.infra.sqs.NotificacaoService;
import br.com.zup.edu.marketplace.model.Promocao;
import br.com.zup.edu.marketplace.repository.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;

    @Autowired
    private NotificacaoService service;

    public Promocao salvarPromocao(Promocao promocao) {
        Promocao promocaoSalva =  promocaoRepository.save(promocao);
        service.send(promocaoSalva.getTitulo(), promocaoSalva.getMensagem());
        return promocaoSalva;
    }
}
