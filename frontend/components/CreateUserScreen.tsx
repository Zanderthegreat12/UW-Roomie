import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

export default function HomeScreen() {
    const navigation = useNavigation();
    const [username, setUser] = useState('');
    const [password, setPass] = useState('');

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

            <Text>{username}</Text>
            <Text>{password}</Text>

             <Button
                title="Create"
                onPress={() => createUser(username, password)}//navigation.navigate('Login', {user: '' + username,})} //createUser(username, password)
             />
        </View>
    );
}

createUser = async(user, pass) => {
    try{
         let responsePromise = fetch("http://localhost:4567/createUser?username=" + user + "&password=" + pass);
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         //User created! Go to the normal screen!
         navigation.navigate('Login', {user: '' + username,})

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