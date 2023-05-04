# Report:

5/03 Meeting Agenda:
Review Testing, Continuous Integration, and Alpha Build Implementation


## Team Report:
#### First subsection:
- Finish gather data about our questions. (Bella and Alex)
- Finish creating the initial Matching Algorithim. (Alex)
- Create fake user profiles for testing. (Bella)
- Start implementing query functions for the database. (Colby)
- Get user input about the front-end design. (Leah)
- Implement user interface in React. (Leah)

#### Second subsection:
- Finished initial matching algorithm
- Created tests:
  - JUnit tests for the matching algorithm
  - JUnit tests for the database queries
- Documented and implemented all query functions. (Sequential functionality)
- Set up SparkJava for the server instead of Node.js for ease of connection to the queries and algorithm.
- Received user feedback and created final app design
- Added Home and Login screens to app user interface
  - Implemented the main features of these screens (Still no connection to backend)
- Created user profiles for testing purposes
- Started creating server API calls to backend.

#### Third subsection:
- Finish the matching algorithm
  - Assign weights to attributes of the matching algorithm (Alex)
  - Finish the method which runs through and scores compatibility (Alex)
  - Implement connection between matching algorithm and server. (Bella and Alex)
- Finish the 20 fake user profiles and put them in a Survey format. (Bella)
- Integrate the server calls with the front end of the app. (Bella and Leah)
- Make queries concurrent (Colby)
- Encrypt contactInfo (Colby).
- Finish adding the rest of the app screens with their main features (Leah)
- Start editing the user interface with good styles/design (Leah)

## Individual Reports:

### Alex:
#### First Subsection:
- Finish gather data about our quesitons
- Finish creating the inital Matching Algorthim
- Start working on a method which runs through and
  scores the compatability between one person and
  everyone else using the platform.

#### Second Subsection:
-  I Finished the inital Matching Algorthim
-  I created JUnit tests for the Algorithm
-  I started to calcuate the weights for it. I found it difficult
   to properly calculate the weights since we have a low question
   count which makes all of the questions matter more so small difference
   could result in a large difference in compabibiltiy.

#### Third Subsection:
- With the information gather properly assign weights to the Matching Alogrithm
- Finsh creating the method which runs through and scores the conpatability between
  one person and everyone else using the platform
- Implement the Matching Algorithm to work with the database and the frontend


### Colby:
#### First subsection:
- Create API for my class Query
- Implement Query Functions associated with account management

#### Second subsection: 
- I created documentation for all necessary Query functions
- I've implemented all necessary Query functions (they only work sequentially right now)
- I have about 2-4 Junit tests created per Query function
- I've set up SparkJava

#### Third Subsection:
- Make my queries work with concurrency
- Clean up my code using private helper methods
- implement encryption for contactInfo.

### Leah:
#### First subsection:
- Get user feedback on the high-fidelity prototype 
- Start implementing the user interface design with React Native

#### Second Subsection:
- Received user feedback and now have a final app design
- Added Home and Login screens to app user interface
  - Implemented the main features of these screens

#### Third Subsection:
- Finish adding the rest of the app screens with their main features (less focus on looks)
- Work with Bella to integrate the server calls with the front end
- Start editing the user interface with good styles/design 

### Bella:
#### First subsection:
- Create at least 20 fake user profiles for testing the matching algorithms.
- Download and set up React Native.
- Practice using Node.js with SQL.
- Start planning the API calls with Node.js
- Talk to Alex/Colby to figure out how to format the data I retrieve from the database for the algorithm.

#### Second subsection:
- Created 10 fake user profiles.
- Practiced with Node.js, eventually decided to switch from Node to Spark for the server side.
  - Allows us to connect it easily to the java side of the app
  - Lets us write query calls and format them in java
- Created several spark server calls including
  - Create Account
  - Get K Matches
  - Log in
  - Create contact
  - Run Algorithm
- Could not test server calls because the matching algorithm is not fully implemented and the database is empty.

#### Third subsection:
- Finish the 20 fake user profiles and put them in a Survey format.
- Finish the server calls.
- Help Leah integrate the server calls with the front end of the app.
