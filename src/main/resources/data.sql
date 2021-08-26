INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (1, 'admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);
INSERT INTO inp_user (USER_ID, EMAIL, PASSWORD, ACTIVATED) VALUES (2, 'user@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);

INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');
--
-- INSERT INTO std_aptinfo(notification_number_id,Housing_Type,SCHEDULED_OCCUPANCY) values (2021000580, '민영','2023-09');
-- INSERT INTO std_area_leve1(AREA_LEVEL1_ID,ADDRESS_LEVEL1,NEARBY_AREA,DEPOSIT_AMOUNT_AREA,METROPOLITAN_AREA  )  VALUES (1,'서울',1,'서울부산','y'),(2,'인천',1,'기타광역시','y'),(3,'경기',1,'기타시군','y'),(4,'대전',2,'기타광역시','n'),(5,'세종',2,'기타시군','n'),(6,'충남',2,'기타시군','n'),(7,'충북',3,'기타시군','n'),(8,'광주',4,'기타광역시','n'),(9,'전남',4,'기타시군','n'),(10,'전북',5,'기타시군','n'),(11,'대구',6,'기타광역시','n'),(12,'경북',6,'기타시군','n'),(13,'부산',7,'서울부산','n'),(14,'울산',7,'기타광역시','n'),(15,'경남',7,'기타시군','n'),(16,'강원도',8,'기타시군','n'),(17,'제주',9,'기타시군','n');
-- INSERT INTO std_priority_deposit_amount(priority_deposit_amount_id,AREA_BELOW,AREA_EXCESS,DEPOSIT_AMOUNT_AREA,DEPOSIT_AMOUNT ) VALUES (1,0,85,'서울부산',3000000),(2,0,85,'기타광역시',2500000),(3,0,85,'기타시군',2000000),(4,85,102,'서울부산',6000000),(5,85,102,'기타광역시',4000000),(6,85,102,'기타시군',3000000),(7,102,135,'서울부산',10000000),(8,102,135,'기타광역시',7000000),(9,102,135,'기타시군',4000000),(10,135,NULL,'서울부산',15000000),(11,135,NULL,'기타광역시',10000000),(12,135,NULL,'기타시군',5000000);
--

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