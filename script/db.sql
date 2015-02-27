grant all privileges on test.* to hxoa identified by "hxoa";
FLUSH PRIVILEGES;



CREATE TABLE `t_user` (
  `id` varchar(30) NOT NULL ,
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `department` varchar(15) NOT NULL,
  `last_login_time` datetime NOT NULL,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `t_user` values('userId', 'userName', 'password', 'department', now());


CREATE TABLE `t_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL COMMENT '˭��������',
  `flow_id` varchar(15) NOT NULL COMMENT '��һ������',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '��������ĵ�ǰ״̬ 0:δ��� 1:�����',
  `step_order` int(2) NOT NULL DEFAULT 0 COMMENT '�����������̵�����һ��'

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#select * from t_request where status=0 and