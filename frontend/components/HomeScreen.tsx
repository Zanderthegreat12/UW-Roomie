import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

/**
 * Function that displays log in screen
 * @returns rendering of screen that allows user to log in
 */
export default function HomeScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState('');
    const [password, setPass] = useState('');

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>UW Roomie</Text>
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
                secureTextEntry={true}
                maxLength={20}
                onChangeText={text => setPass(text)}
                value={password}
            />
            <View style={styles.button}>
                <Button
                    title="Sign In"
                    color="#7c2bee"
                    onPress={() => logIn({userN: username}, {pass: password}, {nav: navigation})} //Once we have server on AWS
                 />
            </View>

            <Text style={styles.text}>Don't have an account?</Text>
            <Button
                title="Create Account"
                color="#7c2bee"
                onPress={() => navigation.navigate('Create Account')}
            />
        </View>
    );
}

/**
 * checks if log in of user is successful.
 * If login successful, identify user as 'userN'
 * Else notify user that login failed
 * @param userN username that the user is logging in with
 * @param pass password that the user is logging in with
 * @param nav navigates to desired screen
 */
logIn = async({userN}, {pass}, {nav}) => {
    try{
         let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/logIn?username=" + userN + "&password=" + pass);
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         let parse = res.json();
         let parsed = await parse;
         if(parsed == true){
            nav.navigate('Login', {user: '' + userN});
         } else {
             alert("Login Failed");
         }

    } catch(e) {
         alert("There was an error contacting the server.");
         console.log(e);
    }
}

/**
 * style for home screen components
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