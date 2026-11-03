package br.com.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Calculadora cuja evolução é registrada pelos ciclos TDD no histórico Git. */
public class CalculadoraDescontoTDD {
    private static final BigDecimal LIMITE_DESCONTO_10 = new BigDecimal("500.00");
    private static final BigDecimal LIMITE_DESCONTO_20 = new BigDecimal("1000.00");
    private static final BigDecimal LIMITE_FRETE = new BigDecimal("100.00");
    private static final BigDecimal FRETE = new BigDecimal("15.00");
    private static final BigDecimal FATOR_DESCONTO_10 = new BigDecimal("0.10");
    private static final BigDecimal FATOR_DESCONTO_20 = new BigDecimal("0.20");
    private static final BigDecimal FATOR_DESCONTO_VIP = new BigDecimal("0.95");

    public BigDecimal calcular(BigDecimal totalItens, CategoriaCliente categoria) {
        Objects.requireNonNull(totalItens, "totalItens não pode ser nulo");
        Objects.requireNonNull(categoria, "categoria não pode ser nula");
        if (totalItens.signum() <= 0) {
            throw new IllegalArgumentException("O total dos itens deve ser positivo");
        }

        BigDecimal percentual = BigDecimal.ZERO;
        if (totalItens.compareTo(LIMITE_DESCONTO_20) > 0) {
            percentual = FATOR_DESCONTO_20;
        } else if (totalItens.compareTo(LIMITE_DESCONTO_10) > 0) {
            percentual = FATOR_DESCONTO_10;
        }
        BigDecimal finalPedido = totalItens.multiply(BigDecimal.ONE.subtract(percentual));
        if (categoria == CategoriaCliente.VIP) {
            finalPedido = finalPedido.multiply(FATOR_DESCONTO_VIP);
        }
        if (finalPedido.compareTo(LIMITE_FRETE) < 0) {
            finalPedido = finalPedido.add(FRETE);
        }
        return finalPedido.setScale(2, RoundingMode.HALF_UP);
    }
}
