import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useState} from 'react';

export default function LoginScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    return (
        <View style={styles.container}>
            <Text style={styles.text}>Welcome {username}</Text>
            <Button
                title="Matching Menu"
                onPress={() => navigation.navigate('Matching Menu', {user: '' + username,})}
            />
            <Button
                title="Liked Menu"
                onPress={() => navigation.navigate('Liked Menu', {user: '' + username,})}
            />
            <Button
                title="Survey Menu"
                onPress={() => navigation.navigate('Survey Menu', {user: '' + username,})}
            />
            <Button
                title="Profile"
                onPress={() => navigation.navigate('Profile', {user: '' + username,})}
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