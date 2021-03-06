CREATE TABLE USER(
	USER_ID BIGINT NOT NULL,
	USER_NAME VARCHAR(20),
	PASSWORD CHAR NOT NULL,
	FIRST_NAME VARCHAR(50),
	LAST_NAME VARCHAR(100),
	PRIMARY KEY (USER_ID)
);

CREATE TABLE CURRENCY(
	CURRENCY_CODE VARCHAR(10) NOT NULL,
	CURRENCY_NAME VARCHAR(25),
	CURRENCY_SYMBOL VARCHAR(10),
	PRIMARY KEY (CURRENCY_CODE)
);

CREATE TABLE EXCHANGE_RATE(
	EXCHANGE_ID VARCHAR(10) NOT NULL,
	EXCHANGE_RATE DECIMAL,
	EXCHANGE_DATE DATE,
	ORIGIN_CURRENCY_CODE VARCHAR(10),
	DEST_CURRENCY_CODE VARCHAR(10),
	PRIMARY KEY (EXCHANGE_ID)
);

ALTER TABLE EXCHANGE_RATE
	ADD FOREIGN KEY (ORIGIN_CURRENCY_CODE) 
	REFERENCES CURRENCY(CURRENCY_CODE);
    
ALTER TABLE EXCHANGE_RATE
    ADD FOREIGN KEY (DEST_CURRENCY_CODE) 
    REFERENCES CURRENCY(CURRENCY_CODE);