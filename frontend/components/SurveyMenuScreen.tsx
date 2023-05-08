import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import Dropdown from 'react-native-dropdown-picker';
import React, {useState} from 'react'
import {Image, ScrollView, FlatList, SafeAreaView} from 'react-native';
Dropdown.setListMode("SCROLLVIEW");

export default function SurveyMenuScreen({route}) {
    const [username, setUser] = useState(route.params.user);
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

    const [firstDormOpen, setFirstDormOpen] = useState(false);
    const [firstDormValue, setFirstDormValue] = useState(null);
    const [secondDormOpen, setSecondDormOpen] = useState(false);
    const [secondDormValue, setSecondDormValue] = useState(null);
    const [thirdDormOpen, setThirdDormOpen] = useState(false);
    const [thirdDormValue, setThirdDormValue] = useState(null);
    const [dorms, setDorms] = useState([
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

    const [roomTypeOpen, setRoomTypeOpen] = useState(false);
    const [roomTypeValue, setRoomTypeValue] = useState(null);
    const [roomTypes, setRoomTypes] = useState([
        {label: 'single/studio', value: 1},
        {label: 'double/2 bedrooms', value: 2},
        {label: 'triple/3 bedrooms', value: 3},
        {label: 'quad suite/4 bedrooms', value: 4},
        {label: '5 bedrooms', value: 5},
        {label: '6 bedrooms', value: 6},
    ]);

    const [genderInclusiveOpen, setGenderInclusiveOpen] = useState(false);
    const [genderInclusiveValue, setGenderInclusiveValue] = useState(null);
    const [genderInclusivity, setGenderInclusivity] = useState([
        {label: 'Yes. I want to be in gender inclusive dorming', value: 1},
        {label: 'No. I don\'t want to be in gender inclusive dorming', value: 0},
    ]);

    const open = [setFirstDormOpen, setSecondDormOpen, setThirdDormOpen, setRoomTypeOpen,
    setGenderInclusiveOpen]
    function onOpen(openedValue: number): void {
        for(let i = 0; i < open.length; i++) {
            if(openedValue != i) {
                open[i](false)
            } else {
                open[i](true)
            }
        }
    }

    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text>Survey Menu</Text>
            <Button
                title="Back to Home"
                onPress={() => navigation.navigate('Login')}
            />
            <Text> {'\n'} </Text>
            <Text>What's your first dorm choice?</Text>
            <Dropdown open={firstDormOpen}
                value={firstDormValue}
                items={dorms}
                setOpen={setFirstDormOpen}
                setValue={setFirstDormValue}
                setItems={setDorms}
                onOpen={() => onOpen(0)}
                onClose={() => onOpen(-1)}
                dropDownDirection="TOP"
            />

            <Text> {'\n'} </Text>
            <Text>What's your second dorm choice?</Text>
            <Dropdown open={secondDormOpen}
                value={secondDormValue}
                items={dorms}
                setOpen={setSecondDormOpen}
                setValue={setSecondDormValue}
                setItems={setDorms}
                onOpen={() => onOpen(1)}
                onClose={() => onOpen(-1)}
                dropDownDirection="TOP"
            />

            <Text> {'\n'} </Text>
            <Text>What's your third dorm choice?</Text>
            <Dropdown open={thirdDormOpen}
                value={thirdDormValue}
                items={dorms}
                setOpen={setThirdDormOpen}
                setValue={setThirdDormValue}
                setItems={setDorms}
                onOpen={() => onOpen(2)}
                onClose={() => onOpen(-1)}
                dropDownDirection="TOP"
            />

            <Text> {'\n'} </Text>
            <Text>What type of dorm room do you want?</Text>
            <Dropdown open={roomTypeOpen}
                      value={roomTypeValue}
                      items={roomTypes}
                      setOpen={setRoomTypeOpen}
                      setValue={setRoomTypeValue}
                      setItems={setRoomTypes}
                      onOpen={() => onOpen(3)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="TOP"
            />

            <Text> {'\n'} </Text>
            <Text>Do you want to opt into gender inclusive dorming?</Text>
            <Dropdown open={genderInclusiveOpen}
                      value={genderInclusiveValue}
                      items={genderInclusivity}
                      setOpen={setGenderInclusiveOpen}
                      setValue={setGenderInclusiveValue}
                      setItems={setGenderInclusivity}
                      onOpen={() => onOpen(3)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="TOP"
            />

            <Text> {'\n'} </Text>
            <Text>What type of dorm room do you want?</Text>
            <Dropdown open={roomTypeOpen}
                      value={roomTypeValue}
                      items={roomTypes}
                      setOpen={setRoomTypeOpen}
                      setValue={setRoomTypeValue}
                      setItems={setRoomTypes}
                      onOpen={() => onOpen(3)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="TOP"
            />
        </ScrollView>
    );
}

getSurvey = async(infoString) => {
    try{
         let responsePromise = fetch("http://localhost:4567/createSurvey?str=" + infoString);
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return;
         }

         //Survey filled out and uploaded! Go to the profile screen!
         navigation.navigate('Profile', {user: '' + username,})

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
        zIndex: 4
    },
});