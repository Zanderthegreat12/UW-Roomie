package roomieapp;

import java.util.ArrayList;
import java.util.List;

class MatchingAlgorithm {

    public MatchingAlgorithm(){}

    /**
     * Calculate the compatability between 2 seperate surveys
     * @param user1 The first user survey in the comparison
     * @param user2 The second user survey in the comparison
     * @return if the users are the same return null else,
     *          a Match containing the calculated compatitabilty between 2 users
     */
    public  Match ComputeCompatability(Survey user1, Survey user2){
        float comp = 0;

        // return null if it's the same user
        if (user1.username.equals(user2.username)){
            return null;
        }

        // compare First Dorm
        if (user1.firstDorm.equals(user2.firstDorm)) {
            comp+=10;
        }

        // compare Second Dorm
        if (user1.secondDorm.equals(user2.secondDorm)) {
            comp+=7;
        }

        // compare Third Dorm
        if (user1.thirdDorm.equals(user2.thirdDorm)) {
            comp+=3;
        }

        // compare Room Type
        if (user1.roomType == user2.roomType){
            comp+=20;
        }

        // compare genderInclusiveness
        if(user1.genderInclusive == user2.genderInclusive) {
            comp+=5;
        }

        // compare student Year
        if(user1.studentYear == user2.roommateYear && user1.roommateYear == user2.studentYear) {
            comp+=10;
        } else if (user1.roommateYear == -1 ) {
            if (user1.studentYear == user2.roommateYear || user2.roommateYear == -1){
                comp+=10;
            }
        } else if (user2.roommateYear == -1 ) {
            if (user2.studentYear == user1.roommateYear) {
                comp+=10;
            }
        }

        // compare drinking and Smoke preferences
        if (user1.drinkingPref == user2.drinkingPref){
            comp+=10;
        }

        // compare time you go to sleep
        if (user1.sleepTime == user2.sleepTime){
            comp+=5;
        }

        // compare time you wake up
        if (user1.wakeTime == user2.wakeTime){
            comp+=5;
        }

        // compare the kind of sleeper you are
        if (user1.heavySleep == user2.heavySleep){
            comp+=6;
        }

        // compare the kind of vert that you are
        if(user1.studentVert == user2.roommateVert && user1.roommateVert == user2.studentVert) {
            comp+=5;
        } else if (user1.roommateVert == -1 ) {
            if (user1.studentVert == user2.roommateVert || user2.roommateVert == -1){
                comp+=5;
            }
        } else if (user2.roommateVert == -1 ) {
            if (user2.studentVert == user1.roommateVert) {
                comp+=5;
            }
        }

        // compare if you want to bring friends over
        if(user1.roommateFriends == 1 || user2.roommateFriends == 1){
            if(user1.roommateFriends == 1 && user2.roommateFriends == 1){
                if(user2.studentFriends != 1 && user1.studentFriends !=1){
                    comp+=7;
                }
            } else if (user1.roommateFriends == 1){
                if(user2.studentFriends != 1) {
                    comp += 7;
                }
            } else {
                if(user1.studentFriends != 1){
                    comp +=7;
                }
            }
        } else {
            comp+=7;
        }

        // compare student neatness
        if(user1.studentNeat == user2.roommateNeat && user1.roommateNeat == user2.studentNeat) {
            comp+=7;
        } else if (user1.roommateNeat == -1 ) {
            if (user1.studentNeat == user2.roommateNeat || user2.roommateNeat == -1){
                comp+=7;
            }
        } else if (user2.roommateNeat == -1 ) {
            if (user2.studentNeat == user1.roommateNeat) {
                comp+=7;
            }
        }

        if (user1.username.compareTo(user2.username) > 0){
            return new Match(user2.username, user1.username, comp,0 );
        } else {
            return new Match(user1.username, user2.username, comp,0 );
        }
    }

    /**
     * Calculate the compatibility for a single user with all other people using the app
     * @param user1 The user whose compatability with everyone you want to calculate
     * @param others A list of all the user surveys in the database
     * @return A list of all the matches for the user and their compatability
     */
    public List<Match> ComputeCompatabilityForAll(Survey user1, List<Survey> others){

        List<Match> matches = new ArrayList<>();

        for(int i = 0; i < others.size(); i++){

           if (!user1.username.equals(others.get(i).username)){
               Match pair = ComputeCompatability(user1, others.get(i));
               matches.add(pair);
           }

       }
       return matches;
    }
}