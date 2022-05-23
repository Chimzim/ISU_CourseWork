use `sdmay22-42`;

DROP TABLE IF EXISTS sampleCollection;
CREATE TABLE `sampleCollection`(
`Month` int(1) Not Null,
`Day` int(1) Not Null,
`Year` int(2) Not Null,
`sampleID` int Not Null unique,
`sensor1_CO2` double Not Null,
`sensor2_CO2` double Not Null,
`sensor3_CO2` double Not Null,
`sensor4_CO2` double Not Null,
`sensor5_CO2` double Not Null,
`longitude` double Not Null,
`latitude` double Not Null,
Primary Key(`Month`, `Day`, `Year`, `sampleID`),
Foreign Key(`sampleID`) references `sampleSite` (`id`)
);