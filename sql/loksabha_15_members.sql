use knowyourcandidates;
drop table loksabha_15_531;

CREATE TABLE IF NOT exists`loksabha_15_531` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`candidateid` INT(11) NOT NULL,
	`name` VARCHAR(40) NOT NULL,
	`imageurl` VARCHAR(256) DEFAULT NULL,
	`mothername` VARCHAR(40) DEFAULT NULL,
	`fathername` VARCHAR(40) DEFAULT NULL,
	`dateofbirth` VARCHAR(20) DEFAULT NULL,
	`placeofbirth` VARCHAR(64) NOT NULL,
	`education` VARCHAR(256) DEFAULT NULL,
	`maritalstatus` VARCHAR(14) DEFAULT NULL,
	`spousename` VARCHAR(40) DEFAULT NULL,  
	`marriagedate` VARCHAR(20) DEFAULT NULL,
	`noofdaughters` VARCHAR(2) DEFAULT NULL,
	`noofsons` VARCHAR(2) DEFAULT NULL,
	`partyname` VARCHAR(64) NOT NULL,
	`profession` VARCHAR(128) DEFAULT NULL,
	`constituency` VARCHAR(128) DEFAULT NULL,
	`presentaddress` VARCHAR(256) DEFAULT NULL,
	`permanentaddress` VARCHAR(256) DEFAULT NULL,
	`emailid` VARCHAR(40) DEFAULT NULL,
	`countriesvisited` BLOB DEFAULT NULL,
	`positionsheld` BLOB DEFAULT NULL,
	`socialandculturalactivities` BLOB DEFAULT NULL,
	`favoritepasttimeandrecreation` BLOB DEFAULT NULL,
	`sportsandclubs` BLOB DEFAULT NULL,
	`literaryartsscience` BLOB DEFAULT NULL,
	`specialinterests` BLOB DEFAULT NULL,
	`otherinformation` BLOB DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

