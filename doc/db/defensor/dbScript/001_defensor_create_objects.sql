/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/2/18 23:15:30                           */
/*==============================================================*/


drop table if exists defensor_t_identity_info;

drop table if exists defensor_t_identity_role_rec;

drop table if exists defensor_t_resource_info;

drop table if exists defensor_t_role_info;

drop table if exists defensor_t_role_resource_rec;

drop table if exists defensor_t_user_identity_rec;

drop table if exists defensor_t_user_info;

/*==============================================================*/
/* Table: defensor_t_identity_info                              */
/*==============================================================*/
create table defensor_t_identity_info
(
   id                   bigint not null auto_increment comment 'id���',
   name                 varchar(64) not null comment '����',
   code                 varchar(32) not null comment 'Ψһ����',
   primary key (id)
);

alter table defensor_t_identity_info comment '�����Ϣ��';

/*==============================================================*/
/* Table: defensor_t_identity_role_rec                          */
/*==============================================================*/
create table defensor_t_identity_role_rec
(
   id                   bigint not null auto_increment comment 'id���',
   identity_id          bigint not null comment '���',
   role_id              bigint not null comment '��ɫ',
   primary key (id)
);

alter table defensor_t_identity_role_rec comment '��ݽ�ɫ��¼';

/*==============================================================*/
/* Table: defensor_t_resource_info                              */
/*==============================================================*/
create table defensor_t_resource_info
(
   id                   bigint not null auto_increment comment 'id���',
   name                 varchar(128) not null comment '��Դ����',
   resource_type        varchar(32) not null comment '��Դ����, �����˵���ҳ�桢������ť',
   resource_url         varchar(256) comment '��Դurl',
   target               varchar(32) comment 'Ŀ��: _blank��_self��_top��_parent��frame',
   privilege            varchar(128) comment 'Ȩ�޵�',
   icon                 varchar(64) comment 'ͼ��',
   parent               varchar(32) comment '�ϼ���Դ',
   weight               int comment 'Ȩ��: ֵԽ��, Ȩ��Խ��',
   enabled              varchar(32) comment '�Ƿ����',
   is_show              varchar(16) comment '�Ƿ���ʾ',
   create_time          datetime comment '����ʱ��',
   update_time          timestamp comment '����ʱ��',
   primary key (id)
);

alter table defensor_t_resource_info comment '��Դ��Ϣ��';

/*==============================================================*/
/* Table: defensor_t_role_info                                  */
/*==============================================================*/
create table defensor_t_role_info
(
   id                   bigint not null auto_increment comment 'id���',
   name                 varchar(128) not null comment '��ɫ��',
   creator              varchar(16) comment '������',
   create_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table defensor_t_role_info comment '��ɫ��Ϣ��';

/*==============================================================*/
/* Table: defensor_t_role_resource_rec                          */
/*==============================================================*/
create table defensor_t_role_resource_rec
(
   id                   bigint not null auto_increment comment 'id���',
   role_id              bigint not null comment '��ɫ',
   resource_id          bigint not null comment '��Դ',
   primary key (id)
);

alter table defensor_t_role_resource_rec comment '��ɫ��ԴȨ�ޱ�';

/*==============================================================*/
/* Table: defensor_t_user_identity_rec                          */
/*==============================================================*/
create table defensor_t_user_identity_rec
(
   id                   bigint not null auto_increment comment 'id���',
   user_id              bigint not null comment '�û�',
   identity_id          bigint not null comment '���',
   primary key (id)
);

alter table defensor_t_user_identity_rec comment '�û���ݹ�����';

/*==============================================================*/
/* Table: defensor_t_user_info                                  */
/*==============================================================*/
create table defensor_t_user_info
(
   id                   bigint not null auto_increment comment 'id���',
   username             varchar(128) not null comment '�û���',
   primary key (id)
);

alter table defensor_t_user_info comment '�û���Ϣ��';

alter table defensor_t_identity_role_rec add constraint FK_Reference_6 foreign key (identity_id)
      references defensor_t_identity_info (id) on delete restrict on update restrict;

alter table defensor_t_identity_role_rec add constraint FK_Reference_7 foreign key (role_id)
      references defensor_t_role_info (id) on delete restrict on update restrict;

alter table defensor_t_role_resource_rec add constraint FK_Reference_10 foreign key (resource_id)
      references defensor_t_resource_info (id) on delete restrict on update restrict;

alter table defensor_t_role_resource_rec add constraint FK_Reference_3 foreign key (role_id)
      references defensor_t_role_info (id) on delete restrict on update restrict;

alter table defensor_t_user_identity_rec add constraint FK_Reference_8 foreign key (identity_id)
      references defensor_t_identity_info (id) on delete restrict on update restrict;

alter table defensor_t_user_identity_rec add constraint FK_Reference_9 foreign key (user_id)
      references defensor_t_user_info (id) on delete restrict on update restrict;

