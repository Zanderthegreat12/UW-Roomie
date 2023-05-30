import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

/**
 * Displays the home screen where the user can redirect themselves
 * to any other screen.
 * @param route contains information about the user
 * @returns rendering of home screen
 */
export default function HomeScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);
    const [phoneNum, setNum] = useState('');
    const [discord, setDiscord] = useState('');
    const [email, setEmail] = useState('');

    /**
     * Get contact info of given user
     * @param user identifier for user
     */
    getContactInfo = async({user}) => {
        try{
             let userNew = encodeURIComponent(user);
             let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getContact?username=" + userNew);
             let res = await responsePromise;
             if(!res.ok){
                 alert("Error! Expected: 200, Was: " + res.status);
                 return;
             }

             let parse = res.json();
             let parsed = await parse;
             if(parsed != null){
                setDiscord(parsed.discord);
                setEmail(parsed.email);
                setNum(parsed.phoneNumber);
             } else {
                setDiscord("No discord given");
                setEmail("No email given");
                setNum("No phone number given");
             }

        } catch(e) {
             alert("There was an error contacting the server.");
             console.log(e);
        }
    }

    getContactInfo({user: username});

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>Profile</Text>
            <Text style = {styles.text}>Username: {username}</Text>
            <Text style = {styles.text}>Phone number: {phoneNum}</Text>
            <Text style = {styles.text}>Discord: {discord}</Text>
            <Text style = {styles.text}>Email: {email}</Text>

            <View style={styles.button}>
                <Button
                    title="Edit Contact Info"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Edit Contact', {user:'' + username, givenPhone: '' + phoneNum,
                        givenDiscord: '' + discord, givenEmail: '' + email})}
                />
            </View>

            <View style={styles.button}>
                 <Button
                    title="Retake Survey"
                    color="#7c2bee"
                    onPress={() => surveyRedirect(username, navigation)}
                 />
            </View>

            <Button
               title="Back to Home"
               color="#7c2bee"
               onPress={() => navigation.navigate('Home',  {user:'' + username})}
            />
        </View>
    );
}

/**
 * get survey info for current user, then redirect to survye screen
 * @param user the identifier for the user
 * @param navigation object that can switch screen the user is currently on
 */
let surveyRedirect = async(user, navigation) => {
    var surveyInfo = await getSurvey({userN: user})
    navigation.navigate('Survey Menu', {user:'' + user, survey: surveyInfo})
}

/**
 * get survey info of userN
 * @param userN the identifier for the user
 */
let getSurvey = async({userN}) => {
    try{
        let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getSurvey?username=" + userN);
        let res = await responsePromise;
        if(!res.ok){
            alert("um")
            return null;
        }
        let parse = res.json();
        let parsed = await parse;
        return parsed

    } catch(e) {
        alert("There was an error contacting the server.");
        console.log(e);
    }
}

/**
 * standard purple and white style for screen for profile screen
 */
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },

    textBox: {
        backgroundColor: 'white',
        height: 50,
        width: 200,
        borderRadius: 8,
    },

    text: {
        margin: 10,
        color: 'white',
    },

    title: {
        fontSize: 30,
        color: '#FFDA8F',
    },

    button: {
        padding: 20,
    },
});