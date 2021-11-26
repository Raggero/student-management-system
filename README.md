# Student management system with Java EE 8

-------------------------------------------------
## JAVA EE JPA LAB 2

The database is filled with students, subjects and teachers when application starts.
* A student can have several subjects
* A teacher can have several subjects
* A subject can have several students but only one teacher

To see the setup and relations use the following endpoints:

#### Get all subjects
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects`
* Responses
  * 200 OK returns a list of all Subjects, with students and teacher.

#### Get subject by id
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects/{id}`
* Enter the requested subjects' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/subjects/2`
* Responses
  * 200 OK returns the specified Subject, with students and teacher.

#### Get subject by name
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects/search`
* Query Param:
  * name: name, value: name of requested subject
* Example: `http://localhost:8080/student-management-system/api/v1/subjects/search?name=math`
* The database has the following subjects at startup: math, swedish, english and biology.
* Responses
  * 200 OK returns the specified Subject, with students and teacher.

---------------------------------------------------------------------
To create new subject and add students and teacher to subject:

#### Create subject
* Method: POST
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects`
* Subject details in request body in JSON format
* Example:
```yaml
{
  "name": "art"
}
```
* Responses
  * 201 Created returns subject in Response body.

#### Create teacher
* Method: POST
* Endpoint: `http://localhost:8080/student-management-system/api/v1/teachers`
* Teacher details in request body in JSON format
* Example:
```yaml
{
  "firstName": "Teacher",
  "lastName": "Anka",
  "email": "anka@school.com"
}
```
* Responses
  * 201 Created returns subject in Response body

#### Add teacher to subject
* Method: PATCH
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects/{subjectId}/teacher/{teacherId}`
* Example: `http://localhost:8080/student-management-system/api/v1/subjects/14/teacher/15`
* You can also add pre-existing teacher to this subject (don't have to create new above)
* A subject can only have one teacher, so executing this endpoint with other teacherId will overwrite previous teacher.
* To find all teachers see further down - Get all teachers
* Responses
  * 200 OK returns updated subject in response body


#### Add student to subject
* Method: PATCH
* Endpoint: `http://localhost:8080/student-management-system/api/v1/subjects/{subjectId}/student/{studentId}`
* Example: `http://localhost:8080/student-management-system/api/v1/subjects/14/student/1`
* You can add a pre-existing student or create then add a new student. 
* To create or find student see further down: Java EE Lab 1 - Create Student or - Get all students.
* A subject can have many students, so executing this endpoint with other studentId will add that student.
* Responses
  * 200 OK returns updated subject in response body

-------------------------------------------------------------------------------------

Complimentary endpoints:

#### Get all teachers
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/teachers`
* Responses
  * 200 OK returns a list of all Teachers.

#### Get teacher by id
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/teachers/{id}`
* Enter the requested teachers' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/teachers/11`
* Responses
  * 200 OK returns the specified Teacher.

#### Get subjects of teacher by id
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/teachers/{id}/getsubjects`
* Enter the requested teachers' id in the url path
* Example:  `http://localhost:8080/student-management-system/api/v1/teachers/11/getsubjects`
* Responses
  * 200 OK returns the specified Teachers' subjects (with complete information).

#### Get all subjects of student by id
Method: GET
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/{id}/getsubjects`
* Enter the requested students' id in the url path
* Example:  `http://localhost:8080/student-management-system/api/v1/students/1/getsubjects`
* Responses
  * 200 OK returns the specified Students' subjects (with complete information).

  
## JAVA EE Lab 1:

### Endpoints

#### Create Student
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

#### Delete student
* Method: DELETE
* Endpoint: `http://localhost:8080/student-management-system/api/v1/students/id`
* Enter the requested students' id in the url path
  * Example:  `http://localhost:8080/student-management-system/api/v1/students/1`
* 
* Responses
    * 204 No Content if delete successful
    * 404 Not Found returns Message: "Student with id {id} was not found and therefore not deleted"
      * This happens if there is no student with specified id in the database