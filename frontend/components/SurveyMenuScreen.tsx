import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import Dropdown from 'react-native-dropdown-picker';
import React, {useState} from 'react'
import {Image, ScrollView, FlatList, SafeAreaView} from 'react-native';
Dropdown.setListMode("SCROLLVIEW");

export default function SurveyMenuScreen() {
    function answerSurveyQ(value) {
        answersOpen = -1
        return value
    }
    const navigation = useNavigation()
    const dormNames = [
        "Alder Hall",
        "Elm Hall",
        "Hansee Hall",
        "Lander Hall",
        "Madrona Hall",
        "Maple Hall",
        "McCarty Hall",
        "McMahon Hall",
        "Oak Hall",
        "Poplar Hall",
        "Terry Hall",
        "Willow Hall",
        "Mercer Court",
        "Stevens Court"
    ]
    var answersOpen = -1
    var firstDorm = null
    var secondDorm = null
    var thirdDorm = null
    var roomType = null
    var genderInclusive = null
    var studentYear = null
    var roommateYear = null
    var drinkingPref = null
    var wakeTime = null
    var sleepTime = null
    var heavySleep = null
    var studentVert = null
    var roommateVert = null
    var studentFriends = null
    var studentNeat = null
    var roommateNeat = null
    var hobbies = null

    const [open, setOpen] = useState(false);
    const [value, setValue] = useState(null);
    const [items, setItems] = useState([
        {label: 'Alder Hall', value: 'Alder Hall'},
        {label: 'Elm Hall', value: 'Elm Hall'},
        {label: 'Hansee Hall', value: 'Hansee Hall'},
        {label: 'Madrona Hall', value: 'Madrona Hall'},
        {label: 'Maple Hall', value: 'Maple Hall'},
        {label: 'McCarty Hall', value: 'McCarty Hall'},
        {label: 'McMahon Hall', value: 'McMahon Hall'},
        {label: 'Oak Hall', value: 'Oak Hall'},
        {label: 'Poplar Hall', value: 'Poplar Hall'},
        {label: 'Terry Hall', value: 'Terry Hall'},
        {label: 'Willow Hall', value: 'Willow Hall'},
        {label: 'Mercer Court', value: 'Mercer Court'},
        {label: 'Stevens Court', value: 'Stevens Court'}
    ]);

    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text>Survey Menu</Text>
            <Button
                title="Back to Home"
                onPress={() => navigation.navigate('Login')}
            />
            <Text>What's your first dorm choice?</Text>
            <Dropdown
                open={open}
                value={value}
                items={items}
                setOpen={setOpen}
                setValue={setValue}
                setItems={setItems}
            />
        </ScrollView>
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