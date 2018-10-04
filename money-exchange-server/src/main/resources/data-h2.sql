insert into CURRENCY (CURRENCY_CODE,CURRENCY_NAME, CURRENCY_SYMBOL) VALUES ('USD', 'DOLARES AMERICANOS', '$');
insert into CURRENCY (CURRENCY_CODE,CURRENCY_NAME, CURRENCY_SYMBOL) VALUES ('EUR', 'EUROS','€');

insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (6,1.9923,PARSEDATETIME('2018-10-05 09:11:34','yyyy-MM-dd HH:mm:ss'),'USD','EUR');
insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (5,1.9923,PARSEDATETIME('2018-10-04 09:56:54','yyyy-MM-dd HH:mm:ss'),'USD','EUR');
insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (4,1.9923,PARSEDATETIME('2018-10-03 11:22:22','yyyy-MM-dd HH:mm:ss'),'USD','EUR');
insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (3,1.9343,PARSEDATETIME('2018-10-02 10:03:32','yyyy-MM-dd HH:mm:ss'),'USD','EUR');
insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (2,1.5455,PARSEDATETIME('2018-10-01 09:02:12','yyyy-MM-dd HH:mm:ss'),'USD','EUR');
insert into EXCHANGE_RATE (EXCHANGE_ID,EXCHANGE_RATE,EXCHANGE_DATE,ORIGIN_CURRENCY_CODE,DEST_CURRENCY_CODE) VALUES (1,1.8923,PARSEDATETIME('2018-09-30 11:22:22','yyyy-MM-dd HH:mm:ss'),'USD','EUR');

insert into USER (USER_ID, USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME) values (1, 'admin', '$2a$10$fc2wGSL3P2JP0O2CwKJeaugDvhZEN2Mf./JI0Xm2nse7BIdpyuh46', 'Admin', 'Of Belatrix');