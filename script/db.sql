grant all privileges on test.* to hxoa identified by "hxoa";
FLUSH PRIVILEGES;



CREATE TABLE `t_user` (
  `id` varchar(30) NOT NULL ,
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `department` varchar(15) NOT NULL,
  `level` int(2) NOT NULL,
  `last_login_time` datetime NOT NULL,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `t_user` values('userId', 'userName', 'password', 'department', now());


CREATE TABLE `t_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL COMMENT '谁发的请求',
  `flow_id` varchar(15) NOT NULL COMMENT '哪一种流程',
  `step_order` int(2) NOT NULL DEFAULT 0 COMMENT '本次请求流程到达哪一步',

  `request_type` varchar(64) NOT NULL COMMENT '申请类型',
  `request_detail_json` varchar(1024) NOT NULL DEFAULT '' COMMENT '用json形式存储的申请具体内容',

  `approve` int(1) NOT NULL DEFAULT 0 COMMENT '最后审批状态 0:未完成 1:reject 2:agree',

  `last_update_time` datetime NOT NULL DEFAULT "0000-00-00 00:00:00" COMMENT '最后一次状态更新的时间',

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `t_request` values(null, 'user_id', 'flow_id', 1, 'request_type', 'request_detail_json', 0, now());

#select * from t_request where status=0 and