#### Environment variables:  
  JDBC_DATABASE_URL  
  JDBC_DATABASE_USERNAME  
  JDBC_DATABASE_PASSWORD  
  
#### Endpoint examples:
- GET
  - /http-status/getStatusDescription?statusCode=300
 
  - /squares/getNumberOfSquares  
  [  
  &nbsp;&nbsp;{"x": 2, "y": 2},  
  &nbsp;&nbsp;{"x": 2, "y": 3},  
  &nbsp;&nbsp;{"x": 3, "y": 3},  
  &nbsp;&nbsp;{"x": 3, "y": 2},  
  &nbsp;&nbsp;{"x": 4, "y": 2},  
  &nbsp;&nbsp;{"x": 4, "y": 3},  
  &nbsp;&nbsp;{"x": 4, "y": 4},  
  &nbsp;&nbsp;{"x": 2, "y": 4},  
  &nbsp;&nbsp;{"x": 3, "y": 4}  
  ]  
  - /number-of-steps/getNumberOfSteps?numberOfStair=3&stepSizeList=1,2,3  
  - /api/location
  - /api/equipment
  - /api/employee
- POST
  - /api/location  
  {  
  &nbsp;&nbsp;"name": "Budapest",  
  &nbsp;&nbsp;"address": "Kossuth u. 4"  
  }
  - /api/equipment  
  {  
  &nbsp;&nbsp;"name": "Cook's delight",  
  &nbsp;&nbsp;"type": "OVEN",  
  &nbsp;&nbsp;"locatedat": 1  
  }
  - /api/employee  
  {  
  &nbsp;&nbsp;"name": "Jane Doe",  
  &nbsp;&nbsp;"job": "COOK",  
  &nbsp;&nbsp;"worksat": 1,  
  &nbsp;&nbsp;"operates": 2,  
  &nbsp;&nbsp;"salary": 450  
  }
- PUT
  - /api/location/1  
  {  
  &nbsp;&nbsp;"name": "Kukutyin",  
  &nbsp;&nbsp;"address": "Kiss u. 4"  
  }
  - /api/equipment/2  
  {  
  &nbsp;&nbsp;"name": "A regular oven",  
  &nbsp;&nbsp;"type": "OVEN",  
  &nbsp;&nbsp;"locatedat": 1  
  }  
  - /api/employee/3  
  {  
  &nbsp;&nbsp;"name": "Petra, the manager",  
  &nbsp;&nbsp;"job": "MANAGER",  
  &nbsp;&nbsp;"worksat": 1,  
  &nbsp;&nbsp;"operates": null,  
  &nbsp;&nbsp;"salary": 450  
  }  
- DELETE
  - /api/employee/3
  - /api/equipment/2
  - /api/location/1
  
