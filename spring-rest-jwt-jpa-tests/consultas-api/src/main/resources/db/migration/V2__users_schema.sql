CREATE TABLE "usuarios" (
	"id" UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	"nome" VARCHAR(100) NOT NULL,
	"username" VARCHAR(100) NOT NULL,
	"password" VARCHAR(200) NOT NULL,
	"role" VARCHAR(30) NOT NULL
);


