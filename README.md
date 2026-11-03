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

A execução final registrou 33 verificações aprovadas.

## Etapa 1 — Abordagem tradicional

O aluno informou que levou cerca de 35 minutos: 15 minutos para implementar a calculadora tradicional e 20 minutos para escrever, executar e ajustar os testes.

As principais dificuldades foram conferir os limites estritos de R$ 500,00, R$ 1.000,00 e R$ 100,00; testar a ordem desconto por faixa, desconto VIP e frete; e comparar `BigDecimal` pelo valor, sem depender da escala.

## Etapa 3 — Reflexão

### Quando ocorreram verificação e validação?

Na abordagem tradicional, a verificação dinâmica ocorreu depois da implementação, com a execução dos testes unitários e a comparação dos resultados com os valores esperados. A validação ocorreu ao conferir se esses cenários representavam as necessidades do sistema de cobrança descritas no enunciado.

No TDD, a verificação dinâmica ocorreu em cada ciclo: o teste falhou antes da implementação (RED), passou após a mudança (GREEN) e foi executado novamente depois da refatoração. A validação foi apoiada por cenários derivados das regras de negócio. Em ambas as abordagens, a leitura dos requisitos e a revisão das condições de fronteira também fazem parte da verificação estática.

### Como a inversão da ordem dos testes afeta a verificação estática e dinâmica?

Ao escrever os testes antes do código, o TDD leva o desenvolvedor a pensar nos requisitos, limites e resultados esperados durante a análise e o desenho da solução. Isso fortalece a verificação estática antes da implementação. Na verificação dinâmica, a execução frequente dos testes fornece retorno a cada regra, revelando falhas mais cedo. O TDD complementa a análise dos requisitos; os testes não a substituem.
