INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (1, 'admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);
INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (2, 'user@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);

INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');


-- # ec2 rds
-- DROP database chungyakpass;
-- CREATE database chungyakpass;
--
-- USE chungyakpass;
--
-- INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (1, 'admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);
-- INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (2, 'user@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);
--
-- INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_USER');
-- INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_ADMIN');
--
-- INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
-- INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
-- INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');
--
-- SELECT * FROM authority ;
-- SELECT * FROM inp_user ;
-- SELECT * FROM user_authority ;
-- SELECT * FROM inp_user_bankbook ;
-- SELECT * FROM inp_address ;
-- SELECT * FROM inp_house ;
-- SELECT * FROM inp_house_member ;
-- SELECT * FROM inp_house_member_relation ;