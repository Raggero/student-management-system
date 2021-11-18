# Student management system with Java EE 8

-------------------------------------------------

Simple CRUD API to handle Students

### CREATE
* Method: POST
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students`
* Student details in request body in JSON format
  * Example:
  ```yaml
  {
    "firstName": "Musse",
    "lastName": "Pig",
    "email": "musse@disney.com",
    "phoneNumber": "0701234567"
  }
  ```
* All fields are mandatory except for phoneNumber
* The same student (based on firstname, lastname and email) cannot be added twice to database
 
* Responses
  * 201 Created returns student in Response body
  * 409 Conflict returns error message in Json format
    * Message: "Student already exists" , if student already in database
    * Message: "Incomplete student information" if mandatory fields missing or empty

### READ
#### Get All Students
* Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students`
* Responses
  * 200 OK returns a list of all Students, if there are students in database
  * 200 OK returns Message: No students in database yet, if no students in database

#### Get student by id
* Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/id`
* Enter the requested students' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/students/1`
* Responses
  * 200 OK returns student in response body
  * 404 Not Found returns Message: Student with ID {id} was not found

#### Get student by lastname
* Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/search`
* Query Param: 
  * name: lastname, value: lastname of requested students
  * Example: `http://localhost:8080/student-management-system/api/v1/students/search?lastname=Pig`
* Responses
  * 200 OK returns a list of students in response body, if there are students with specified lastname in database
  * 200 OK returns Message: No students with lastname {lastname} was found in the database, if no students with specified lastname in database
  
### UPDATE
#### Update student
* Method: PUT
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students`
* Student details in request body in JSON format including id
* Example:
```yaml
{
  "id": 1,
  "firstName": "Mimmi",
  "lastName": "Pig",
  "email": "mimmi@disney.com",
  "phoneNumber": "0701234567"
}
```
* All fields are mandatory except for phoneNumber
* Responses
    * 200 OK returns updated student in response body
    * 404 Not Found returns Message: Student could not be found and therefore not updated.
      * This happens if there is no student with specified id in the database
    * 409 Conflict returns Message: Incomplete student information, if mandatory fields missing or empty


#### Update student phonenumber
* Method: PATCH
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/id`
* Enter the requested students' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/students/1`
* The students new phonenumber in request body in JSON format
* Example:
```yaml
{
  "phoneNumber": "0315555555"
}
```
* Responses
    * 200 OK returns updated student in response body
    * 404 Not Found returns Message: Student could not be found and therefore not update the phonenumber
        * This happens if there is no student with specified id in the database

### DELETE
* Method: DELETE
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/id`
* Enter the requested students' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/students/1`
* 
* Responses
    * 204 No Content if delete successful
    * 404 Not Found returns Message: "Student with id {id} was not found and therefore not deleted"
      * This happens if there is no student with specified id in the database