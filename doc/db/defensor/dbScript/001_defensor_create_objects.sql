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
   id                   bigint not null auto_increment comment 'id编号',
   name                 varchar(64) not null comment '名称',
   code                 varchar(32) not null comment '唯一编码',
   primary key (id)
);

alter table defensor_t_identity_info comment '身份信息表';

/*==============================================================*/
/* Table: defensor_t_identity_role_rec                          */
/*==============================================================*/
create table defensor_t_identity_role_rec
(
   id                   bigint not null auto_increment comment 'id编号',
   identity_id          bigint not null comment '身份',
   role_id              bigint not null comment '角色',
   primary key (id)
);

alter table defensor_t_identity_role_rec comment '身份角色记录';

/*==============================================================*/
/* Table: defensor_t_resource_info                              */
/*==============================================================*/
create table defensor_t_resource_info
(
   id                   bigint not null auto_increment comment 'id编号',
   name                 varchar(128) not null comment '资源名称',
   resource_type        varchar(32) not null comment '资源类型, 包括菜单、页面、操作按钮',
   resource_url         varchar(256) comment '资源url',
   target               varchar(32) comment '目标: _blank、_self、_top、_parent、frame',
   privilege            varchar(128) comment '权限点',
   icon                 varchar(64) comment '图标',
   parent               varchar(32) comment '上级资源',
   weight               int comment '权重: 值越大, 权重越大',
   enabled              varchar(32) comment '是否可用',
   is_show              varchar(16) comment '是否显示',
   create_time          datetime comment '创建时间',
   update_time          timestamp comment '更新时间',
   primary key (id)
);

alter table defensor_t_resource_info comment '资源信息表';

/*==============================================================*/
/* Table: defensor_t_role_info                                  */
/*==============================================================*/
create table defensor_t_role_info
(
   id                   bigint not null auto_increment comment 'id编号',
   name                 varchar(128) not null comment '角色名',
   creator              varchar(16) comment '创建人',
   create_time          datetime comment '创建时间',
   primary key (id)
);

alter table defensor_t_role_info comment '角色信息表';

/*==============================================================*/
/* Table: defensor_t_role_resource_rec                          */
/*==============================================================*/
create table defensor_t_role_resource_rec
(
   id                   bigint not null auto_increment comment 'id编号',
   role_id              bigint not null comment '角色',
   resource_id          bigint not null comment '资源',
   primary key (id)
);

alter table defensor_t_role_resource_rec comment '角色资源权限表';

/*==============================================================*/
/* Table: defensor_t_user_identity_rec                          */
/*==============================================================*/
create table defensor_t_user_identity_rec
(
   id                   bigint not null auto_increment comment 'id编号',
   user_id              bigint not null comment '用户',
   identity_id          bigint not null comment '身份',
   primary key (id)
);

alter table defensor_t_user_identity_rec comment '用户身份关联表';

/*==============================================================*/
/* Table: defensor_t_user_info                                  */
/*==============================================================*/
create table defensor_t_user_info
(
   id                   bigint not null auto_increment comment 'id编号',
   username             varchar(128) not null comment '用户名',
   primary key (id)
);

alter table defensor_t_user_info comment '用户信息表';

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

