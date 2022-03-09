# Spring-Data-Envers

In any application, auditing means that we track and log every change in all business objects, i.e, track each insert, update and delete operations.
Basically it involves tracking three things
  1. What operation was performed?
  2. Who did it?
  3. When was it done?

Auditing helps us in maintaining history records, which can later help us in tracking user activities.

# Diagram
![image](https://user-images.githubusercontent.com/79688972/157378638-8d790120-76ce-431a-96e4-08657e93a2dc.png)

# Process to get the project up and running 
1. Clone the project on to your local system 
   ````
   git clone https://github.com/surya-sukumar/Spring-Data-Envers.git
   ````
2. Open any JDE ( Intellij / Eclipse )
3. Start the project on terminal using
   ````
   gradle bootRun
   ````
   The project will be up and running at http://localhost:8080/. In order to view the database visit http://localhost:8080/h2-console/ and click on connect.
   
   ![image](https://user-images.githubusercontent.com/79688972/157380306-3e7c288a-a292-487b-b394-35de8dff354f.png)

4. Use any REST client ( Postman ) to access the endpoints.
   ````
   GET - /user - returns a list of all users.
   POST - /user - used to create a user.
   PUT - /user/{id} - used to update information of user with id "id".
   DELETE - /user/{id} - used to delete the user with id "id".
   ````
   
   ![image](https://user-images.githubusercontent.com/79688972/157380595-44095735-6d0b-47d0-a54d-cb9f5d5c35ee.png)
   
   ![image](https://user-images.githubusercontent.com/79688972/157380766-6ab44a73-62f4-4f02-8394-a9cf931eb095.png)

6. You can view the data in the database on connecting to http://localhost:8080/h2-console/ -

   ![image](https://user-images.githubusercontent.com/79688972/157380983-69ae300e-95a5-49ca-904a-2b0106f4933e.png)
   
7. Any INSERT ( REVTYPE - 0 ), UPDATE ( REVTYPE - 1 ) or DELETE ( REVTYPE - 2 ) is recorded in the USER_AUD table -

   ![image](https://user-images.githubusercontent.com/79688972/157381368-8ba87919-aabd-4374-8dee-d4c7afb7005b.png)
   
9. You can also fetch what changes were done to the table using the LAG() sql function -

   ![image](https://user-images.githubusercontent.com/79688972/157381818-d17b3871-94ab-45c6-a87b-5bd194df574f.png)

