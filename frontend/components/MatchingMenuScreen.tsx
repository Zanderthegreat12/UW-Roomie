import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {getFocusedRouteNameFromRoute, useNavigation} from "@react-navigation/native";
import {useState, useEffect} from "react";


export default function MatchingMenuScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const[data, setData] = useState([]);

    let AlgMatch = async(user) => {
        let a = await runAlg(user);
        let b = await getMatches(user);
    }

    let runAlg = async(user) => {
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

    let getMatches = async (user) => {
        try {
            let responsePromise = fetch("http://localhost:4567/getKmatch?username=" + user + "&numMatch=" + 10); //HARD CODE 10 AS A GLOBAL VAR
            let res = await responsePromise;
            if (!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            let parse = res.json();
            let parsed = await parse;
            //SET A VAR WITH THE MATCHES INFO

        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);

            const M = require('../Test Data/Matches.json')
            console.log(M)

            let User = "b"

            let ExtraButtons: any[] = [];
            let i = 0;

            while(i < M.Matches.length) {

                let Matchname : string;
                let comp : number;
                comp = M.Matches[i].compatability

                if(M.Matches[i].user1 == User) {
                    Matchname = M.Matches[i].user2

                    ExtraButtons.push(<View style={styles.button}><Button title={Matchname + " " + comp}
                                                                          color={"#7c2bee"}
                                                                          onPress={()=> navigation.navigate('Match Info', {user: '' + username, match: '' + Matchname, comp:'' + comp})}/></View>);
                } else {
                    Matchname = M.Matches[i].user1
                    ExtraButtons.push(<View style={styles.button}><Button title={Matchname + " " + comp}
                                                                          color={"#7c2bee"}
                                                                          onPress={()=> navigation.navigate('Match Info', {user:'' + username, match:'' + Matchname, comp:'' + comp})}/></View>);
                }
                i++;
            }
            setData(ExtraButtons);
        }
    }

    useEffect(()=>{AlgMatch(username);},[]);


    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.title}>Matching Menu</Text>
            {data}
            <View style={styles.button}>
                <Button
                    title="Back to Home"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Login', {user: '' + username})}
                />
            </View>
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


const styles = StyleSheet.create({
    container: {
        flexGrow: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },

    text: {
        margin: 10,
        color: 'white',
    },

    title: {
        fontSize: 30,
        padding: 20,
        color: 'white',
    },

    button: {
        padding: 10,
    },
});