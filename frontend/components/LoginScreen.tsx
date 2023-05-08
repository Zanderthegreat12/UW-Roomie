import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useState} from 'react';

export default function LoginScreen({route}) {
    const navigation = useNavigation();
    const [username, setUser] = useState(route.params.user);

    return (
        <View style={styles.container}>
            <Text>Welcome {username}</Text>
            <Button
                title="Matching Menu"
                onPress={() => navigation.navigate('Matching Menu')}
            />
            <Button
                title="Survey Menu"
                onPress={() => navigation.navigate('Survey Menu')}
            />
            <Button
                title="Profile"
                onPress={() => navigation.navigate('Profile')}
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