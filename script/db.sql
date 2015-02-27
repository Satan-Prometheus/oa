grant all privileges on test.* to hxoa identified by "hxoa";
FLUSH PRIVILEGES;



CREATE TABLE `t_user` (
  `id` varchar(30) NOT NULL,
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `department` int(11) NOT NULL,

  `last_login_time` datetime NOT NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
