import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button, Image } from 'react-native';
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
            <View style={{flexDirection:'row', height:'20%'}}>
                <View style={[styles.button, {flex:1,},]}>
                    <View>
                        <Image style = {styles.logo}
                            source = {require('../assets/roommate.png')}>
                        </Image>
                        <Button
                            title="Potential matches"
                            color="#7c2bee"
                            onPress={() => navigation.navigate('Matching Menu', {user: '' + username,})}
                        />
                    </View>
                </View>
                <View style={[styles.button, {flex:1,},]}>
                    <View>
                        <Image style = {styles.logo}
                            source = {require('../assets/match.png')}>
                        </Image>
                        <Button
                            title="View Matches"
                            color="#7c2bee"
                            onPress={() => navigation.navigate('View Matches Screen', {user: '' + username,})}
                        />
                    </View>
                </View>
            </View>
            <View style={{flexDirection:'row', height:'20%'}}>
                <View style={[styles.button, {flex:1,},]}>
                    <Button
                        title="View Outgoing Matches"
                        color="#7c2bee"
                        onPress={() => navigation.navigate('Outgoing Matches Screen', {user: '' + username,})}
                    />
                </View>
                <View style={[styles.button, {flex:1,},]}>
                    <View>
                        <Button
                            title="Accept/Reject Oncoming Matches"
                            color="#7c2bee"
                            onPress={() => navigation.navigate('Incoming Matches Screen', {user: '' + username,})}
                        />
                    </View>
                </View>
            </View>
            <View style={{flexDirection:'row', height:'20%'}}>
                <View style={[styles.button, {flex:1,},]}>
                    <Button
                        title="Your profile"
                        color="#7c2bee"
                        onPress={() => navigation.navigate('Profile', {user: '' + username,})}
                    />
                </View>
                <View style={[styles.button, {flex:1,},]}>
                    <Button
                        title="Log Out"
                        color="#7c2bee"
                        onPress={() => navigation.navigate('Home', {user: ''})}//navigation.replace('Home')}
                    />
                </View>
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
        justifyContent: 'center',
        flexGrow: 1,
    },

    logo: {
        width: 50,
        height: 50,
        margin: 10,
        alignSelf: 'center',
    },
});