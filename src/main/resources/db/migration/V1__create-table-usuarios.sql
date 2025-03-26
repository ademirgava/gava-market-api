create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)

);

insert into gava_market.usuarios values(1, 'juninhogava', '$2a$12$FzTKPfeoxdhWOBdTyriQt.VTiCS.g9lGsuBDlq5HERXlY3MpSH2wS');