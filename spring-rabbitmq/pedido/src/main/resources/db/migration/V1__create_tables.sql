CREATE TABLE "pedidos" (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	cpf_cliente VARCHAR(20) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	tipo_pagamento VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL,
	data_criacao  TIMESTAMP NOT NULL,
	data_atualizacao  TIMESTAMP NOT NULL
);

CREATE TABLE "pagamentos" (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	id_pedido UUID NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	tipo_pagamento VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL,
	data_processamento  TIMESTAMP NOT NULL
);
