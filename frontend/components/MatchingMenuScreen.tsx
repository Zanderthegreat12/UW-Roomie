import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";

export default function MatchingMenuScreen() {
    const navigation = useNavigation()
    return (
        <View style={styles.container}>
            <Text>Matching Menu</Text>
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