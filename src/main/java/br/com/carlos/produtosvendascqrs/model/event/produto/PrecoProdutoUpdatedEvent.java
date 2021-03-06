package br.com.carlos.produtosvendascqrs.model.event.produto;


import java.io.Serializable;
import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import br.com.carlos.produtosvendascqrs.model.event.EventoIdentificavel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * @author carlos.oliveira
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrecoProdutoUpdatedEvent extends EventoIdentificavel implements Serializable {

    @TargetAggregateIdentifier
    private String id;
    private BigDecimal preco;

}
