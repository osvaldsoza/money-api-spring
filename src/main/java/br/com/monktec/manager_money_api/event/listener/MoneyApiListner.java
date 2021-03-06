package br.com.monktec.manager_money_api.event.listener;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class MoneyApiListner implements ApplicationListener<MoneyApiEvent> {

    @Override
    public void onApplicationEvent(MoneyApiEvent moneyApiEvent) {
        HttpServletResponse response = moneyApiEvent.getResponse();
        Long codigo = moneyApiEvent.getCodigo();

        adicionaHeaderLocation(response, codigo);
    }

    private void adicionaHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
