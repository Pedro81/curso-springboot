INSERT INTO usuarios (username, pasword, enabled, nombre, apellido, email) VALUES('pera','$2a$10$jIJxK3f73gAnHFKjD2Nv7O1SvFZNvIMJIv1Ip2s7R5rgzQpCy2MjW',1,'Pedro','Diaz','pera.diaz@gmail.com');
INSERT INTO usuarios (username, pasword, enabled, nombre, apellido, email) VALUES('admin','$2a$10$7UjMvERj1jVfsdiMzm19quUC6pKx7cc1jGJbrGYq3QnseUnqVt/EK',1,'Thor','dd','thor.dd@gmail.com');

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,2);