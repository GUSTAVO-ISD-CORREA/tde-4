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
        BigDecimal subtotal = totalItens;
        if (totalItens.compareTo(new BigDecimal("1000.00")) > 0) {
            subtotal = totalItens.multiply(new BigDecimal("0.80"));
        } else if (totalItens.compareTo(new BigDecimal("500.00")) > 0
                && totalItens.compareTo(new BigDecimal("1000.00")) <= 0) {
            subtotal = totalItens.multiply(new BigDecimal("0.90"));
        }
        if (categoria == CategoriaCliente.VIP) {
            subtotal = subtotal.multiply(new BigDecimal("0.95"));
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }
}
