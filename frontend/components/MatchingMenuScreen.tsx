import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";


export default function MatchingMenuScreen() {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    const M = require('../Test Data/Matches.json')
    console.log(M)

    let User = "b"

    let ExtraButtons: any[] = [];
    let i = 0;

    while(i < M.Matches.length) {

        if(M.Matches[i].user1 == User) {
            ExtraButtons.push(<Text> {M.Matches[i].user2 + " " + M.Matches[i].compatability}</Text>);
        } else {
            ExtraButtons.push(<Text> {M.Matches[i].user1 + " " + M.Matches[i].compatability}</Text>);
        }
        i++;
    }

    return (
        <View style={styles.container}>
            <Text>Matching Menu</Text>
            {ExtraButtons}
            <Button
            title="Back to Home"
            onPress={() => navigation.navigate('Login')}
            />
        </View>
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
        flex: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },
});