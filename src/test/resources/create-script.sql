create table PREISREGELUNG (ID bigint not null, primary key (ID));
create table TRANCHENMODELL (ID bigint not null, PREISREGELUNG_ID bigint null, primary key (ID));
create table TRANCHE (ID bigint not null, TRANCHENMODELL_ID bigint not null, primary key (ID));

alter table TRANCHENMODELL add constraint FK_TRANCHENMODELL_PREISREGELUNG_ID foreign key (PREISREGELUNG_ID) references PREISREGELUNG;
alter table TRANCHE add constraint FK_TRANCHE_TRANCHENMODELL_ID foreign key (TRANCHENMODELL_ID) references TRANCHENMODELL;
