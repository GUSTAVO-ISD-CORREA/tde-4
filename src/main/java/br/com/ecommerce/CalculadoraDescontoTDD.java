package br.com.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Implementação construída incrementalmente pelos ciclos TDD. */
public class CalculadoraDescontoTDD {
    public BigDecimal calcular(BigDecimal totalItens, CategoriaCliente categoria) {
        Objects.requireNonNull(totalItens, "totalItens não pode ser nulo");
        Objects.requireNonNull(categoria, "categoria não pode ser nula");
        if (totalItens.signum() <= 0) {
            throw new IllegalArgumentException("O total dos itens deve ser positivo");
        }
        return totalItens.setScale(2, RoundingMode.HALF_UP);
    }
}
