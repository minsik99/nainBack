DROP TABLE TB_NOTICE CASCADE CONSTRAINTS;

CREATE TABLE TB_NOTICE (
NOTICE_NO NUMBER,
NOTICE_TITLE VARCHAR2(200) NOT NULL,
NOTICE_DATE DATE DEFAULT SYSDATE NOT NULL,
NOTICE_MODIFY DATE,
NOTICE_DELETE DATE,
MEMBER_NO NUMBER NOT NULL,
NOTICE_FILE VARCHAR2(100),
NOTICE_MFILE VARCHAR2(100),
NOTICE_CONTENT VARCHAR2(1000) NOT NULL,
NOTICE_READCOUNT NUMBER DEFAULT 0,
NOTICE_IMPORTENT DATE,
CONSTRAINT NOTICE_PK_NOTICE_NO PRIMARY KEY (NOTICE_NO),
CONSTRAINT NOTICE_FK_MEMBER_NO FOREIGN KEY (MEMBER_NO) REFERENCES TB_MEMBER (MEMBER_NO)
);

COMMENT ON COLUMN TB_NOTICE.NOTICE_NO IS '공지글 번호';
COMMENT ON COLUMN TB_NOTICE.NOTICE_TITLE IS '공지글 제목';
COMMENT ON COLUMN TB_NOTICE.NOTICE_DATE IS '등록 날짜';
COMMENT ON COLUMN TB_NOTICE.NOTICE_MODIFY IS '수정 날짜';
COMMENT ON COLUMN TB_NOTICE.NOTICE_DELETE IS '삭제 날짜';
COMMENT ON COLUMN TB_NOTICE.MEMBER_NO IS '회원 번호';
COMMENT ON COLUMN TB_NOTICE.NOTICE_FILE IS '파일 업로드명';
COMMENT ON COLUMN TB_NOTICE.NOTICE_MFILE IS '파일 수정명';
COMMENT ON COLUMN TB_NOTICE.MEMBER_NO IS '회원 번호';
COMMENT ON COLUMN TB_NOTICE.NOTICE_CONTENT IS '내용';
COMMENT ON COLUMN TB_NOTICE.NOTICE_READCOUNT IS '조회수';
COMMENT ON COLUMN TB_NOTICE.NOTICE_IMPORTENT IS '중요도 표시기한';

SET DEFINE OFF;

CREATE SEQUENCE NOTICE_NO_SEQ
START WITH 1
INCREMENT BY 1
MAXVALUE 50;

Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (1,'NAIN에 방문하신 걸 환영합니다.',to_date('24/06/19','RR/MM/DD'),null,null,12,null,null,'앞으로 중요한 소식들은 여기서 알려드리도록 하겠습니다. 감사합니다',9,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (2,'구직자 맞춤형 추천 채용 정보 서비스',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>안녕하세요, 여러분의 이력서와 관심사를 분석하여 맞춤형 채용 정보를 추천해드리는 서비스를 시작했습니다. 맞춤형 채용 정보를 통해 더 빠르고 효과적으로 취업에 성공하세요.</p>',6,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (3,'새로운 관리자 분들을 소개합니다.',to_date('24/06/25','RR/MM/DD'),null,null,12,null,null,'저를 도와 줄 도토리, 문어발 회원입니다. 앞으로 이 두분들을 통해 소식을 전해드리겠습니다.',3,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (4,'전문 직업 상담 서비스 시작',to_date('24/06/24','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>안녕하세요, 구직자 여러분을 위해 전문 직업 상담 서비스를 시작합니다. 경력 상담, 면접 준비, 이력서 작성 등 다양한 상담을 제공하오니, 많은 이용 부탁드립니다.</p>',7,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (5,'취업 성공 사례 공유 이벤트',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>회원님, 저희 사이트를 통해 취업에 성공하신 회원님들의 성공 사례를 공유해 주세요. 선정된 분들께는 특별한 선물을 드립니다. 많은 참여 부탁드립니다.</p>',13,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (6,'자격증 취득 지원 프로그램 안내',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>안녕하세요, 구직자 여러분의 경쟁력을 높이기 위해 자격증 취득 지원 프로그램을 시작합니다. 다양한 자격증 과정에 대한 정보를 제공하며, 일부 과정은 할인된 가격에 수강하실 수 있습니다.</p><p>&nbsp;</p>',44,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (7,'회원 프로필 업데이트 권장',to_date('24/06/23','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>회원 여러분, 채용 담당자들이 최신 정보를 확인할 수 있도록 회원님의 프로필을 업데이트해 주세요. 최신 경력과 스킬을 추가하여 더 많은 기회를 얻으시기 바랍니다.</p>',31,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (8,'온라인 채용 박람회 개최 안내',to_date('24/06/24','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>안녕하세요 회원님, 저희 구직사이트에서 7월 20일부터 7월 22일까지 온라인 채용 박람회를 개최합니다. 다양한 기업의 채용 정보를 한눈에 확인하고, 직접 화상 면접을 진행할 수 있는 기회를 놓치지 마세요.</p>',15,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (9,'경력직 채용 공고 대량 업데이트',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>구직자 여러분, 다양한 산업 분야의 경력직 채용 공고가 대량으로 업데이트되었습니다. 최신 채용 정보를 확인하고, 원하는 직무에 빠르게 지원해보세요. 채용 공고는 채용 게시판에서 확인하실 수 있습니다.</p>',29,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (10,'AI 이력서 분석 서비스 도입 안내',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,1,null,null,'<p>안녕하세요 회원님, 저희 구직사이트에 AI 이력서 분석 서비스가 새롭게 도입되었습니다. 이 서비스는 여러분의 이력서를 분석하여 더욱 효과적인 취업 전략을 제안합니다. 많은 이용 부탁드립니다. 감사합니다.</p>',25,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (11,'구직자 설문 조사 참여 요청',to_date('24/06/25','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>안녕하세요, 구직자 여러분의 소중한 의견을 듣고자 설문 조사를 진행하고 있습니다. 설문에 참여해주시면 추첨을 통해 소정의 상품을 드립니다. 많은 참여 부탁드립니다.</p>',61,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (12,'페이징테스트2',to_date('24/06/25','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),3,null,null,'<p>테스트공지사항3???dddddd</p>',37,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (13,'등록될듯',to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),3,null,null,'<p>ㅇㅇㅇㅇ???</p>',15,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (14,'새로작성',to_date('24/06/28','RR/MM/DD'),null,to_date('24/06/28','RR/MM/DD'),3,null,null,'<p>ㅇㅇㅇ</p>',15,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (15,'등록되지?',to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>ㅇㅇㅇ</p>',48,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (16,'등록되지?',to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),to_date('24/06/28','RR/MM/DD'),3,null,null,'<p>ㅇㅇㅇ</p>',55,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (17,'등록해서 삭제할 거',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>등록합니다</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>',6,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (18,'신규생성',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>테스트</p>',5,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (19,'서비스 이용약관 변경 안내',to_date('24/07/01','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>회원님, 저희 서비스의 이용약관이 10월 1일부터 변경될 예정입니다. 변경된 약관은 홈페이지에서 확인하실 수 있습니다. 이용에 참고해주시기 바랍니다.</p>',100, null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (20,'새로운 면접 준비 가이드 제공',to_date('24/07/01','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,'이력서 작성양식.txt','20240705105917.txt','<p>회원 여러분, 성공적인 면접을 위해 새로운 면접 준비 가이드를 제공하게 되었습니다. 가이드는 첨부파일에서 다운로드하실 수 있으며, 많은 활용 부탁드립니다.</p>',158,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (21,'ㅂㄷㄼㄷㄹ',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>ㅂㄷㄼㄷㄼㄷㄼㄷ</p>',2,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (22,'ㅂㄷㅅㅂㄷㅀㅂㄷㄹㄹ',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>ㅂㄷㄼㄷㄼㄷㄼㄷㄹ</p>',2,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (23,'ㅂㄷㄼㄷㄼㄷㄹ',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>ㅂㄷㄼㄷㄼㄷㄼㄷㄼㄹㄷㅂㄼㄷㄹ</p>',8,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (24,'ㅂㄷㄼㄷㄹ',to_date('24/07/01','RR/MM/DD'),null,to_date('24/07/01','RR/MM/DD'),3,null,null,'<p>ㅂㄷㄼㄷㄹ</p>',13,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (25,'신입 공채 정보 업데이트',to_date('24/07/01','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>구직자 여러분, 최신 신입 공채 정보를 업데이트하였습니다. 새로운 공채 정보는 기업공고 게시판에서 확인하실 수 있으며, 많은 지원 부탁드립니다.</p>',102,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (26,'ㅂㄷㄼㄷㄹ',to_date('24/07/02','RR/MM/DD'),to_date('24/07/04','RR/MM/DD'),to_date('24/07/04','RR/MM/DD'),3,null,null,'<p>ㅂㄷㄼㄷㄼㄷㄼ</p>',75,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (27,'이력서 작성 가이드 업데이트',to_date('24/07/02','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>회원 여러분, 구직 활동에 도움을 드리고자 이력서 작성 가이드를 업데이트하였습니다. 새로운 가이드는 다음 글에서 확인하실 수 있습니다.</p>',80,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (28,'여름 휴가 기간 안내',to_date('24/07/02','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>안녕하세요, 저희 회사의 여름 휴가 기간은 8월 1일부터 8월 7일까지입니다. 이 기간 동안 고객 지원 서비스가 제한적으로 운영됩니다. 불편을 끼쳐드려 죄송합니다.</p>',72, null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (29,'등록테스트',to_date('24/07/03','RR/MM/DD'),null,to_date('24/07/03','RR/MM/DD'),3,null,null,'<p>ㄷㅂㄼㄷㄹ</p>',27,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (30,'eqfqef',to_date('24/07/04','RR/MM/DD'),to_date('24/07/04','RR/MM/DD'),to_date('24/07/04','RR/MM/DD'),3,null,null,'<p>qefqefqef</p>',8, null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (31,'개인정보 처리방침 변경 안내',to_date('24/07/04','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>안녕하세요, 저희 서비스의 개인정보 처리방침이 9월 1일자로 변경될 예정입니다. 변경된 사항에 대한 자세한 내용은 홈페이지를 참조해주시기 바랍니다.</p>',27,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (32,'qefqefq',to_date('24/07/04','RR/MM/DD'),null,to_date('24/07/04','RR/MM/DD'),3,null,null,'<p>qefqefqef</p>',2,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (33,'eqfqefqef',to_date('24/07/04','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),3,null,'20240705092620.png','<p>qefqefqefqefqef</p>',43,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (34,'서비스 점검 안내',to_date('24/07/04','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>안녕하세요 고객님, 7월 10일 오전 2시부터 4시까지 시스템 점검이 예정되어 있습니다. 점검 시간 동안 서비스 이용이 일시적으로 중단될 예정이오니, 양해 부탁드립니다. 감사합니다.</p>',13,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (35,'123123',to_date('24/07/05','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),3,null,'20240705092925.png','<p>12312312</p>',7,null);
Insert into c##nain.TB_NOTICE (NOTICE_NO,NOTICE_TITLE,NOTICE_DATE,NOTICE_MODIFY,NOTICE_DELETE,MEMBER_NO,NOTICE_FILE,NOTICE_MFILE,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_IMPORTENT) values (36,'포트폴리오 보고서 작성 샘플',to_date('24/07/05','RR/MM/DD'),to_date('24/07/05','RR/MM/DD'),null,3,null,null,'<p>보고서 작성 샘플입니다.</p><p>잘 마무리 하셔서 다들 좋은 곳에 취업을 하시길 기원합니다!</p><p>좋은 결과 있을 거에요 ^^</p>',3,null);

commit;
