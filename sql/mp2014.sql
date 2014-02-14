create database knowyourcandidates;

use knowyourcandidates;

drop table MP2014;
CREATE TABLE if not exists `MP2014` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `dob` varchar(12) NOT null,
  `party_name` varchar(64) NOT NULL,
  `constituency` varchar(128) NULL,
  `permanent_address` varchar(256) not null,
  `permanent_telephone1` varchar(32)  null,
  `permanent_telephone2` varchar(32)  null,
  `permanent_mobile` varchar(32)  null,
  `delhi_address` varchar(256) not null,
  `telephone` varchar(20) not null,
  `fax` varchar(20) null,
  `mobile_no` varchar(12) not null,
  `email` varchar(50) not null,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO knowyourcandidates.mp2014
(name, dob, party_name, constituency, permanent_address, permanent_telephone1, permanent_telephone2, permanent_mobile, delhi_address, telephone, fax, mobile_no, email) 
VALUES ('Aaroon Rasheed,Shri J.M.', '5/13/1950', 'Indian National Congress', 'Theni (Tamil Nadu )', '(i) 7, VRC Road, Street - I,Teynampet, Chennai - 600 002 Tamil Nadu', '(044) 28156283', '', '09444048611', '3, Mahadev Road,, New Delhi - 110 001', '(011) 23355101', '(011) 23355102', '9868180133', 'jm.aaronrasheed@sansad.nic.in');


INSERT INTO knowyourcandidates.mp2014
(name, dob, party_name, constituency, permanent_address, permanent_telephone1, permanent_telephone2, permanent_mobile, delhi_address, telephone, fax, mobile_no, email) 
VALUES ('Abdul Rahman,Shri', '5/28/1959', 'Dravida Munnetra Kazhagam', 'Vellore (Tamil Nadu )', 'No. 6, Scheme Road, Silver Scheme Building,,Mahalingapuram,Chennai - 600 034 Tamil Nadu', ' (044) 28171786', '', '09442178655', '189, North Avenue,, New Delhi - 110 001', '(011) 23093029', '', '9013180160', 'rahmanexec@yahoo.com');

