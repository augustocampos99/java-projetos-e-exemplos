CREATE TABLE "pacientes" (
	"id" UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	"cpf" VARCHAR(20) NOT NULL,
	"nome" VARCHAR(100) NOT NULL
);

CREATE TABLE "medicos" (
	"id" UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	"crm" VARCHAR(20) NOT NULL,
	"nome" VARCHAR(100) NOT NULL
);

CREATE TABLE "consultas" (
	"id" UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	"id_medico" UUID NOT NULL,
	"id_paciente" UUID NOT NULL,
	"data_consulta" TIMESTAMP NOT NULL,
	"observacao" TEXT NULL
);

