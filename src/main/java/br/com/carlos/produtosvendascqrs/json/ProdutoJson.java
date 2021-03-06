package br.com.carlos.produtosvendascqrs.json;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * @author carlos.oliveira
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoJson implements Serializable {

    private String nome;
    private BigDecimal preco;

}
