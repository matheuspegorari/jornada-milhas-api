create table depoimentos (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    imagem_url varchar(100) not null,
    depoimento varchar(4000) not null,
    primary key (id)
)