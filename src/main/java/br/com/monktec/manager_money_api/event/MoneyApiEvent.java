package br.com.monktec.manager_money_api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class MoneyApiEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Long codigo;

    public MoneyApiEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);

        this.response = response;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getCodigo() {
        return codigo;
    }


}
