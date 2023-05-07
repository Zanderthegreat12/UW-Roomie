import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';

createAccount = async() => {
    try{
         let responsePromise = fetch("http://localhost:4567/createUser?username=Test1&password=Test1");
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }
            alert("Successfully created your new account!");
            navigation.navigate('Login');
    } catch(e) {
         alert("There was an error contacting the server.");
         console.log(e);
    }
}

export default function HomeScreen() {
    const navigation = useNavigation();
    return (
        <View style={styles.container}>
            <Text>UW Roomie</Text>
            <Button
                title="Sign In"
                onPress={() => navigation.navigate('Login')}//logIn()}
             />
             <Button
                title="Create Account"
                onPress={() => createAccount()}
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