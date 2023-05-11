# Report:

5/10 Meeting Agenda:
Review Alpha build version


## Team Report:
#### First subsection:
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

#### Second subsection:
- Fully implemented matching algorithm and connected it to the database.
- Created pages for users to see their matches.
- Queries work with concurrency
- Cleaned up query class
  - Added helper methods
- Backend server set up on AWS
  - Connected to front end through async functions.
- Set up second database for testing purposes
- Finished implementing basic features for all screens
  - Servey dropdown menu
  - Textboxes for entering user info (create user/login)
  - Matching page
  - Login Page
  - Create new user
- Integrated the server calls with the front end of the app.

#### Third subsection:
- Fix create user so that it won't allow for resetting passwords. (Bella)
- Making the app display more intuitive/nicer (Mostly Leah)
  - radio buttons (Leah)
  - Displaying/inputting contact info (Bella)
  - Navigation menu bar (Leah)
- Implement liked user functionality
  - Front end display feature (Alex)
  - Server API call to record likes (Bella)
- Set up the swipe right functionality for the app. (Alex)
- Return error if database/connection isn't working properly (Colby)
- implement encryption for contact info (Colby)
- allow storage for photos (Colby)

## Individual Reports:

### Alex:
#### First Subsection:
- With the information gather properly assign weights to the Matching Alogrithm
- Finsh creating the method which runs through and scores the conpatability between
  one person and everyone else using the platform
- Implement the Matching Algorithm to work with the database and the frontend

#### Second Subsection:
- Made the Matching Algorithm function to calculate all compatability for a person
- Created the matching pages on frontend. I learned how to properly use Async functions in Javascript which
  I struggled with for a while. I also learned how to properly code in Javascript.
- Managed to create a page to display a matches survey results so users can look at them.
#### Third Subsection:
- Set up the swipe right functionality for the app,=.
- Start to work on implementing the liked user functionality.


### Colby:
#### First subsection:
- Make my queries work with concurrency
- Clean up my code using private helper methods
- implement encryption for contactInfo.

#### Second subsection: 
- Made queries work with concurrency
- Cleaned up Query class with helper methods
- Created Survey Menu screen
- set up backend server on AWS
- set up second database for testing purposes


#### Third Subsection:
- Return error if database/connection isn't working properly
- implement encryption for contact info
- allow storage for photos


### Leah:
#### First subsection:
- Finish adding the rest of the app screens with their main features (less focus on looks)
- Work with Bella to integrate the server calls with the front end
- Start editing the user interface with good styles/design 

#### Second Subsection:
- Finished implementing basic features for all screens
- Created dropdown button lists for the survey
- Tried to implement radio buttons for the survey but they didn't work well enough; currently stuck on this
- Added more styles and padding

#### Third Subsection:
- Figure out how to make the radio buttons work
- Make a navigation menu bar for most screens
- Do more editing to make the screens look nicer

### Bella:
#### First subsection:
- Finish the 20 fake user profiles and put them in a Survey format.
- Finish the server calls.
- Help Leah integrate the server calls with the front end of the app.

#### Second subsection:
- Finished enough fake user profiles and uploaded them to the database.
- Finished the server calls.
- Integrated the server calls with the front end of the app.
- Created the log in screen and home screen of the app.

#### Third subsection:
- Fix create user so that it won't allow for resetting passwords.
- Create place to input contact data.
- Display contact info in the profile screen.
- Create new APIs for setting compatibility scores and user likes.
