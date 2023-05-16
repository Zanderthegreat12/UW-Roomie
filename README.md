# UW-Roomie
UW Roomie is a phone application where students who are planning on dorming 
can go to find roomates based on their preferences. 
Upon creating an account they will be prompted to fill a survey which describes 
their characteristics and the characteristics that they are looking for in a roommate.
From there the matching algorithm will find people 
who answered similarly and display those people to the user. 
If the user likes one of the potential dormmates, 
then they can attempt to get in contact with that user 
and if the other user agrees then it will display their contact information. 
From there then the users can determine if they want to dorm or not. <br /> <br />

How to run code in devlopment environment (for users):
  1. Clone UW-Roomie repository on your local machine
  2. Install ExpoGo on phone to use app
  3. Nagivate to the frontend folder of this Repo from the Command Prompt
  4. Run "npm install expo"
  5. Run "npx expo start"
  6. Scan QR code on computer from within ExpoGo app
  7. Note: backend server will be down during the following times, {12:00 - 12:10 am, 6:00-6:10 am, 12:00-12:10 pm, 6:00-6:10 pm}

Bug Reporting:
  1. Report bugs here: https://github.com/Zanderthegreat12/UW-Roomie/issues
  2. Bug Reports should include the following information: <br>
    - The screen(s) where the bug occurs <br>
    - What event triggered the bug (if applicable)
    - What the bug actually is

How to host code on independent server (for developers):
  1. Clone UW-Roomie repository on your local machine
  2. Create 2 SQL databases. One for testing and one for main service
  3. Input the needed properties of your databses in dbconn (service database) and dbconntest (test database)
  4. host code in backend folder on a server using the following commands: <br>
    - to build the code, run "mvn package -Dmaven.test.skip" as a command line <br>
    - to run the code, run "mvn compile exec:java -Dexec.mainClass=roomieapp.Server" as a command line
  5. Anytime the https://5pfrmumuxf.us-west-2.awsapprunner.com is written in frontend code, change it to your new server's domain <br>
    - This isn't currently centralized in our code
  6. Install ExpoGo on phone to use app
  7. Nagivate to the frontend folder of this Repo from the Command Prompt (on local machine)
  8. Run "npm install expo"
  9. Run "npx expo start"
  10. Scan QR code on computer from within ExpoGo app

--The current Use Cases that are functional are Filing out the survery and Finding Matches--

This means that you should be able to create an account, fill out the suvery and find people who
have similar compatibility.

The Structure of this Repo is as follows:
  1. Backend - contains the programming data about the servers, database and the matching algorithms. 
               Also contains the files where the backend interacts with the frontend.
  2. Frontend - contains the layout and design of the UW-Roomie app which the user sees when
                they open up and use the app. 
  3. reports - stores the progress reports for the TA for each week we have worked on.
  
How to test backend:
  1. Set up JDK on computer
  2. Run "mvn -B package --file pom.xml" in backend folder from Command Prompt
  
How to test frontend:
  1. Run "npm install" in frontend folder from Command Prompt
  2. Run "npm install expo" in frontend folder from Command Prompt
  3. Run "npm test" in frontend folder from Command Prompt

Adding Tests:
  1. Create Java class in backend/src/test/java/roomieapp
  2. Make sure newly created Java class name ends in "Test"
  3. Import Junit, and create tests using Junit framework in newly created class
