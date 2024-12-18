-----------------------전체 삭제 질의문문-----------------------

-- 1. 제약조건 삭제
ALTER TABLE recommendation DROP CONSTRAINT R_3;
ALTER TABLE daily_userperformance DROP CONSTRAINT R_1;
ALTER TABLE task DROP CONSTRAINT R_2;
ALTER TABLE post DROP CONSTRAINT R_8;
ALTER TABLE post_tasks DROP CONSTRAINT R_5;
ALTER TABLE post_tasks DROP CONSTRAINT R_6;

-- 2. 테이블블 삭제
DROP TABLE post_tasks PURGE;
DROP TABLE post PURGE;
DROP TABLE task PURGE;
DROP TABLE daily_userperformance PURGE;
DROP TABLE recommendation PURGE;
DROP TABLE userinfo PURGE;

-- 3. 시퀀스 삭제
DROP SEQUENCE Sequence_post_task_id;
DROP SEQUENCE Sequence_postid;
DROP SEQUENCE Sequence_taskid;
DROP SEQUENCE Sequence_userid;


-----------------------생성 질의문-----------------------

CREATE SEQUENCE Sequence_post_task_id
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_postid
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_taskid
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE Sequence_userid
	INCREMENT BY 1
	START WITH 1;

CREATE TABLE userinfo
(
	user_id              INTEGER  NOT NULL ,
	name                 VARCHAR2(40)  NULL ,
	email                VARCHAR2(40)  NULL ,
	password             VARCHAR2(14)  NULL ,
	birthDate            DATE  NULL ,
	chronoType           VARCHAR2(10)  NULL  CONSTRAINT  Validation_Rule_chronoType_571481004 CHECK (chronoType IN ('MORNING', 'EVENING'))
);

CREATE UNIQUE INDEX XPKuserinfo ON userinfo
(user_id   ASC);

ALTER TABLE userinfo
	ADD CONSTRAINT  XPKuserinfo PRIMARY KEY (user_id);

CREATE TABLE recommendation
(
	morningRoutineForMorningType VARCHAR2(30)  NULL ,
	afternoonRoutineForMorningType VARCHAR2(30)  NULL ,
	eveningRoutineForMorningType VARCHAR2(30)  NULL ,
	morningRoutineForEveningType VARCHAR2(30)  NULL ,
	afternoonRoutineForEveningType VARCHAR2(30)  NULL ,
	eveningRoutineForEveningType VARCHAR2(30)  NULL ,
	user_id              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKrecommendation ON recommendation
(user_id   ASC);

ALTER TABLE recommendation
	ADD CONSTRAINT  XPKrecommendation PRIMARY KEY (user_id);

CREATE TABLE daily_userperformance
(
	performanceId        INTEGER  NOT NULL ,
	cal_date             DATE  NULL ,
	completionRate       NUMBER(5,2)  NULL  CONSTRAINT  Validation_Rule_completionRate_1030664334 CHECK (completionRate BETWEEN 0 AND 100),
	user_id              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKdaily_userperformance ON daily_userperformance
(performanceId   ASC,user_id   ASC);

ALTER TABLE daily_userperformance
	ADD CONSTRAINT  XPKdaily_userperformance PRIMARY KEY (performanceId,user_id);

CREATE TABLE task
(
	task_id              INTEGER  NOT NULL ,
	task_order           INTEGER  NULL ,
	description          VARCHAR2(120)  NULL ,
	isCompleted          SMALLINT  NULL ,
	user_id              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKtask ON task
(task_id   ASC,user_id   ASC);

ALTER TABLE task
	ADD CONSTRAINT  XPKtask PRIMARY KEY (task_id,user_id);

CREATE TABLE post
(
	post_id              INTEGER  NOT NULL ,
	title                VARCHAR2(30)  NULL ,
	author              VARCHAR2(40)  NULL ,
	create_date          DATE  NULL ,
	user_id              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKpost ON post
(post_id   ASC,user_id   ASC);

ALTER TABLE post
	ADD CONSTRAINT  XPKpost PRIMARY KEY (post_id,user_id);

CREATE TABLE post_tasks
(
	post_task_id         INTEGER  NOT NULL ,
	task_order           INTEGER  NULL ,
	description          VARCHAR2(120)  NULL ,
	post_id 			 INTEGER  NOT NULL ,
	user_id              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKpost_tasks ON post_tasks
(post_task_id   ASC,post_id   ASC,user_id   ASC);

ALTER TABLE post_tasks
	ADD CONSTRAINT  XPKpost_tasks PRIMARY KEY (post_task_id,post_id,user_id);

ALTER TABLE recommendation
	ADD (
CONSTRAINT R_3 FOREIGN KEY (user_id) REFERENCES userinfo (user_id));

ALTER TABLE daily_userperformance
	ADD (
CONSTRAINT R_1 FOREIGN KEY (user_id) REFERENCES userinfo (user_id));

ALTER TABLE task
	ADD (
CONSTRAINT R_2 FOREIGN KEY (user_id) REFERENCES userinfo (user_id));

ALTER TABLE post
	ADD (
CONSTRAINT R_8 FOREIGN KEY (user_id) REFERENCES userinfo (user_id));

ALTER TABLE post_tasks
	ADD (
CONSTRAINT R_5 FOREIGN KEY (post_id, user_id) REFERENCES post (post_id, user_id));

ALTER TABLE post_tasks
	ADD (
CONSTRAINT R_6 FOREIGN KEY (user_id) REFERENCES userinfo (user_id));

