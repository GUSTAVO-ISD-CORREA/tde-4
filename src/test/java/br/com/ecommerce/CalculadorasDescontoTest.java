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
        testaRegra1TDD();
        testaRegra2TDD();
        testaRegra3TDD();
        System.out.println("OK: " + verificacoes + " verificações");
    }

    private static void testaRegra1TDD() {
        CalculadoraDescontoTDD calc = new CalculadoraDescontoTDD();
        eq("TDD regra 1 valor base", "200.00", calc.calcular(bd("200"), CategoriaCliente.PADRAO));
        testaValoresInvalidos("CalculadoraDescontoTDD", calc::calcular);
    }

    private static void testaRegra2TDD() {
        CalculadoraDescontoTDD calc = new CalculadoraDescontoTDD();
        eq("TDD regra 2 limite 500", "500.00", calc.calcular(bd("500"), CategoriaCliente.PADRAO));
        eq("TDD regra 2 limite 500.01", "450.01", calc.calcular(bd("500.01"), CategoriaCliente.PADRAO));
        eq("TDD regra 2 valor 600", "540.00", calc.calcular(bd("600"), CategoriaCliente.PADRAO));
        eq("TDD regra 2 limite 1000", "900.00", calc.calcular(bd("1000"), CategoriaCliente.PADRAO));
    }

    private static void testaRegra3TDD() {
        CalculadoraDescontoTDD calc = new CalculadoraDescontoTDD();
        eq("TDD regra 3 limite 1000.01", "800.01", calc.calcular(bd("1000.01"), CategoriaCliente.PADRAO));
        eq("TDD regra 3 valor 1101", "880.80", calc.calcular(bd("1101"), CategoriaCliente.PADRAO));
    }

    private static void testaDescontosPorFaixa(String nome, Calculadora calc) {
        eq(nome + " sem desconto até 500", "500.00", calc.calcular(bd("500"), CategoriaCliente.PADRAO));
        eq(nome + " limite 500.01", "450.01", calc.calcular(bd("500.01"), CategoriaCliente.PADRAO));
        eq(nome + " 10% acima de 500", "540.00", calc.calcular(bd("600"), CategoriaCliente.PADRAO));
        eq(nome + " 10% inclusive em 1000", "900.00", calc.calcular(bd("1000"), CategoriaCliente.PADRAO));
        eq(nome + " limite 1000.01", "800.01", calc.calcular(bd("1000.01"), CategoriaCliente.PADRAO));
        eq(nome + " 20% acima de 1000", "880.80", calc.calcular(bd("1101"), CategoriaCliente.PADRAO));
    }

    private static void testaVip(String nome, Calculadora calc) {
        eq(nome + " VIP adicional sobre desconto", "855.00", calc.calcular(bd("1000"), CategoriaCliente.VIP));
        eq(nome + " VIP sem faixa", "114.00", calc.calcular(bd("120"), CategoriaCliente.VIP));
        eq(nome + " VIP faixa 10% e HALF_UP", "427.51", calc.calcular(bd("500.01"), CategoriaCliente.VIP));
        eq(nome + " VIP faixa 20%", "836.00", calc.calcular(bd("1100"), CategoriaCliente.VIP));
    }

    private static void testaFrete(String nome, Calculadora calc) {
        eq(nome + " frete quando final abaixo de 100", "100.50", calc.calcular(bd("90"), CategoriaCliente.VIP));
        eq(nome + " frete abaixo de 100", "114.00", calc.calcular(bd("99"), CategoriaCliente.PADRAO));
        eq(nome + " frete após desconto VIP", "114.75", calc.calcular(bd("105"), CategoriaCliente.VIP));
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
