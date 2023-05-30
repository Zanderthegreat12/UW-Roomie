import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {getFocusedRouteNameFromRoute, useNavigation} from "@react-navigation/native";
import React, {useState, useEffect} from "react";
import {Divider} from "react-native-paper";

/**
 * functions that displays the screen to view mutual matches
 * (i.e. both users have matched with each other)
 * @param route cotains user information
 * @returns rendering of screen to see complete/mutual matches
 */
export default function ViewMatchesScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const[data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    /**
     * get all complete matches between this user and all other users
     * @param user identifier for user
     */
    let getMatches = async (user) => {
        try {
            let userNew = encodeURIComponent(user);
            let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getComplete?username=" + userNew); //HARD CODE 10 AS A GLOBAL VAR
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
                ExtraButtons.push(<Text key={-1} style={styles.text}> No Mutual  Matches at the Moment. Try again later.</Text>);
            }

            // adds all needed buttons to showing matches to screen
            while(i < parsed.length) {

                let Matchname: string;
                let comp: number;
                comp = parsed[i].compatibility

                if (parsed[i].user1 == user) {
                    Matchname = parsed[i].user2
                    if(parsed[i].matchStatus == 3) {
                        ExtraButtons.push(<View style={{ flexDirection:"row" }}><View key={i} style={styles.button2}><Button title={Matchname}
                                                                                                                             color={"#7c2bee"}
                                                                                                                             onPress={() => navigation.navigate('Match Profile', {
                                                                                                                                 user: '' + username,
                                                                                                                                 match: '' + Matchname,
                                                                                                                             })}/></View><Text style={styles.text2}>{" " + comp}</Text></View>);

                    }
                } else {
                    if(parsed[i].matchStatus == 3) {
                        Matchname = parsed[i].user1
                        ExtraButtons.push(<View style={{ flexDirection:"row" }}><View key={i} style={styles.button2}><Button title={Matchname}
                                                                                                                             color={"#7c2bee"}
                                                                                                                             onPress={() => navigation.navigate('Match Profile', {
                                                                                                                                 user: '' + username,
                                                                                                                                 match: '' + Matchname,
                                                                                                                             })}/></View><Text style={styles.text2}>{" " + comp}</Text></View>);

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

    useEffect(()=>{getMatches(username);},[]);


    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.title}>View Mutual Matches</Text>
            {!loading &&  <Text style={styles.text}> Match Name            Compatability Percent</Text>}
            <Divider style={{width:'75%'}} bold="true"/>
            {loading && <Text style={styles.text}> Loading...</Text>}
            {data}
            <Divider style={{width:'75%'}} bold="true"/>
            <View style={styles.button}>
                <Button
                    title="Back to Home"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Home', {user: '' + username})}
                />
            </View>
        </ScrollView>
    );alignItems
}

/**
 * standard purple and white style for screen to veiw possible matches
 */
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
        color: '#FFDA8F',
    },

    button: {
        padding: 10,
    },

    text2: {
        margin: 10,
        color: 'white',
        paddingTop: 10,
        fontWeight: "bold"
    },

    button2: {
        padding: 10,
        width: 250,
    },

});