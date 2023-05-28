import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView, Text, View, Button } from 'react-native';
import {getFocusedRouteNameFromRoute, useNavigation} from "@react-navigation/native";
import {useState, useEffect} from "react";

/**
 * Function to dispplay matching screen
 * @param route contains info about user
 * @returns rendering a screen to view possible matches
 */
export default function AlgBufferScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const [loading, setLoading] = useState(true);

    const[data, setData] = useState([]);


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
                alert("There was an error contacting the server.");
            }
            navigation.navigate('Matching Menu', {user:'' + user});

        } catch(e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    useEffect(()=>{runAlg(username);},[]);

    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.text}>Calculating Compatability. Please wait...</Text>
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

    title: {
        fontSize: 30,
        padding: 20,
        color: 'white',
    },

    button: {
        padding: 10,
    },
});