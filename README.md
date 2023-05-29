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

Icons created by Freepick - Flaticon: <br />
Shared housing icons - https://www.flaticon.com/free-icons/shared-housing <br />
Package icons - https://www.flaticon.com/free-icons/package <br />
Gift box icons - https://www.flaticon.com/free-icons/gift-box <br />
Profile icons - https://www.flaticon.com/free-icons/profile <br />
Export icons - https://www.flaticon.com/free-icons/export <br /> <br />

## USER GUIDE
How to Install and Run App:
  1. Install code here - https://expo.dev/artifacts/eas/5sW4JNPdUQDGiCkJqUK5ac.apk
  2. Run app using adroid device or android emulator
  3. Note: This app is currently only available for android 

How to run code in devlopment environment (for users):
  1. Clone UW-Roomie repository on your local machine
  2. Install ExpoGo on phone to use app
  3. Nagivate to the frontend folder of this Repo from the Command Prompt
  4. Run "npm install expo"
  5. Run "npx expo start"
  6. Scan QR code on computer from within ExpoGo app

How to use App:
1. Create new profile. Include username, password, and contact info.
    - Username should not include a space
    - If specific contact info does not apply, write n/a
2. Take the survey. Make sure that every field is filled out.
3. Go to the match menu. There will be a list of top matches.
4. Click on a match that looks interesting. If you want to talk to them, click the send match request button.
5. To check incoming match requests, click on the incoming matches buttons in the home screen. From here you can either accept or reject the match.
6. Finally, go to the liked match screen where you can see information about the people that you matched with, such as contact info.

Bug Reporting:
  1. Report bugs here: https://github.com/Zanderthegreat12/UW-Roomie/issues
  2. Bug Reports should include the following information: <br>
    - The screen(s) where the bug occurs <br>
    - What event triggered the bug (if applicable) <br>
    - What the expected value/screen is supposed to be <br>
    - What the actual value/screen is

Known Bugs:
- Not all matches show up after creating survey.
- Loading match menu resets match status.

## DEVELOPER GUIDE
How to host code on independent server (for developers):
  1. Clone UW-Roomie repository on your local machine
  2. Create 2 SQL databases. One for testing and one for main service
  3. Input the needed properties of your databses in dbconn (service database) and dbconntest (test database)
  4. host code in backend folder on a server using the following commands: <br>
    - Run the code using JDK of your choice <br>
    - to build the code, run "mvn package -Dmaven.test.skip" as a command line <br>
    - to run the code, run "mvn compile exec:java -Dexec.mainClass=roomieapp.Server" as a command line
  5. Anytime the https://5pfrmumuxf.us-west-2.awsapprunner.com is written in frontend code, change it to your new server's domain <br>
    - This isn't currently centralized in our code
  6. Install ExpoGo on phone to use app
  7. Nagivate to the frontend folder of this Repo from the Command Prompt (on local machine)
  9. Run "npm install expo"
  10. Run "npx expo start"
  11. Scan QR code on computer from within ExpoGo app

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
  1. Set up JDK on computer <br>
      - For more information, visit https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/
  3. Run "mvn -B package --file pom.xml" in backend folder from Command Prompt
  
How to test frontend:
  1. Run "npm install" in frontend folder from Command Prompt
  2. Run "npm install expo" in frontend folder from Command Prompt
  3. Run "npm test" in frontend folder from Command Prompt

Adding Tests:
  1. Create Java class in backend/src/test/java/roomieapp
  2. Make sure newly created Java class name ends in "Test"
  3. Import Junit, and create tests using Junit framework in newly created class
