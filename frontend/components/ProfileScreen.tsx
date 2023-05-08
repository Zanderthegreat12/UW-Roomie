import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

export default function HomeScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);
    const [phoneNum, setNum] = useState('');
    const [discord, setDiscord] = useState('');
    const [email, setEmail] = useState('');

    return (
        <View style={styles.container}>
            <Text style = {styles.title}>Profile</Text>
            <Text style = {styles.text}>Username: {username}</Text>
            <Text style = {styles.text}>Phone number: {phoneNum}</Text>
            <Text style = {styles.text}>Discord: {discord}</Text>
            <Text style = {styles.text}>Email: {email}</Text>

             <Button style = {styles.button}
                title="Retake Survey"
                onPress={() => navigation.navigate('Survey Menu', {user: + '' + username})}
             />

             <Button
                title="Back to Home"
                onPress={() => navigation.navigate('Login',  {user: + '' + username})}
             />
        </View>
    );
}

getContactInfo = async(user) => {
    try{
         let responsePromise = fetch("http://localhost:4567/getContact?username=" + user);
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         let parse = res.json();
         let parsed = await parse;
         //SET A VAR WITH THE CONTACT INFO

    } catch(e) {
         alert("There was an error contacting the server.");
         console.log(e);
    }
}

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
    },

    title: {
        fontSize: 30,
    },
});