# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table abzeichen (
  abzeichen_id                  integer auto_increment not null,
  name                          varchar(255),
  beschreibung                  varchar(255),
  bild                          varchar(255),
  constraint pk_abzeichen primary key (abzeichen_id)
);

create table freundeverzeichnis (
  freunde_verzeichnis_id        integer auto_increment not null,
  user_a_userid                 integer,
  user_b_userid                 integer,
  constraint pk_freundeverzeichnis primary key (freunde_verzeichnis_id)
);

create table level (
  level_id                      integer auto_increment not null,
  level_name                    varchar(255),
  erhaltene_efahrung            integer,
  erhaltenes_abzeichen_abzeichen_id integer,
  constraint uq_level_erhaltenes_abzeichen_abzeichen_id unique (erhaltenes_abzeichen_abzeichen_id),
  constraint pk_level primary key (level_id)
);

create table nachrichten (
  nachrichten_id                integer auto_increment not null,
  absender                      integer,
  empfaenger                    integer,
  inhalt                        varchar(255),
  gelesen                       tinyint(1) default 0,
  constraint pk_nachrichten primary key (nachrichten_id)
);

create table quizfrage (
  fragen_id                     integer auto_increment not null,
  name                          varchar(255),
  frage                         varchar(255),
  falsche_antwort1              varchar(255),
  falsche_antwort2              varchar(255),
  falsche_antwort3              varchar(255),
  richtige_antwort              varchar(255),
  aufloesung                    varchar(255),
  constraint pk_quizfrage primary key (fragen_id)
);

create table rang (
  rang_id                       integer auto_increment not null,
  bezeichnung                   varchar(255),
  xp_grenze                     integer,
  constraint pk_rang primary key (rang_id)
);

create table schritteverzeichnis (
  schritt_verzeichnis_id        integer auto_increment not null,
  schritt_id                    integer,
  level_level_id                integer,
  zutat_zutat_id                integer,
  quiz_frage_fragen_id          integer,
  constraint pk_schritteverzeichnis primary key (schritt_verzeichnis_id)
);

create table user (
  userid                        integer auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  passwort                      varchar(255),
  erfahrung                     integer,
  promille                      decimal(2,1),
  gewaehltes_abzeichen_abzeichen_id integer,
  bestandenes_level_level_id    integer,
  rang_rang_id                  integer,
  last_login                    datetime(6),
  constraint pk_user primary key (userid)
);

create table zutat (
  zutat_id                      integer auto_increment not null,
  beschreibung                  varchar(255),
  name                          varchar(255),
  bild                          varchar(255),
  constraint pk_zutat primary key (zutat_id)
);

alter table freundeverzeichnis add constraint fk_freundeverzeichnis_user_a_userid foreign key (user_a_userid) references user (userid) on delete restrict on update restrict;
create index ix_freundeverzeichnis_user_a_userid on freundeverzeichnis (user_a_userid);

alter table freundeverzeichnis add constraint fk_freundeverzeichnis_user_b_userid foreign key (user_b_userid) references user (userid) on delete restrict on update restrict;
create index ix_freundeverzeichnis_user_b_userid on freundeverzeichnis (user_b_userid);

alter table level add constraint fk_level_erhaltenes_abzeichen_abzeichen_id foreign key (erhaltenes_abzeichen_abzeichen_id) references abzeichen (abzeichen_id) on delete restrict on update restrict;

alter table schritteverzeichnis add constraint fk_schritteverzeichnis_level_level_id foreign key (level_level_id) references level (level_id) on delete restrict on update restrict;
create index ix_schritteverzeichnis_level_level_id on schritteverzeichnis (level_level_id);

alter table schritteverzeichnis add constraint fk_schritteverzeichnis_zutat_zutat_id foreign key (zutat_zutat_id) references zutat (zutat_id) on delete restrict on update restrict;
create index ix_schritteverzeichnis_zutat_zutat_id on schritteverzeichnis (zutat_zutat_id);

alter table schritteverzeichnis add constraint fk_schritteverzeichnis_quiz_frage_fragen_id foreign key (quiz_frage_fragen_id) references quizfrage (fragen_id) on delete restrict on update restrict;
create index ix_schritteverzeichnis_quiz_frage_fragen_id on schritteverzeichnis (quiz_frage_fragen_id);

alter table user add constraint fk_user_gewaehltes_abzeichen_abzeichen_id foreign key (gewaehltes_abzeichen_abzeichen_id) references abzeichen (abzeichen_id) on delete restrict on update restrict;
create index ix_user_gewaehltes_abzeichen_abzeichen_id on user (gewaehltes_abzeichen_abzeichen_id);

alter table user add constraint fk_user_bestandenes_level_level_id foreign key (bestandenes_level_level_id) references level (level_id) on delete restrict on update restrict;
create index ix_user_bestandenes_level_level_id on user (bestandenes_level_level_id);

alter table user add constraint fk_user_rang_rang_id foreign key (rang_rang_id) references rang (rang_id) on delete restrict on update restrict;
create index ix_user_rang_rang_id on user (rang_rang_id);


# --- !Downs

alter table freundeverzeichnis drop foreign key fk_freundeverzeichnis_user_a_userid;
drop index ix_freundeverzeichnis_user_a_userid on freundeverzeichnis;

alter table freundeverzeichnis drop foreign key fk_freundeverzeichnis_user_b_userid;
drop index ix_freundeverzeichnis_user_b_userid on freundeverzeichnis;

alter table level drop foreign key fk_level_erhaltenes_abzeichen_abzeichen_id;

alter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_level_level_id;
drop index ix_schritteverzeichnis_level_level_id on schritteverzeichnis;

alter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_zutat_zutat_id;
drop index ix_schritteverzeichnis_zutat_zutat_id on schritteverzeichnis;

alter table schritteverzeichnis drop foreign key fk_schritteverzeichnis_quiz_frage_fragen_id;
drop index ix_schritteverzeichnis_quiz_frage_fragen_id on schritteverzeichnis;

alter table user drop foreign key fk_user_gewaehltes_abzeichen_abzeichen_id;
drop index ix_user_gewaehltes_abzeichen_abzeichen_id on user;

alter table user drop foreign key fk_user_bestandenes_level_level_id;
drop index ix_user_bestandenes_level_level_id on user;

alter table user drop foreign key fk_user_rang_rang_id;
drop index ix_user_rang_rang_id on user;

drop table if exists abzeichen;

drop table if exists freundeverzeichnis;

drop table if exists level;

drop table if exists nachrichten;

drop table if exists quizfrage;

drop table if exists rang;

drop table if exists schritteverzeichnis;

drop table if exists user;

drop table if exists zutat;

