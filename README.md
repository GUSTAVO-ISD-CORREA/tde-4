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

## Etapa 1 — Tempo e dificuldades

O tempo total foi de cerca de 35 minutos: 15 minutos para implementar a calculadora tradicional e 20 minutos para escrever, executar e ajustar a suíte de testes. A dificuldade foi moderada. A parte que exigiu mais atenção foi implementar a lógica, principalmente os limites estritos dos descontos e do frete, além da ordem entre desconto por faixa, desconto VIP e frete.

## Etapa 3 — Reflexão

### Quando ocorreram verificação e validação?

Na abordagem tradicional, a verificação dinâmica ocorreu depois da implementação, com a execução dos testes e a comparação dos resultados com os valores esperados. A validação ocorreu ao conferir se os cenários de teste representavam as necessidades de cobrança descritas no enunciado.

No TDD, a verificação dinâmica ocorreu em cada ciclo: o teste falhou antes da implementação (RED), passou após a mudança (GREEN) e foi executado novamente depois da refatoração. A validação foi apoiada por cenários derivados das regras de negócio. Em ambas as abordagens, analisar os requisitos e conferir os limites também faz parte da verificação estática.

### Como a inversão da ordem dos testes afeta a verificação estática e dinâmica?

Escrever os testes antes do código leva o desenvolvedor a analisar requisitos, limites e resultados esperados antes de implementar cada regra. Isso fortalece a verificação estática. Na verificação dinâmica, executar os testes a cada ciclo dá retorno frequente e ajuda a encontrar falhas mais cedo. O TDD complementa a análise dos requisitos, mas não a substitui.
