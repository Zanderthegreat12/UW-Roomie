import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useEffect, useState} from 'react';

export default function MatchInfoScreen({route}) {
    const navigation = useNavigation();

    const [username, setUser] = useState(route.params.user);
    const [matchname, setMatchName] = useState(route.params.match);
    const [comp, setComp] = useState(route.params.comp);

    const[data, setData] = useState([]);

    let getContact = async(user) => {
        try{
             let responsePromise = fetch("http://localhost:4567/runAlg?username=" + user);
             let res = await responsePromise;
             if(!res.ok){
                alert("Error! Expected: 200, Was: " + res.status);
               return;
             }
           } catch(e) {
             alert("There was an error contacting the server.");
             console.log(e);
        }
    }

    useEffect(()=>{getContact(username);},[]);


    return (
        <View style={styles.container}>
            <Text style={styles.text}> {username + " " + matchname + " " + comp }</Text>
            <Button
                title="Back to all Matches"
                color="#7c2bee"
                onPress={() => navigation.navigate('Matching Menu', {user: '' + username})}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },

    text: {
        margin: 10,
        color: 'white',
    },
});