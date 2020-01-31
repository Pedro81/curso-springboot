INSERT INTO usuarios (username, pasword, enabled, nombre, apellido, email) VALUES('pera','$2a$10$7xqJPHxrcAznI.6hZ0Ap6.kpikPISWBLmSFAhjGXvFjY6weCVH1im',1,'Pedro','Diaz','pera.diaz@gmail.com');
INSERT INTO usuarios (username, pasword, enabled, nombre, apellido, email) VALUES('admin','$2a$10$ztUW/ET4wz8sEX8U6ixnduNdB47xHbvh.yo5XSZ0.IoitrYJGFESC',1,'Thor','dd','thor.dd@gmail.com');

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,2);