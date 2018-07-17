Feature:Login 
@ID_01
Scenario:Login using Invalid Credentials
Given I open HRIS Application 
When I enter invalid credentials
Then invalid login text should appear
@ID_02
Scenario:Login without Credentials
Given I open HRIS Application 
When I do not enter Password
Then style attribute to be red
@ID_03
Scenario:Login using valid Credentials
Given  I open HRIS Application
When I enter valid credentials
Then check time sheet page
