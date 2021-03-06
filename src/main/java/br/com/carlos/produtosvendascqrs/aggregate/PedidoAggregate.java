package br.com.carlos.produtosvendascqrs.aggregate;


import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import br.com.carlos.produtosvendascqrs.json.ItemPedidoJson;
import br.com.carlos.produtosvendascqrs.model.command.pedido.AddItemPedidoToPedidoCommand;
import br.com.carlos.produtosvendascqrs.model.command.pedido.AddPedidoCommand;
import br.com.carlos.produtosvendascqrs.model.event.pedido.ItemPedidoAddedToPedidoEvent;
import br.com.carlos.produtosvendascqrs.model.event.pedido.PedidoAddedEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author carlos.oliveira
 */
@Slf4j
@Getter
@Setter
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class PedidoAggregate implements Serializable {

    @AggregateIdentifier
    private String id;
    private List<ItemPedidoJson> itens;
    private ItemPedidoJson item;

    @CommandHandler
    public PedidoAggregate(AddPedidoCommand comando) {
        log.info("Handling {} command: {}", comando.getClass().getSimpleName(), comando);
        notNull(comando.getId(), "Id não pode ser null");
        notEmpty(comando.getItens(), "Lista de Produtos não pode estar vazia ou nula");
        isTrue(comando.getItens().stream().anyMatch(
                itemPedido -> (itemPedido.getQuantidade() != null && itemPedido.getQuantidade() > 0) && (
                        itemPedido.getProdutoId() != null)),
                "A quantidade não pode estar vazia e o produto deve ser informado");

        apply(new PedidoAddedEvent(comando.getId(), comando.getItens()));
        log.info("Done handling {} command: {}", comando.getClass().getSimpleName(), comando);
    }

    @EventSourcingHandler
    public void on(PedidoAddedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

        this.id = event.getId();
        this.itens = event.getItens();

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    //-------------------------------------------------------------------------------------------------------//

    @CommandHandler
    public PedidoAggregate(AddItemPedidoToPedidoCommand comando) {
        log.info("Handling {} command: {}", comando.getClass().getSimpleName(), comando);
        notNull(comando.getId(), "Id não pode ser null");
        ItemPedidoJson item = comando.getItem();
        notNull(item, "Item Pedido não pode ser null");
        notNull(item.getProdutoId(), "Produto Id não pode ser null");
        isTrue(item.getQuantidade() != null && item.getQuantidade() > 0, "A quantidade não pode estar vazia");

        apply(new ItemPedidoAddedToPedidoEvent(comando.getId(), comando.getItem()));
        log.info("Done handling {} command: {}", comando.getClass().getSimpleName(), comando);
    }

    @EventSourcingHandler
    public void on(ItemPedidoAddedToPedidoEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);

        this.id = event.getId();
        this.item = event.getItem();

        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

}
