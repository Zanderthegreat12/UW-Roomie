import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

/**
 * Function that displays a way to edit contact info
 * @returns rendering for the edit contact info screen
 */
export default function EditContactInfo({route}) {
    const navigation = useNavigation();
    //var username = route.params.user;
    const [username, setUser] = useState('' + route.params.user);
    const [phone, setPhone] = useState('' + route.params.givenPhone);
    const [discord, setDis] = useState('' + route.params.givenDiscord);
    const [email, setEmail] = useState('' + route.params.givenEmail);

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>Please input contact info</Text>
            <Text style = {styles.text}>Insert Phone Number:</Text>
            <TextInput
                editable
                style={styles.textBox}
                maxLength={20}
                onChangeText={text => setPhone(text)}
                value={phone}
            />

            <Text style = {styles.text}>Insert Email:</Text>
            <TextInput
                editable
                style={styles.textBox}
                maxLength={20}
                onChangeText={text => setEmail(text)}
                value={email}
            />

            <Text style = {styles.text}>Insert Discord:</Text>
            <TextInput
                editable
                style={styles.textBox}
                maxLength={20}
                onChangeText={text => setDis(text)}
                value={discord}
            />


             <Button
                title="Submit"
                color="#7c2bee"
                onPress={() => contactInfo({userN: username}, {nav: navigation}, {pNum: phone},
                    {dis: discord}, {email: email})}
             />
        </View>
    );
}

/**
 * stores user's username, password, and contact info on server side
 * @param userN identifier for the user
 * @param nav object that can switch screen shown to user
 * @param pNum user's phone number
 * @param dis user's discord
 * @param email user's email
 */
contactInfo = async({userN}, {nav}, {pNum}, {dis}, {email}) => {
    try{
         //Encoding user input
         let userNew = encodeURIComponent(userN);
         let pNumNew = encodeURIComponent(pNum);
         let emailNew = encodeURIComponent(email);
         let cord = encodeURIComponent(dis);

         //Making server call
         let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/createContact?username="+userNew+"&email="+emailNew+"&pNum="+pNumNew+"&discord="+cord);
         let res = await responsePromise;
         if(!res.ok){
             //alert("Some field not filled");
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

        //Parse returning info.
        let parse = res.json();
        let parsed = await parse;
        if(parsed == true) {
            //Contact info updated! Go back to profile screen
            nav.navigate('Profile', {user: '' + userN,})
        }

    } catch(e) {
         alert("There was an error contacting the server.");
         console.log(e);
    }
}

/**
 * style for create user screen
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
        height: 40,
        width: 200,
        borderRadius: 8,
        padding: 10,
    },

    text: {
        margin: 10,
        color: 'white'
    },

    title: {
        fontSize: 30,
        color: 'white',
    },
});