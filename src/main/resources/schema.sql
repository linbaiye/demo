CREATE TABLE `user_loan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `application_no` varchar(255) DEFAULT NULL,
  `daily_interest_calculated_time` datetime DEFAULT NULL,
  `daily_interest_rate` decimal(4,2) DEFAULT NULL,
  `overdue_daily_interest_rate` decimal(10, 2) DEFAULT NULL,
  `principle` decimal(10, 2) DEFAULT NULL,
  `interest` decimal(4,2) DEFAULT NULL,
  `loan_amount` decimal(10, 2) DEFAULT NULL,
  `loan_term` int(11) DEFAULT NULL,
  `state` varchar(16) DEFAULT NULL,
  `started_date_time` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8