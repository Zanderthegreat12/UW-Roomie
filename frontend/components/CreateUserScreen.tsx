import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

/**
 * Function that displays create user screen
 * @returns rendering for creation user screen
 */
export default function HomeScreen() {
    const navigation = useNavigation();
    const [username, setUser] = useState('');
    const [password, setPass] = useState('');
    const [phone, setPhone] = useState('');
    const [discord, setDis] = useState('');
    const [email, setEmail] = useState('');

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>Create </Text>
            <Text style = {styles.text}>Insert Username:</Text>
            <TextInput
                editable
                style={styles.textBox}
                maxLength={20}
                onChangeText={text => setUser(text)}
                value={username}
            />

            <Text style = {styles.text}>Insert Password:</Text>
            <TextInput
                editable
                style={styles.textBox}
                maxLength={20}
                onChangeText={text => setPass(text)}
                value={password}
            />

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
                title="Create"
                color="#7c2bee"
                onPress={() => createUser({userN: username}, {pass: password}, {nav: navigation}, {pNum: phone},
                    {dis: discord}, {email: email})}
             />
        </View>
    );
}

/**
 * stores user's username, password, and contact info on server side
 * @param userN identifier for the user
 * @param pass user's password to sign in
 * @param nav object that can switch screen shown to user
 * @param pNum user's phone number
 * @param dis user's discord
 * @param email user's email
 */
createUser = async({userN}, {pass}, {nav}, {pNum}, {dis}, {email}) => {
    try{
         //If something is left blank, autofills is with n/a for strings, 0 for phone number
         if(pNum.trim() == ""){
            pNum = "0";
         }

         if(dis.trim() == ""){
            dis = "N/a"
         }

         if(email.trim() == ""){
            email = "N/a"
         }

         //Check to make sure that input is valid
         const testDis = dis.split("#");
         if((isNaN(pNum) || pNum.length != 10) && pNum != 0){
            alert("Error! Phone number is not valid. Please make sure it is 10 digits and that there are no letters");

         } else if(!email.includes("@uw.edu") && email != "N/a"){
             alert("Error! Email is not a valid uw email. Format should be <email>@uw.edu");

         } else if((testDis.length != 2 || isNaN(testDis[1])) && dis != "N/a"){
            alert("Error! Invalid discord. Format should be <name>#<number>");

         } else if(userN.includes(" ")) {
            alert("Error! Username can not contain spaces.");

         } else { //Everything is valid. Encode and submit.
             //let cord = dis.replace("#", '%23');
             let userNew = encodeURIComponent(userN);
             let passNew = encodeURIComponent(pass);
             let emailNew = encodeURIComponent(email);
             let cord = encodeURIComponent(dis);

             let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/createUser?username=" + userNew + "&password=" + passNew + "&pNum=" + pNum + "&discord=" + cord + "&email=" + emailNew);
             let res = await responsePromise;
             if(!res.ok){
                 alert("Some field not filled");
                 //alert("Error! Expected: 200, Was: " + res.status);
                 return;
             }

            let parse = res.json();
            let parsed = await parse;
            if(parsed == true) {
                //User created! Go to the normal screen!
                nav.navigate('Survey Menu', {user: '' + userN, survey: null})
            } else {
                alert("Username already taken");
            }
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
        color: '#FFDA8F',
    },
});