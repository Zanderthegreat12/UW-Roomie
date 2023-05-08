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
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
    },
});