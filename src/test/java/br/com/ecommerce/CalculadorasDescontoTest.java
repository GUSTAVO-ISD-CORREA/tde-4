package br.com.ecommerce;

import java.math.BigDecimal;

/** Suíte unitária autocontida, executável sem dependências externas. */
public final class CalculadorasDescontoTest {
    private static int verificacoes;

    public static void main(String[] args) {
        testaDescontosPorFaixa(CalculadoraDescontoTradicional.class.getSimpleName(), new CalculadoraDescontoTradicional()::calcular);
        testaVip(CalculadoraDescontoTradicional.class.getSimpleName(), new CalculadoraDescontoTradicional()::calcular);
        testaFrete(CalculadoraDescontoTradicional.class.getSimpleName(), new CalculadoraDescontoTradicional()::calcular);
        testaValoresInvalidos(CalculadoraDescontoTradicional.class.getSimpleName(), new CalculadoraDescontoTradicional()::calcular);
        testaDescontosPorFaixa(CalculadoraDescontoTDD.class.getSimpleName(), new CalculadoraDescontoTDD()::calcular);
        testaVip(CalculadoraDescontoTDD.class.getSimpleName(), new CalculadoraDescontoTDD()::calcular);
        testaFrete(CalculadoraDescontoTDD.class.getSimpleName(), new CalculadoraDescontoTDD()::calcular);
        testaValoresInvalidos(CalculadoraDescontoTDD.class.getSimpleName(), new CalculadoraDescontoTDD()::calcular);
        System.out.println("OK: " + verificacoes + " verificações");
    }

    private static void testaDescontosPorFaixa(String nome, Calculadora calc) {
        eq(nome + " sem desconto até 500", "500.00", calc.calcular(bd("500"), CategoriaCliente.PADRAO));
        eq(nome + " 10% acima de 500", "540.00", calc.calcular(bd("600"), CategoriaCliente.PADRAO));
        eq(nome + " 10% inclusive em 1000", "900.00", calc.calcular(bd("1000"), CategoriaCliente.PADRAO));
        eq(nome + " 20% acima de 1000", "880.80", calc.calcular(bd("1101"), CategoriaCliente.PADRAO));
    }

    private static void testaVip(String nome, Calculadora calc) {
        eq(nome + " VIP adicional sobre desconto", "855.00", calc.calcular(bd("1000"), CategoriaCliente.VIP));
    }

    private static void testaFrete(String nome, Calculadora calc) {
        eq(nome + " frete quando final abaixo de 100", "100.50", calc.calcular(bd("90"), CategoriaCliente.VIP));
        eq(nome + " frete abaixo de 100", "114.00", calc.calcular(bd("99"), CategoriaCliente.PADRAO));
        eq(nome + " sem frete no limite de 100", "100.00", calc.calcular(bd("100"), CategoriaCliente.PADRAO));
    }

    private static void testaValoresInvalidos(String nome, Calculadora calc) {
        invalid(nome + " zero", () -> calc.calcular(bd("0"), CategoriaCliente.PADRAO));
        invalid(nome + " negativo", () -> calc.calcular(bd("-1"), CategoriaCliente.PADRAO));
    }

    private static BigDecimal bd(String valor) { return new BigDecimal(valor); }
    private static void eq(String caso, String esperado, BigDecimal obtido) {
        verificacoes++;
        if (bd(esperado).compareTo(obtido) != 0) throw new AssertionError(caso + ": esperado " + esperado + ", obtido " + obtido);
    }
    private static void invalid(String caso, Runnable acao) {
        verificacoes++;
        try { acao.run(); } catch (IllegalArgumentException esperado) { return; }
        throw new AssertionError(caso + ": deveria lançar IllegalArgumentException");
    }
    @FunctionalInterface private interface Calculadora {
        BigDecimal calcular(BigDecimal totalItens, CategoriaCliente categoria);
    }
}
