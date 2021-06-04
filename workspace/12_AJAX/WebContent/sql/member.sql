DROP TABLE BOARD;
DROP TABLE MEMBER_LOG;
DROP TABLE MEMBER;

SELECT * FROM USER_SEQUENCES;
DROP SEQUENCE BOARD_SEQ;
DROP SEQUENCE MEMBER_SEQ;
DROP SEQUENCE MEMBER_LOG_SEQ;
DROP SEQUENCE REPLY_SEQ;

CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32) NOT NULL UNIQUE,
	PW VARCHAR2(32) NOT NULL,
	NAME VARCHAR2(32),
	EMAIL VARCHAR2(100),
	PHONE VARCHAR2(15),
	REGDATE TIMESTAMP
);

CREATE SEQUENCE MEMBER_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;

INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'admin', '1111', '관리자', 'admin@home.com', '010-1111-2222', SYSTIMESTAMP);
COMMIT;