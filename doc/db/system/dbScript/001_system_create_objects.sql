/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/9 17:02:00                            */
/*==============================================================*/


drop table if exists vigor_system_t_dict_info;

drop table if exists vigor_system_t_dict_item_rec;

/*==============================================================*/
/* Table: vigor_system_t_dict_info                              */
/*==============================================================*/
create table vigor_system_t_dict_info
(
   id                   bigint not null auto_increment comment 'id���',
   dict_key             varchar(32) not null comment '�ֵ��',
   name                 varchar(64) comment '����',
   primary key (id)
);

alter table vigor_system_t_dict_info comment '�ֵ���Ϣ��';

/*==============================================================*/
/* Table: vigor_system_t_dict_item_rec                          */
/*==============================================================*/
create table vigor_system_t_dict_item_rec
(
   id                   bigint not null comment 'id���',
   code                 varchar(32) not null comment '����',
   value                varchar(128) comment '�ֵ�ֵ',
   dict_id              bigint not null comment '�����ֵ�',
   primary key (id)
);

alter table vigor_system_t_dict_item_rec comment '�ֵ����¼��';

alter table vigor_system_t_dict_item_rec add constraint FK_Reference_1 foreign key (dict_id)
      references vigor_system_t_dict_info (id) on delete restrict on update restrict;

