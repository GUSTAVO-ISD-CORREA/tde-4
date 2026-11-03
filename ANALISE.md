# Análise das abordagens

## Etapa 1 — Code First, Test Later

A calculadora tradicional foi implementada antes de sua suíte. Depois, os testes foram escritos para confrontar o código com as regras e os casos de fronteira.

**Tempo:** cerca de 35 minutos no total: 15 minutos para implementar a `CalculadoraDescontoTradicional` e 20 minutos para escrever, executar e ajustar os cenários de teste, conforme informado pelo aluno.

**Dificuldades:** ao testar depois do código, há risco de escolher casos que confirmem a implementação em vez de questionar os requisitos. Foi necessário conferir os limites estritos (`> 500`, `> 1000`, `< 100`), a precedência dos descontos e o cálculo do frete após o desconto VIP. Valores `BigDecimal` foram comparados por valor, sem depender da escala.

## Etapa 2 — TDD

A calculadora TDD foi reiniciada em um novo commit, sem alterar os commits que já estavam no GitHub. Em seguida, cada regra foi exercitada em RED, implementada até GREEN e registrada em commit. Por fim, as constantes foram extraídas e toda a suíte foi executada novamente.

Os ciclos realizados são identificáveis no histórico Git pelos commits `chore(tdd): reinicia`, `feat(tdd): ciclo 1` a `ciclo 5` e `refactor(tdd)`. O RED foi observado executando a suíte contra o esqueleto ou a implementação incompleta; os commits das regras guardam o teste e a implementação GREEN correspondente.

Os testes são executáveis sem dependências externas:

```powershell
javac -encoding UTF-8 -d out src/main/java/br/com/ecommerce/*.java src/test/java/br/com/ecommerce/CalculadorasDescontoTest.java
java -cp out br.com.ecommerce.CalculadorasDescontoTest
```

Na execução final, a suíte informou `OK: 33 verificações`.

## Regras e decisões

- Valores nulos são rejeitados; totais zerados ou negativos lançam `IllegalArgumentException`.
- Acima de R$ 500,00 e até R$ 1.000,00 aplica-se 10%; acima de R$ 1.000,00 aplica-se 20%. As faixas são mutuamente exclusivas.
- O desconto VIP de 5% é calculado sobre o subtotal após o desconto por faixa.
- O frete de R$ 15,00 é adicionado quando o subtotal após todos os descontos fica estritamente abaixo de R$ 100,00.
- O arredondamento para duas casas usa `RoundingMode.HALF_UP` no resultado final.

## BDD — exemplos de comportamento

- Dado um cliente padrão e itens de R$ 500,01, quando calcular o pedido, então aplicar 10% e retornar R$ 450,01.
- Dado um cliente VIP e itens de R$ 105,00, quando calcular o pedido, então aplicar 5% de desconto, cobrar R$ 15,00 de frete sobre o subtotal de R$ 99,75 e retornar R$ 114,75.
- Dado um subtotal de exatamente R$ 100,00 após descontos, quando calcular o pedido, então não cobrar frete.

## Verificação e validação

Na abordagem tradicional, a verificação dinâmica ocorreu após a implementação, com a execução da suíte. A validação relacionou os resultados aos cenários de cobrança pedidos. No TDD, a execução dos testes começou antes da implementação de cada regra (RED), passou após a mudança (GREEN) e foi repetida depois da refatoração. A análise dos limites e das regras corresponde à verificação estática; os cenários ligados ao objetivo do e-commerce apoiam a validação. Testes unitários não substituem a validação em um ambiente de produção.
