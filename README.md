Query text file with servlet based API  As a Product Owner i would like to have Servlet WEB API that will 
allow me to query/filter information from text files over web so that

● Any person would have access to file content

● I can do basic query/filter operations online without using text editor or grep/awk/  
Basically you API should read text file from file system and apply filtering/querying logic to produce response. 
Acceptance criteria: 1. API response content type should be JSON 2. API should have following query params:

● limit​: integer which represents max number of chars in text that API should return. If parameter 
is blank or missing return max 10000 chars. 

● q​: string which represents text to search in file, i.e. if it q=java - API should return all strings which equals to ‘java’ or 
containing it. If q is blank or missing - API should return all text from file 

● length​: integer which represents max string length. API should return string which doesn’t exceed that number or if there is no such strings empty response. If parameter is blank or missing ignore it. 
Example API call/response: 
 
http://localhost/textfileAPI?q=Java&limit=1000&length=5&includeMetaData=true 
 
Such call should return all strings that contains ‘Java’ with length <= 5 or trimmed to 5 chars with total response length <= 1000, longer response should be trimmed.  Additionally metadata will be included. Example JSON response: 
 
{   "text": [ "Java ", 
"\"Java",
"Java " "The o" 
"The l"   
] }
