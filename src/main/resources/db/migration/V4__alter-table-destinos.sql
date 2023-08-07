ALTER TABLE destinos DROP COLUMN imagem_url;
ALTER TABLE destinos ADD (
    imagem_url1 varchar(255) not null,
    imagem_url2 varchar(255) not null,
    meta varchar(160) not null,
    texto varchar(4000)
    );


