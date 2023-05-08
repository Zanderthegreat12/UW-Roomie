import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import {useState} from "react";


export default function LikedMenuScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const L = require('../Test Data/Likes.json')
    console.log(L)

    let User = "b"

    let ExtraButtons: any[] = [];
    let i = 0;

    while(i < L.Matches.length) {

        let Matchname : string;
        let comp : number;
        comp = L.Matches[i].compatability

        if(L.Matches[i].user1 == User) {
            Matchname = L.Matches[i].user2

            ExtraButtons.push(<Button title={Matchname + " " + comp}
                                      onPress={()=> navigation.navigate('Match Info', {user:'' + username, match:'' + Matchname, comp:'' + comp})}/>);
        } else {
            Matchname = L.Matches[i].user1
            ExtraButtons.push(<Button title={Matchname + " " + comp}
                                      onPress={()=> navigation.navigate('Match Info', {user: '' + username, match:'' + Matchname, comp:'' + comp})}/>);
        }
        i++;
    }

    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text>Liked Menu</Text>
            {ExtraButtons}
            <Button
            title="Back to Home"
            onPress={() => navigation.navigate('Login', {user:'' + username})}
            />
        </ScrollView>
    );alignItems
}

runAlg = async(user) => {
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

getMatches = async(user) => {
    try{
         let responsePromise = fetch("http://localhost:4567/getKmatch?username=" + user + "&numMatch=" + 10); //HARD CODE 10 AS A GLOBAL VAR
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         let parse = res.json();
         let parsed = await parse;
         //SET A VAR WITH THE MATCHES INFO

    } catch(e) {
         alert("There was an error contacting the server.");
         console.log(e);
    }
}

const styles = StyleSheet.create({
    container: {
        flexGrow: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },
});