import { StatusBar } from 'expo-status-bar';
import {StyleSheet, Text, View, Button, Alert, TouchableOpacity, ScrollView, Image} from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useEffect, useState} from 'react';

/**
 * Function to display the profile screen
 * @param route contains information about the user
 * @returns rendering for profile screen
 */
export default function MatchInfoScreen({route}) {
    const navigation = useNavigation();

    const [username, setUser] = useState(route.params.user);
    const [matchname, setMatchName] = useState(route.params.match);
    const [comp, setComp] = useState(route.params.comp);
    const [status, setStatus] = useState(route.params.status);
    const [buttonStatus, setButtonStatus] = useState(false);
    const [loading, setLoading] = useState(true);

    let ReturnButton: any[];
    ReturnButton = []

    if(route.params.screen == 1){
        ReturnButton.push(
        <View style={styles.button}>
            <Button
                title="Back to all Matches"
                color="#FFA456"
                onPress={() => navigation.navigate('Matching Menu', {user: '' + username})}
            />
        </View>)
    } else if (route.params.screen == 2){
        ReturnButton.push(
        <View style={styles.button}>
            <Button
                title="Back to Oncomming Matches"
                color="#FFA456"
                onPress={() => navigation.navigate('Incoming Matches Screen', {user: '' + username})}
            />
        </View>)
    } else {
        ReturnButton.push(
        <View style={styles.button}>
            <Button
                title="Back to Outgoing Matches"
                color="#FFA456"
                onPress={() => navigation.navigate('Outgoing Matches Screen', {user: '' + username})}
            />
        </View>)
    }

    const[data, setData] = useState([]);


    /**
     * Gets the contact info of desired user
     * @param user the username of current user
     */
    let getContact = async(user) => {
        try{
            let userNew = encodeURIComponent(user);
            let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getSurvey?username=" + userNew);
            let res = await responsePromise;
            if(!res.ok){
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            let parse = res.json();
            let parsed = await parse;

            let TextInfo: any[];
            TextInfo = [];

            TextInfo.push(<Text key={0} style={styles.title}>{parsed.username}</Text>);
            TextInfo.push(<Text key={1} style={styles.text}>Top Dorm:    {parsed.firstDorm}</Text>);
            TextInfo.push(<Text key={2} style={styles.text}>Second Dorm:    {parsed.secondDorm}</Text>);
            TextInfo.push(<Text key={3} style={styles.text}>Third Dorm:    {parsed.thirdDorm}</Text>);

            if (parsed.roomType == 1){
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Single/Studio</Text>);
            } else if (parsed.roomType == 2) {
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Double/2 Bedrooms</Text>);
            } else if (parsed.roomType == 3) {
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Triple/3 Bedrooms</Text>);
            } else if (parsed.roomType == 4) {
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Quad-Suite/4 Bedrooms</Text>);
            } else if (parsed.roomType == 5) {
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   5 Bedrooms</Text>);
            } else if (parsed.roomType == 6) {
                TextInfo.push(<Text key={4} style={styles.text}>Room Type:   6 Bedrooms</Text>);
            }

            if (parsed.genderInclusive == 0){
                TextInfo.push(<Text key={5} style={styles.text}>Gender Inclusive:   Not Interested</Text>);
            } else {
                TextInfo.push(<Text key={5} style={styles.text}>Gender Inclusive:   Yes, Interested</Text>);
            }

            if (parsed.studentYear == 1) {
                TextInfo.push(<Text key={6} style={styles.text}>Student Year:   1st Year</Text>);
            } else if (parsed.studentYear == 2) {
                TextInfo.push(<Text key={6} style={styles.text}>Student Year:   2nd Year</Text>);
            } else if (parsed.studentYear == 3) {
                TextInfo.push(<Text key={6} style={styles.text}>Student Year:   3rd Year</Text>);
            } else if (parsed.studentYear == 4) {
                TextInfo.push(<Text key={6} style={styles.text}>Student Year:   4th Year</Text>);
            }

            if (parsed.drinkingPref == 0) {
                TextInfo.push(<Text key={7} style={styles.text}>Drinking Preference:   No Drinking</Text>);
            } else {
                TextInfo.push(<Text key={7} style={styles.text}>Drinking Preference:   Drinking Ok</Text>);
            }

            if (parsed.wakeTime == 6) {
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   6:00 am - 7:00 am</Text>);
            } else if (parsed.wakeTime == 7){
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   7:00 am - 8:00 am</Text>);
            } else if (parsed.wakeTime == 8){
                TextInfo.push(<Text style={styles.text}>Wake up Time:   8:00 am - 9:00 am</Text>);
            } else if (parsed.wakeTime == 9){
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   9:00 am - 10:00 am</Text>);
            } else if (parsed.wakeTime == 10){
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   10:00 am - 11:00 am</Text>);
            } else if (parsed.wakeTime == 11){
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   11:00 am - 12:00 pm</Text>);
            } else if (parsed.wakeTime == 12){
                TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   12:00 pm - 1:00 pm</Text>);
            }

            if (parsed.sleepTime == 6) {
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   9:00 pm - 10:00 pm</Text>);
            } else if (parsed.sleepTime == 7){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   10:00 pm - 11:00 pm</Text>);
            } else if (parsed.sleepTime == 8){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   11:00 pm - 12:00 am</Text>);
            } else if (parsed.sleepTime == 9){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   12:00 am - 1:00 am</Text>);
            } else if (parsed.sleepTime == 10){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   1:00 am - 2:00 am</Text>);
            } else if (parsed.sleepTime == 11){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   2:00 am - 3:00 pm</Text>);
            } else if (parsed.sleepTime == 12){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   3:00 pm - 4:00 pm</Text>);
            }

            if(parsed.heavySleep == 0){
                TextInfo.push(<Text key={10} style={styles.text}>Sleep Type:  Light Sleeper</Text>);
            } else {
                TextInfo.push(<Text key={10} style={styles.text}>Sleep Type:  Heavy Sleeper</Text>);
            }

            if(parsed.studentVert == 0){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Introvert</Text>);
            } else if (parsed.studetVert == 1){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Ambivert</Text>);
            } else if (parsed.studentVert == 2){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Extrovert</Text>);
            }

            if(parsed.studentFriends == 0){
                TextInfo.push(<Text key={12} style={styles.text}>Friends in Room: Won't bring Friends</Text>);
            } else {
                TextInfo.push(<Text key={12} style={styles.text}>Friends in Room: Will bring Friends</Text>);
            }

            if(parsed.studentNeat == 0){
                TextInfo.push(<Text key={13} style={styles.text}>Neatness: Messy</Text>);
            } else {
                TextInfo.push(<Text key={13} style={styles.text}>Neatness: Clean</Text>);
            }


            setData(TextInfo);
            setLoading(false);

        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    /**
     * Changes the matchStatus to Accepted and sends that to
     * the database
     * @param user the username of current user
     * @param matchname the name of the match
     * @param  num 0 if this is the first time a person from this
     *             match pair accepts the match, 1 or 2 if the other
     *             user then accepts.
     */
    let AcceptMatch = async({user},{matchname}, {num}) => {
        try{
            let userNew = encodeURIComponent(user);
            let matchNew = encodeURIComponent(matchname);

            let responsePromise;
            let res;
            if (num == 0) {
                if (userNew.localeCompare(matchNew) == -1) {
                    responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + userNew + "&otherName=" + matchNew + "&newStatus=1");
                    res = await responsePromise;
                } else {
                    responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + matchNew+ "&otherName=" + userNew + "&newStatus=2");
                    res = await responsePromise;
                }
            } else {
                if (userNew.localeCompare(matchNew) == -1) {
                    responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + userNew + "&otherName=" + matchNew + "&newStatus=3");
                    res = await responsePromise;
                } else {
                    responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + matchNew + "&otherName=" + userNew +"&newStatus=3");
                    res = await responsePromise;
                }
            }
            if(!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            setButtonStatus(true)
            if(num == 0) {
                Alert.alert("Match Request was succesfully sent");
            } else {
                Alert.alert("Match was Mutual Accepted", "Check View Matches on the Home menu to get their information");
            }

        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    /**
     * Changes the matchStatus to Rejected and sends that to
     * the database
     * @param user the username of current user
     * @param matchname the name of the match
     */
    let RejectMatch = async({user}, {matchname}) => {
        try{
            let userNew = encodeURIComponent(user);
            let matchNew = encodeURIComponent(matchname);

            let responsePromise;
            let res;

            if (userNew.localeCompare(matchNew) == -1) {
                responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + userNew + "&otherName=" + matchNew + "&newStatus=-1" );
                res = await responsePromise;
            }  else {
                responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + matchNew + "&otherName=" + userNew + "&newStatus=-1");
                res = await responsePromise;
            }
            if(!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            setButtonStatus(true)
            Alert.alert("Match Request was succesfully rejected");

        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    /**
     * Changes the matchStatus to none and sends that to
     * the database
     * @param user the username of current user
     * @param matchname the name of the match
     */
    let UndoMatch = async({user}, {matchname}) => {
        try{
            let userNew = encodeURIComponent(user);
            let matchNew = encodeURIComponent(matchname);

            let responsePromise;
            let res;

            responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/setMatchStatus?username=" + userNew + "&otherName=" + matchNew + "&newStatus=0" );
            res = await responsePromise;
            if(!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            setButtonStatus(true)
            Alert.alert("Match Request was successfuly rescinded");

        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }


    useEffect(()=>{getContact(matchname)},[]);
    let matchBtns: any[] = []
    //changes what buttons are displayed on screen depending on
    //the type of matches that the screens are displaying
    if(status == 0) {
        matchBtns.push(
        <View style={styles.button}>
            <Image style = {styles.logo}
                source = {require('../assets/remove.png')}>
            </Image>
            <Button
                title="Reject Match"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => RejectMatch({user:username}, {matchname:matchname})}
            />
        </View>)
        matchBtns.push(
        <View style={styles.button}>
            <Image style = {styles.logo}
                source = {require('../assets/check.png')}>
            </Image>
            <Button
                title="Send Match Request"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => AcceptMatch({user: username},  {matchname: matchname}, {num:status})}
            />
        </View>)
    } else if((status == 1 && username.localeCompare(matchname) == 1) ||
            (status == 2 && username.localeCompare(matchname) == -1)) {
        matchBtns.push(
        <View style={styles.button}>
            <Image style = {styles.logo}
                source = {require('../assets/remove.png')}>
            </Image>
            <Button
                title="Reject Match"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => RejectMatch({user:username}, {matchname:matchname})}
            />
        </View>)
        matchBtns.push(
        <View style={styles.button}>
            <Image style = {styles.logo}
                source = {require('../assets/check.png')}>
            </Image>
            <Button
                title="Accept Match"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => AcceptMatch({user: username},  {matchname: matchname}, {num:status})}
            />
        </View>)
    } else if((status == 2 && username.localeCompare(matchname) == 1) ||
            (status == 1 && username.localeCompare(matchname) == -1)) {
        matchBtns.push(
        <View style={styles.button}>
            <Image style = {styles.logo}
                source = {require('../assets/remove.png')}>
            </Image>
            <Button
                title="Undo Match Request"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => UndoMatch({user:username}, {matchname:matchname})}
            />
        </View>)
    }


    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <View style={styles.container}>
                {loading && <Text style={styles.text}> Loading...</Text>}
                <Text>{'\n'}</Text>
                {data}
                <View style={{ flexDirection:"row", padding:10, flex:1}}>
                    {matchBtns}
                </View>
                {ReturnButton}
                <Text>{'\n'}</Text>
            </View>
        </ScrollView>
    );
}


/**
 * styles for the profile screen
 */
const styles = StyleSheet.create({
    container: {
        flexGrow: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },

    text: {
        margin: 10,
        color: 'white',
    },

    title: {
        fontSize: 30,
        color: '#FFDA8F',
        padding: 10,
    },

    button: {
        justifyContent:'center',
        padding: 10,
    },

    logo: {
        width: 40,
        height: 40,
        margin: 10,
        alignSelf: 'center',
    }
});