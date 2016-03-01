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
   id                   bigint not null auto_increment comment 'id编号',
   name                 varchar(128) not null comment '资源名称',
   resource_type        varchar(32) not null comment '资源类型, 包括菜单、页面、操作按钮',
   resource_url         varchar(256) comment '资源url',
   target               varchar(32) comment '目标: _blank、_self、_top、_parent、frame',
   privilege            varchar(128) comment '权限点',
   icon                 varchar(64) comment '图标',
   parent               varchar(32) comment '上级资源',
   sort_no              int comment '排序, 递增顺序',
   is_show              varchar(16) comment '是否显示',
   create_time          datetime comment '创建时间',
   update_time          timestamp comment '更新时间',
   primary key (id)
);

alter table vigor_defensor_t_resource_info comment '资源信息表';

/*==============================================================*/
/* Table: vigor_defensor_t_role_info                            */
/*==============================================================*/
create table vigor_defensor_t_role_info
(
   id                   bigint not null auto_increment comment 'id编号',
   name                 varchar(128) not null comment '角色名',
   identify             varchar(32) comment '角色标记',
   creator              varchar(16) comment '创建人',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remarks              varchar(256) comment '备注',
   primary key (id)
);

alter table vigor_defensor_t_role_info comment '角色信息表';

/*==============================================================*/
/* Table: vigor_defensor_t_role_resource_rec                    */
/*==============================================================*/
create table vigor_defensor_t_role_resource_rec
(
   id                   bigint not null auto_increment comment 'id编号',
   role_id              bigint not null comment '角色',
   resource_id          bigint not null comment '资源',
   primary key (id)
);

alter table vigor_defensor_t_role_resource_rec comment '角色资源权限表';

/*==============================================================*/
/* Table: vigor_defensor_t_user_info                            */
/*==============================================================*/
create table vigor_defensor_t_user_info
(
   id                   bigint not null auto_increment comment 'id编号',
   username             varchar(128) not null comment '用户名',
   password             varchar(128) not null comment '密码',
   create_time          datetime comment '创建时间',
   primary key (id)
);

alter table vigor_defensor_t_user_info comment '用户信息表';

/*==============================================================*/
/* Table: vigor_defensor_t_user_role_rec                        */
/*==============================================================*/
create table vigor_defensor_t_user_role_rec
(
   id                   bigint not null auto_increment comment 'id编号',
   user_id              bigint not null comment '用户',
   role_id              bigint not null comment '角色',
   primary key (id)
);

alter table vigor_defensor_t_user_role_rec comment '用户角色关系记录表';

alter table vigor_defensor_t_role_resource_rec add constraint FK_Reference_10 foreign key (resource_id)
      references vigor_defensor_t_resource_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_role_resource_rec add constraint FK_Reference_3 foreign key (role_id)
      references vigor_defensor_t_role_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_user_role_rec add constraint FK_Reference_4 foreign key (role_id)
      references vigor_defensor_t_role_info (id) on delete restrict on update restrict;

alter table vigor_defensor_t_user_role_rec add constraint FK_Reference_5 foreign key (user_id)
      references vigor_defensor_t_user_info (id) on delete restrict on update restrict;

