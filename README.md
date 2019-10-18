The method testExceptionInInputFolderToTestSedaRoute() is failing because 
NotificationBuilder is not working as expected.

To run the NotificationBUilder test method:
1. Clone the project
2. Comment the @Ignore line on top of testExceptionInInputFolderToTestSedaRoute() 
	test method in CamelDemoRouteTest.java
3. Test by issuing the command "mvn test"