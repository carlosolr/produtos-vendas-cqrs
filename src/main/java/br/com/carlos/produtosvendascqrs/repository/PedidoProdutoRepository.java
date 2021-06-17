package br.com.carlos.produtosvendascqrs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carlos.produtosvendascqrs.json.ItemPedido;
import br.com.carlos.produtosvendascqrs.model.entity.Pedido;
import br.com.carlos.produtosvendascqrs.model.entity.PedidoProduto;


/**
 * @author carlos.oliveira
 */
@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, String> {


}
