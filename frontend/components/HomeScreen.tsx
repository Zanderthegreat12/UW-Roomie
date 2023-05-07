import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';

var username = "";
var password = "";

export default function HomeScreen() {
    const navigation = useNavigation();
    return (
        <View style={styles.container}>
            <Text>UW Roomie</Text>
            <Text>Insert Username:</Text>
            <TextInput
                onChangeText={text => onChangeUser(text)}
                value={text}
            />
            <Text>Insert Password:</Text>
            <TextInput
                onChangeText={text => onChangePass(text)}
                value={text}
            />
            <Text>{username}</Text>
            <Button
                title="Sign In"
                onPress={() => navigation.navigate('Login')}//logIn()}
             />
             <Button
                title="Create Account"
                onPress={() => navigation.navigate('Create Account')}
             />
        </View>
    );
}

onChangeUser = (event: any) => {
    username = event.target.value;
}

onChangePass = (event: any) => {
    password = event.target.value;
}

logIn = async() => {
    try{
         let responsePromise = fetch("http://localhost:4567/logIn?username=Test1&password=Test1");
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         let parse = res.json();
         let parsed = await parse;
         if(parsed = true){
            navigation.navigate('Login')
         }

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