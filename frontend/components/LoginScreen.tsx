import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useState} from 'react';

/**
 * Function to display the user's home screen
 * @param route contains information about the user
 * @returns rendering for the home screen
 */
export default function LoginScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome {username}</Text>
            <View style={styles.button}>
                <Button
                    title="Potential matches"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Matching Menu', {user: '' + username,})}
                />
            </View>
            <View style={styles.button}>
                <Button
                    title="Accept/Reject Oncoming Matches"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Incoming Matches Screen', {user: '' + username,})}
                />
            </View>
            <View style={styles.button}>
                <Button
                    title="View Outgoing Matches"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Outgoing Matches Screen', {user: '' + username,})}
                />
            </View>
            <View style={styles.button}>
                <Button
                    title="View Matches"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('View Matches Screen', {user: '' + username,})}
                />
            </View>
            <View style={styles.button}>
                <Button
                    title="Your profile"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Profile', {user: '' + username,})}
                />
            </View>
            <View style={styles.button}>
                <Button
                    title="Log Out"
                    color="#7c2bee"
                    onPress={() => navigation.replace('Home')}//navigation.navigate('Home', {user: ''})}
                />
            </View>
        </View>
    );
}

/**
 * style sheet for home screen
 */
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

    title: {
        fontSize: 30,
        padding: 10,
        color: 'white',
    },

    button: {
        padding: 10,
    },
});