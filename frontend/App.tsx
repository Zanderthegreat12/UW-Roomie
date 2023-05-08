import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';

import HomeScreen from './components/HomeScreen';
import LoginScreen from './components/LoginScreen';
import MatchingMenuScreen from './components/MatchingMenuScreen';
import SurveyMenuScreen from "./components/SurveyMenuScreen";
import LikedMenuScreen from "./components/LikedMenuScreen";
import CreateUserScreen from "./components/CreateUserScreen";
import ProfileScreen from "./components/ProfileScreen";
import MatchInfoScreen from "./components/MatchInfoScreen";

const stack = createStackNavigator();

export default function App() {
  return (
    <>
    <NavigationContainer>
        <stack.Navigator>
            <stack.Screen
                name="Home"
                component={HomeScreen} />
            <stack.Screen
                name="Login"
                component={LoginScreen} />
            <stack.Screen
                name="Create Account"
                component={CreateUserScreen} />
            <stack.Screen
                name="Matching Menu"
                component={MatchingMenuScreen} />
            <stack.Screen
                name="Survey Menu"
                component={SurveyMenuScreen} />
            <stack.Screen
                name="Liked Menu"
                component={LikedMenuScreen} />
            <stack.Screen
                name="Profile"
                component={ProfileScreen} />
            <stack.Screen
                name="Match Info"
                component={MatchInfoScreen} />
        </stack.Navigator>

    </NavigationContainer></>
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
