package br.com.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Implementação construída incrementalmente pelos ciclos TDD. */
public class CalculadoraDescontoTDD {
    private static final BigDecimal LIMITE_500 = new BigDecimal("500.00");
    private static final BigDecimal LIMITE_1000 = new BigDecimal("1000.00");
    private static final BigDecimal FRETE_LIMITE = new BigDecimal("100.00");
    private static final BigDecimal FRETE = new BigDecimal("15.00");
    private static final BigDecimal DESCONTO_10 = new BigDecimal("0.10");
    private static final BigDecimal DESCONTO_20 = new BigDecimal("0.20");
    private static final BigDecimal FATOR_VIP = new BigDecimal("0.95");

    public BigDecimal calcular(BigDecimal totalItens, CategoriaCliente categoria) {
        Objects.requireNonNull(totalItens, "totalItens não pode ser nulo");
        Objects.requireNonNull(categoria, "categoria não pode ser nula");
        if (totalItens.signum() <= 0) {
            throw new IllegalArgumentException("O total dos itens deve ser positivo");
        }
        BigDecimal subtotal = totalItens;
        if (totalItens.compareTo(LIMITE_1000) > 0) {
            subtotal = totalItens.multiply(BigDecimal.ONE.subtract(DESCONTO_20));
        } else if (totalItens.compareTo(LIMITE_500) > 0) {
            subtotal = totalItens.multiply(BigDecimal.ONE.subtract(DESCONTO_10));
        }
        if (categoria == CategoriaCliente.VIP) {
            subtotal = subtotal.multiply(FATOR_VIP);
        }
        if (subtotal.compareTo(FRETE_LIMITE) < 0) {
            subtotal = subtotal.add(FRETE);
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }
}
