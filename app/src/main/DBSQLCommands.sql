CREATE TABLE Record (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	DATE	 INTEGER	 NOT NULL
);

CREATE TABLE Barometer (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	Pressure REAL			 NOT NULL,
	Temperature REAL		 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE Hygrometer (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	RelativeHumidity REAL	 NOT NULL,
	Temperature		 REAL	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE IRTemperature (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	ObjectTemperature REAL	 NOT NULL,
	AmbientTemperature REAL	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE Movement (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	Acc_X	 REAL			 NOT NULL,
	Acc_Y	 REAL			 NOT NULL,
	Acc_Z	 REAL			 NOT NULL,
	Gyr_X	 REAL			 NOT NULL,
	Gyr_Y	 REAL			 NOT NULL,
	Gyr_Z	 REAL			 NOT NULL,
	Mag_X	 REAL			 NOT NULL,
	Mag_Y	 REAL			 NOT NULL,
	Mag_Z	 REAL			 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE OpticalSensor (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	Intensity REAL	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE SensorSoftware (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	SystemID 		TEXT	 NOT NULL,
	ModelNumber 	TEXT	 NOT NULL,
	SerialNumber 	TEXT	 NOT NULL,
	FirmwareRevision TEXT 	 NOT NULL,
	SoftwareRevision TEXT	 NOT NULL,
	ManufacturerName TEXT	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE BarometerParam (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	NotifyInterval	INTEGER	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE HygrometerParam (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	NotifyInterval	INTEGER	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE IRTemperatureParam (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	NotifyInterval	INTEGER	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE MovementParam (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	NotifyInterval	INTEGER	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);

CREATE TABLE OpticalSensorParam (
	_id		 INTEGER		 PRIMARY KEY		 AUTOINCREMENT,
	ID_RECORD INTEGER		 NOT NULL,
	NotifyInterval	INTEGER	 NOT NULL,
	FOREIGN KEY( ID_RECORD ) REFERENCES Record( _id )
);
