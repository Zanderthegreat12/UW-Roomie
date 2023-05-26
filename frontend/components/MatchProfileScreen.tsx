import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

/**
 * Displays a screen that shows another specified user's profile
 * @param route contains information about the user
 * @returns rendering showing the profile of specified user
 */
export default function MatchProfileScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);
    const [matchName, setMatch] = useState(route.params.match);
    const [phoneNum, setNum] = useState('');
    const [discord, setDiscord] = useState('');
    const [email, setEmail] = useState('');

    /**
     * get contact info of specified user
     * @param user the user whom we want the contact info of
     */
    let getContactInfo = async({user}) => {
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

    getContactInfo({user: matchName});

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>Profile</Text>
            <Text style = {styles.text}>Username: {matchName}</Text>
            <Text style = {styles.text}>Phone number: {phoneNum}</Text>
            <Text style = {styles.text}>Discord: {discord}</Text>
            <Text style = {styles.text}>Email: {email}</Text>

            <Button
                title="Return to Mutual Matches"
                color="#7c2bee"
                onPress={() => navigation.navigate('View Matches Screen',  {user:'' + username})}
            />
        </View>
    );
}

/**
 * purple and white style with centered text for
 * this screen.
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
        color: 'white',
    },

    button: {
        padding: 20,
    },
});