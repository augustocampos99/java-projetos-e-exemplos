CREATE TABLE "funcionarios" (
	"id" UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	"cpf" VARCHAR(11) NOT NULL,
	"nome" VARCHAR(100) NOT NULL,
	"cargo" VARCHAR(200) NOT NULL,
	"status" VARCHAR(10) NOT NULL,
	"data_criacao" TIMESTAMP NOT NULL,
	"data_atualizacao" TIMESTAMP NOT NULL
);

