import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import {TextInput} from 'react-native';
import React, {useState} from 'react';

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

createUser = async({userN}, {pass}, {nav}, {pNum}, {dis}, {email}) => {
    try{
         let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/createUser?username=" + userN + "&password=" + pass + + "&pNum=" + pNum + "&discord=" + dis + "&email=" + email);
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
            nav.navigate('Login', {user: '' + userN})
        } else {
            alert("Username already taken");
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

    textBox: {
        backgroundColor: 'white',
        height: 40,
        width: 200,
        borderRadius: 8,
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