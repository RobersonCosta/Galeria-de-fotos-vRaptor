create table usuario (
id serial primary key,
login varchar not null,
senha varchar not null
);

create table galeria (
id serial primary key,
id_usuario int references usuario on delete cascade,
titulo varchar not null,
descricao varchar not null
);

create table imagem (
id serial primary key,
id_galeria int references galeria on delete cascade,
descricao varchar not null,
arquivo varchar not null
);

