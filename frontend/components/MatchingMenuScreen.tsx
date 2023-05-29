import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {getFocusedRouteNameFromRoute, useNavigation} from "@react-navigation/native";
import {useState, useEffect} from "react";

/**
 * Function to dispplay matching screen
 * @param route contains info about user
 * @returns rendering a screen to view possible matches
 */
export default function MatchingMenuScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);
    const [loading, setLoading] = useState(true);

    const[data, setData] = useState([]);

    /**
     * Finds top matches for given user
     * @param user username of current user
     */
    let AlgMatch = async(user) => {
        //let a = await runAlg(user);
        let b = await getMatches(user);
        setLoading(false);
    }

    /**
     * calculates the new compatibility with given user will all other users
     * @param user username of current user
     */
    let runAlg = async(user) => {
        try{
            let userNew = encodeURIComponent(user);
            let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/runAlg?username=" + userNew);
            let res = await responsePromise;
            if(!res.ok){
                return;
            }
        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    /**
     * retrives the top matches for the given user (defined as having the best compatibility)
     * @param user username of current user
     */
    let getMatches = async (user) => {
        try {
            let userNew = encodeURIComponent(user);
            let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/getKmatch?username=" + userNew + "&numMatch=" + 10); //HARD CODE 10 AS A GLOBAL VAR
            let res = await responsePromise;
            if (!res.ok) {
                alert("Error! Expected: 200, Was: " + res.status);
                return;
            }

            let parse = res.json();
            let parsed = await parse;
            //SET A VAR WITH THE MATCHES INFO

            let ExtraButtons: any[] = [];
            let i = 0;

            if(parsed.length == 0){
                ExtraButtons.push(<Text key={-1} style={styles.text}> No Matches at the Moment. Try again later.</Text>);
                ExtraButtons.push(<Text key={-2} style={styles.text}>If you haven't filled out Survey please do so </Text>);
            }

            while(i < parsed.length) {

                let Matchname: string;
                let comp: number;
                comp = parsed[i].compatibility
                let status = parsed[i].matchStatus

                if (parsed[i].user1 == user) {
                    Matchname = parsed[i].user2

                    ExtraButtons.push(<View style={{ flexDirection:"row" }}><View key={i} style={styles.button2}><Button title={Matchname}
                                                                                  color={"#7c2bee"}
                                                                                  onPress={() => navigation.navigate('Match Info', {
                                                                                      user: '' + username,
                                                                                      match: '' + Matchname,
                                                                                      comp: '' + comp,
                                                                                      status: ''+ status,
                                                                                      screen: ''+1
                                                                                  })}/></View><Text style={styles.text2}>{" " + comp}</Text></View>);
                } else {
                    Matchname = parsed[i].user1
                    ExtraButtons.push(<View style={{ flexDirection:"row" }}><View key={i} style={styles.button2}><Button title={Matchname}
                                                                                                                         color={"#7c2bee"}
                                                                                                                         onPress={() => navigation.navigate('Match Info', {
                                                                                                                             user: '' + username,
                                                                                                                             match: '' + Matchname,
                                                                                                                             comp: '' + comp,
                                                                                                                             status: ''+ status,
                                                                                                                             screen: ''+1
                                                                                                                         })}/></View><Text style={styles.text2}>{" " + comp}</Text></View>);
                }
                i++;
            }
            setData(ExtraButtons);

        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    useEffect(()=>{AlgMatch(username);},[]);
    useEffect(() => {
        const focusHandler = navigation.addListener('focus', () => {
            AlgMatch(username)
        });
        return focusHandler;
    }, [navigation]);

    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.title}>Matching Menu</Text>
            <View style={styles.button}>
                <Button
                    title="Refresh"
                    color="#7c2bee"
                    onPress={() => getMatches(username)}
                />
            </View>
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

/**
 * style for matching screen
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

    text2: {
        margin: 10,
        color: 'white',
        paddingTop: 10,
        fontWeight: "bold"
    },

    title: {
        fontSize: 30,
        padding: 20,
        color: 'white',
    },

    button: {
        padding: 10,
    },

    button2: {
        padding: 10,
        width: 250
    },
});