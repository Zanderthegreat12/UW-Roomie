import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';

import HomeScreen from './components/HomeScreen';
import LoginScreen from './components/LoginScreen';

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
