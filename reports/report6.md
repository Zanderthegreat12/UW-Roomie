# Report:

5/25 Meeting Agenda:
Review Peer Review build version

## Team Report:
#### First subsection:
- Fine tune the matching algrothim so it gives a better value (Alex)
- Fix accepting match so a user can't force a mutual match by clicking the accept button (Alex)
- Make the buttons look uniform for all users
- allow storage for photos (Colby)
- be able to view already taken survey (Colby)
- Frontend documentation (colby)
- Fix log out so that it clears old log in data. (Bella)
- Add something to edit contact info in profile (Bella)
- Implement working radio buttons (Leah)
- Finish the menu bar (Leah)
- Add design formatting to match information screens (Leah)

#### Second subsection:
- Accept/reject buttons disabled after accepting/rejecting a match
- Loading displayed when the algorithm is running.
- can view old survey answers when retaking survey
- fixed bug that crashed server after no usage for an hour
- added frontend documentation
- retaking survey doesn't erase match requests and mutual matches anymore
- Fixed bug where special characters would break http requests for discord
- Needed to encode all user-input strings before any http requests.
- Fixed a server error where if the contact info threw an error, it would create an account without contact info.

#### Third subsection:
- Fix log out so that it clears old log in data. (Bella)
- Fix textbox limit for email. (Bella)
- Add something to edit contact info in profile (Bella)
- Add create user form authentication (make sure its a valid discord/email) (Bella)
- Disable back to home button when taking the survey for the first time. (Bella)
- Fine tune the matching algrothim (Alex)
- Make the front end look more uniform and presentable. (All of us)
- add loading screen for survey screen
- ability to undo rejected matches
- add query to view all rejected matches

## Individual Reports:

### Alex:
#### First Subsection:
- Fine tune the matching algrothim so it gives a better value
- Fix accepting match so a user can't force a mutual
  match by clicking the accept button
- Make the buttons look uniform for all users

#### Second Subsection:
- I managed to make it so you can't click a button after accepting
  or reject a Match. I struggled with that since I had to set it up
  when you first load the page to check that information and it caused
  a few bugs where i didn't know the origin.
- I also had to research how make it to display the words "loading.." while the 
  async funciton was running.

#### Third Subsection:
- Fine tune the matching algrothim
- Make the front end look more uniform and presentable.

### Colby:
#### First subsection:
- allow storage for photos
- be able to view already taken survey
- add frontend documentation for new screens

#### Second subsection:
- can view old survey answers when retaking survey
- fixed bug that crashed server after no usage for an hour
- added frontend documentation
- retaking survey doesn't erase match requests and mutual matches anymore

#### Third Subsection:
- add loading screen for survey screen
- ability to undo rejected matches
- add query to view all rejected matches

### Leah:
#### First subsection:
- Implement working radio buttons
- Finish the menu bar
- Add design formatting to match information screens

#### Second Subsection:
-

#### Third Subsection:
-

### Bella:
#### First subsection:
- Fix runAlg so that it doesn't reset match status.
- Fix log out so that it clears old log in data.
- Front end documentation
- Add something to edit contact info in profile
- Make front end look nice

#### Second subsection:
- Fixed bug where special characters would break http requests for discord
  - Needed to encode all user-input strings before any http requests.
- Fixed a server error where if the contact info threw an error, it would create an account without contact info.

#### Third subsection:
- Fix log out so that it clears old log in data.
- Fix textbox limit for email.
- Add something to edit contact info in profile
- Add create user form authentication (make sure its a valid discord/email)
  - At this point in time no way to truly verify.
- Disable back to home button when taking the survey for the first time.
