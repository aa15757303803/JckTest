-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE maven;
-- 使用数据库
use maven;
-- 创建表
CREATE TABLE user (
'id' bigint NOT NULL AUTO_INCREMENT  COMMENT '主键',
'username' varchar(100) NOT NULL COMMENT '名称',
'password' varchar(100) NOT NULL COMMENT '密码'
PRIMARY  KEY (id)
) ENGINE =InnoDB AUTO_INCREMENT = 1000 DEFAULT  CHARSET = utf8 COMMENT='用户表';

-- 插入数据
insert  into user(username,password) values ('张三','123');
insert  into user(username,password) values ('李四','123');
insert  into user(username,password) values ('王五','123');