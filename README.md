# TDE 04 — Cálculo de descontos e frete

Projeto em Java que calcula o valor final de um pedido de e-commerce em duas versões: abordagem tradicional (código primeiro, testes depois) e TDD.

## Regras implementadas

- Totais de itens zerados ou negativos lançam `IllegalArgumentException`.
- Acima de R$ 500,00 e até R$ 1.000,00, aplica-se 10% de desconto.
- Acima de R$ 1.000,00, aplica-se 20% de desconto.
- Cliente VIP recebe mais 5% sobre o valor após o desconto por faixa.
- Se o subtotal depois dos descontos for menor que R$ 100,00, acrescenta-se R$ 15,00 de frete.
- O resultado usa duas casas decimais e arredondamento `HALF_UP`.

## Executar a suíte

No PowerShell, a partir da pasta do projeto:

```powershell
javac -encoding UTF-8 -d out src/main/java/br/com/ecommerce/*.java src/test/java/br/com/ecommerce/CalculadorasDescontoTest.java
java -cp out br.com.ecommerce.CalculadorasDescontoTest
```

A execução final da suíte registrou 33 verificações aprovadas.
