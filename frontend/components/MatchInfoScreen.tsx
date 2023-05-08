import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useState} from 'react';

export default function MatchInfoScreen({route}) {
    const navigation = useNavigation();

    const [username, setUser] = useState(route.params.user);
    const [matchname, setMatchName] = useState(route.params.match);
    const [comp, setComp] = useState(route.params.comp);

    return (
        <View style={styles.container}>
            <Text style={styles.text}> {username + " " + matchname + " " + comp }</Text>
            <Button
                title="Back to all Matches"
                color="#7c2bee"
                onPress={() => navigation.navigate('Matching Menu', {user: '' + username})}
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

    text: {
        margin: 10,
        color: 'white',
    },
});