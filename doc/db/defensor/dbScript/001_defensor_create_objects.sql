/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/2/26 22:19:24                           */
/*==============================================================*/


drop table if exists vigor_defensor_t_resource_info;

drop table if exists vigor_defensor_t_role_info;

drop table if exists vigor_defensor_t_role_resource_rec;

drop table if exists vigor_defensor_t_user_info;

drop table if exists vigor_defensor_t_user_role_rec;

/*==============================================================*/
/* Table: vigor_defensor_t_resource_info                        */
/*==============================================================*/
create table vigor_defensor_t_resource_info
(
   id                   bigint not null auto_increment comment 'id���',
   name                 varchar(128) not null comment '��Դ����',
   resource_type        varchar(32) not null comment '��Դ����, �����˵���ҳ�桢������ť',
   resource_url         varchar(256) comment '��Դurl',
   target               varchar(32) comment 'Ŀ��: _blank��_self��_top��_parent��frame',
   privilege            varchar(128) comment 'Ȩ�޵�',
   icon                 varchar(64) comment 'ͼ��',
   parent               varchar(32) comment '�ϼ���Դ',
   sort_no              int comment '����, ����˳��',
   is_show              varchar(16) comment '�Ƿ���ʾ',
   create_time          datetime comment '����ʱ��',
   update_time          timestamp comment '����ʱ��',
   primary key (id)
);

alter table vigor_defensor_t_resource_info comment '��Դ��Ϣ��';

/*==============================================================*/
/* Table: vigor_defensor_t_role_info                            */
/*==============================================================*/
create table vigor_defensor_t_role_info
(
   id                   bigint not null auto_increment comment 'id���',
   name                 varchar(128) not null comment '��ɫ��',
   identify             varchar(32) comment '��ɫ���',
   creator              varchar(16) comment '������',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   remarks              varchar(256) comment '��ע',
   primary key (id)
);

alter table vigor_defensor_t_role_info comment '��ɫ��Ϣ��';

/*==============================================================*/
/* Table: vigor_defensor_t_role_resource_rec                    */
/*==============================================================*/
create table vigor_defensor_t_role_resource_rec
(
   id                   bigint not null auto_increment comment 'id���',
   role_id              bigint not null comment '��ɫ',
   resource_id          bigint not null comment '��Դ',
   primary key (id)
);

alter table vigor_defensor_t_role_resource_rec comment '��ɫ��ԴȨ�ޱ�';

/*==============================================================*/
/* Table: vigor_defensor_t_user_info                            */
/*==============================================================*/
create table vigor_defensor_t_user_info
(
   id                   bigint not null auto_increment comment 'id���',
   username             varchar(128) not null comment '�û���',
   password             varchar(128) not null comment '����',
   create_time          datetime comment '����ʱ��',
   primary key (id)
);

alter table vigor_defensor_t_user_info comment '�û���Ϣ��';

/*==============================================================*/
/* Table: vigor_defensor_t_user_role_rec                        */
/*==============================================================*/
create table vigor_defensor_t_user_role_rec
(
   id                   bigint not null auto_increment comment 'id���',
   user_id              bigint not null comment '�û�',
   role_id              bigint not null comment '��ɫ',
   primary key (id)
);

alter table vigor_defensor_t_user_role_rec comment '�û���ɫ��ϵ��¼��';

alter table vigor_defensor_t_role_resource_rec add constraint FK_Reference_10 foreign key (resource_id)
      references vigor_defensor_t_resource_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_role_resource_rec add constraint FK_Reference_3 foreign key (role_id)
      references vigor_defensor_t_role_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_user_role_rec add constraint FK_Reference_4 foreign key (role_id)
      references vigor_defensor_t_role_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_user_role_rec add constraint FK_Reference_5 foreign key (user_id)
      references vigor_defensor_t_user_info (id) on delete restrict on update restrict;

