package br.com.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Calculadora implementada na abordagem tradicional (código antes dos testes). */
public class CalculadoraDescontoTradicional {
    private static final BigDecimal LIMITE_500 = new BigDecimal("500.00");
    private static final BigDecimal LIMITE_1000 = new BigDecimal("1000.00");
    private static final BigDecimal FRETE_LIMITE = new BigDecimal("100.00");
    private static final BigDecimal FRETE = new BigDecimal("15.00");

    public BigDecimal calcular(BigDecimal totalItens, CategoriaCliente categoria) {
        Objects.requireNonNull(totalItens, "totalItens não pode ser nulo");
        Objects.requireNonNull(categoria, "categoria não pode ser nula");
        if (totalItens.signum() <= 0) {
            throw new IllegalArgumentException("O total dos itens deve ser positivo");
        }

        BigDecimal percentual = BigDecimal.ZERO;
        if (totalItens.compareTo(LIMITE_1000) > 0) {
            percentual = new BigDecimal("0.20");
        } else if (totalItens.compareTo(LIMITE_500) > 0) {
            percentual = new BigDecimal("0.10");
        }
        BigDecimal finalPedido = totalItens.multiply(BigDecimal.ONE.subtract(percentual));
        if (categoria == CategoriaCliente.VIP) {
            finalPedido = finalPedido.multiply(new BigDecimal("0.95"));
        }
        if (finalPedido.compareTo(FRETE_LIMITE) < 0) {
            finalPedido = finalPedido.add(FRETE);
        }
        return finalPedido.setScale(2, RoundingMode.HALF_UP);
    }
}
