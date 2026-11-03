# Análise das abordagens

## Etapa 1 — Code First, Test Later

O código tradicional foi escrito antes da suíte. A suíte cobre os limites de R$ 500, R$ 1.000 e R$ 100, desconto VIP, cálculo do frete após descontos e valores inválidos.

**Tempo gasto:** não cronometrado durante a atividade; registrar a duração real ao executar esta etapa em sala. Não é possível reconstruir uma medição confiável depois.

**Dificuldades observadas:** escrever testes depois de implementar exige reconstruir as decisões tomadas no código; limites estritos e ordem dos descontos/frete são fáceis de deixar implícitos. Casos de fronteira e valores monetários exigem comparar valores decimais sem depender da escala interna do `BigDecimal`.

## Etapa 2 — TDD

A classe `CalculadoraDescontoTDD` e a suíte autocontida cobrem os requisitos. Os commits locais registram a implementação tradicional e, depois, a calculadora TDD com seus testes. Eles não registram separadamente cada estado vermelho e verde; portanto, não comprovam um ciclo TDD por regra no histórico. Como este ambiente não possui Maven, os testes são executáveis diretamente com `javac` e `java`.

## Decisões

- Os descontos de 10% e 20% são faixas mutuamente exclusivas; acima de R$ 1.000 aplica-se 20%, não 10% + 20%.
- O desconto VIP é aplicado sobre o valor após o desconto por faixa.
- O frete é adicionado se, depois dos descontos, o subtotal for estritamente inferior a R$ 100.
- Valores monetários são arredondados para duas casas com `HALF_UP` no resultado final.
