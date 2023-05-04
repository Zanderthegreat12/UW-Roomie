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

How to run code in devlopment environment:
1. Clone UW-Roomie repository on your local machine
2. Install ExpoGo on phone to test app
3. Run "npm install expo" in frontend folder from Command Prompt
4. Run "npx expo start" in frontend folder from Command Prompt
5. Scan QR code on computer from within ExpoGo app

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
