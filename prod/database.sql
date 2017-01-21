CREATE SCHEMA `codingame` DEFAULT CHARACTER SET utf8mb4;
use codingame;
CREATE TABLE IF NOT EXISTS bot (
	`bot_id` bigint(20) NOT NULL auto_increment,
	`name` varchar(100) default '',
	`user_id` bigint(20) NULL,
	`date_created` datetime,
	PRIMARY KEY  (bot_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS game (
	`game_id` bigint(20) NOT NULL auto_increment,
	`name` varchar(100) default '',
	`map_id` bigint(20) NULL,
	`bot1_id` bigint(20) NULL,
	`bot2_id` bigint(20) NULL,
	`bot3_id` bigint(20) NULL,
	`bot4_id` bigint(20) NULL,
	`date_created` datetime,
	PRIMARY KEY  (game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS map (
	`map_id` bigint(20) NOT NULL auto_increment,
	`name` varchar(100) default '',
	`width` varchar(100) default '',
	`height` varchar(100) default '',
	`data` varchar(100) default '',
	`nb_somme` varchar(100) default '',
	`max_somme` varchar(100) default '',
	`min_somme` varchar(100) default '',
	`date_created` datetime,
	PRIMARY KEY  (map_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS space (
	`space_id` bigint(20) NOT NULL auto_increment,
	`tenant_id` bigint(20) NULL,
	`name` varchar(100) default '',
	`description` varchar(100) default '',
	`params` varchar(100) default '',
	`date_created` datetime,
	`date_updated` datetime,
	PRIMARY KEY  (space_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tenant (
	`tenant_id` bigint(20) NOT NULL auto_increment,
	`name` varchar(100) default '',
	`theme` varchar(100) default '',
	`quota_event` varchar(100) default '',
	`quota_user` varchar(100) default '',
	`actif` tinyint(1) NULL,
	`params` varchar(100) default '',
	`date_created` datetime,
	`date_updated` datetime,
	PRIMARY KEY  (tenant_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS turn (
	`turn_id` bigint(20) NOT NULL auto_increment,
	`index` varchar(100) default '',
	`game_id` bigint(20) NULL,
	`date_created` datetime,
	`x_bot1` varchar(100) default '',
	`y_bot1` varchar(100) default '',
	`x_bot2` varchar(100) default '',
	`y_bot2` varchar(100) default '',
	`x_bot3` varchar(100) default '',
	`y_bot3` varchar(100) default '',
	`x_bot4` varchar(100) default '',
	`y_bot4` varchar(100) default '',
	`amount_bot1` varchar(100) default '',
	`amount_bot2` varchar(100) default '',
	`amount_bot3` varchar(100) default '',
	`amount_bot4` varchar(100) default '',
	PRIMARY KEY  (turn_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
	`user_id` bigint(20) NOT NULL auto_increment,
	`tenant_id` varchar(100) default '',
	`first_name` varchar(100) default '',
	`last_name` varchar(100) default '',
	`login` varchar(100) default '',
	`password` varchar(100) default '',
	`email` varchar(100) default '',
	`date_created` datetime,
	`date_updated` datetime,
	`date_deleted` datetime,
	`date_disable` datetime,
	PRIMARY KEY  (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into codingame.user (user_id, tenant_id, first_name, last_name, login, password) values ("1","1","Guillaume","Admin","admin","admin");
