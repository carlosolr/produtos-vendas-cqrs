package br.com.carlos.produtosvendascqrs.model.event.pedido;


import java.io.Serializable;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import br.com.carlos.produtosvendascqrs.json.ItemPedido;
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
public class ItemPedidoAddedToPedidoEvent extends EventoIdentificavel implements Serializable {

    @TargetAggregateIdentifier
    private String id;
    private ItemPedido item;

}
