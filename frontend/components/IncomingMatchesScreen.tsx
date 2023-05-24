import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {getFocusedRouteNameFromRoute, useNavigation} from "@react-navigation/native";
import React, {useState, useEffect} from "react";


export default function IncomingMatchesScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const[data, setData] = useState([]);
    const [loading, setLoading] = useState(true);


    let getIncomingMatches = async (user) => {
        try {
            let userNew = encodeURIComponent(user);
            let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getIncoming?username=" + userNew); //HARD CODE 10 AS A GLOBAL VAR
            let res = await responsePromise;
            if (!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            let parse = res.json();
            let parsed = await parse;

            let ExtraButtons: any[] = [];
            let i = 0;

            if(parsed.length == 0){
                ExtraButtons.push(<Text key={-1} style={styles.text}> No Match Requests available. Check again later</Text>);
            }

            while(i < parsed.length) {

                let Matchname: string;
                let comp: number;
                comp = parsed[i].compatibility
                let status = parsed[i].matchStatus

                if (parsed[i].user1 == user) {
                    Matchname = parsed[i].user2
                    if(parsed[i].matchStatus == 2) {
                        ExtraButtons.push(<View key={i} style={styles.button}><Button title={Matchname + " " + comp}
                                                                                      color={"#7c2bee"}
                                                                                      onPress={() => navigation.navigate('Match Info', {
                                                                                          user: '' + username,
                                                                                          match: '' + Matchname,
                                                                                          comp: '' + comp,
                                                                                          status: '' + status
                                                                                      })}/></View>);
                    }
                } else {
                    if(parsed[i].matchStatus == 1) {
                        Matchname = parsed[i].user1
                        ExtraButtons.push(<View key={i} style={styles.button}><Button title={Matchname + " " + comp}
                                                                                      color={"#7c2bee"}
                                                                                      onPress={() => navigation.navigate('Match Info', {
                                                                                          user: '' + username,
                                                                                          match: '' + Matchname,
                                                                                          comp: '' + comp,
                                                                                          status: '' + status
                                                                                      })}/></View>);
                    }
                }
                i++;
            }
            setData(ExtraButtons);
            setLoading(false);

        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    useEffect(()=>{getIncomingMatches(username);},[]);


    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.title}>Matching Menu</Text>
            {loading && <Text style={styles.text}> Loading...</Text>}
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