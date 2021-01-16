CREATE TABLE `loan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(64) unique DEFAULT NULL,
  `annual_rate` decimal(9,8) DEFAULT NULL,
  `bad_days` int(11) NOT NULL,
  `installment_term` int(11) NOT NULL,
  `started_time` datetime DEFAULT now(),
  `last_daily_calculated_time` datetime DEFAULT NULL,
  `loan_amount` decimal(21,10) DEFAULT NULL,
  `overdue_daily_rate` decimal(9,8) DEFAULT NULL,
  `overdue_days` int(11) NOT NULL,
  `state` varchar(16) DEFAULT NULL,
  `created_time` datetime DEFAULT now(),
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `installment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loan_id` bigint(20) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `installment_number` int(11) DEFAULT NULL,
  `outstanding_interest` decimal(15,8) DEFAULT NULL,
  `outstanding_principle` decimal(15,8) DEFAULT NULL,
  `created_time` datetime DEFAULT now(),
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;