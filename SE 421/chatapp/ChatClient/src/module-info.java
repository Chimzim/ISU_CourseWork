module chatapp {
	exports application.database;
	exports application;
	exports application.chatboard;
	exports application.signin;

	requires java.sql;
}