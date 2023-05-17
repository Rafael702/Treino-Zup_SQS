# Você pegou uma tarefa no serviço de um banco digital 
> Para alterar o fluxo de transferência, ao finalizar uma transferência deverá ser enviada uma mensagem para a fila de pagamentos_realizados, para que seja emitido o comprovante do cliente.

>O modelo esperado da mensagem deve ser gerada com os seguintes campos: valor, numeroConta, agenciaConta, origem ( qual serviço solicitou o envio ), tipoPagamento(sendo que possuem alguns tipos padrões : TED, PIX, BOLETO).
Baixe o Projeto base e implemente nele a feature.
Projeto: Projeto Base [https://github.com/zup-academy/bancodigital/tree/sqs-tc1-lt2]