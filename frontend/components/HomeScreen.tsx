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
            <Text>UW Roomie</Text>
            <Text>Insert Username:</Text>
            <TextInput
                editable
                style={{padding: 5}}
                onChangeText={text => setUser(text)}
                value={username}
            />

            <Text>Insert Password:</Text>
            <TextInput
                editable
                style={{padding: 5}}
                onChangeText={text => setPass(text)}
                value={password}
            />

            <Text>{username}</Text>
            <Text>{password}</Text>

            <Button
                title="Sign In"
                onPress={() => navigation.navigate('Login')}//logIn()} //Once we have server on AWS
             />
             <Button
                title="Create Account"
                onPress={() => navigation.navigate('Create Account')}
             />
        </View>
    );
}

logIn = async() => {
    try{
         let responsePromise = fetch("http://localhost:4567/logIn?username=Test1&password=Test1");
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         let parse = res.json();
         let parsed = await parse;
         if(parsed = true){
            navigation.navigate('Login')
         }

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
});