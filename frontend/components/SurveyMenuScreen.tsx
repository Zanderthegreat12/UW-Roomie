import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import Dropdown from 'react-native-dropdown-picker';
import React, {useState} from 'react'
import {Image, ScrollView, FlatList, SafeAreaView} from 'react-native';
Dropdown.setListMode("SCROLLVIEW")

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

    const [studentYearOpen, setStudentYearOpen] = useState(false);
    const [studentYearValue, setStudentYearValue] = useState(null);
    const [studentYears, setStudentYears] = useState([
        {label: '1st year', value: 1},
        {label: '2nd year', value: 2},
        {label: '3rd year', value: 3},
        {label: '4th year', value: 4},
    ]);

    const [roommateYearOpen, setRoommateYearOpen] = useState(false);
    const [roommateYearValue, setRoommateYearValue] = useState(null);
    const [roommateYears, setRoommateYears] = useState([
        {label: '1st year', value: 1},
        {label: '2nd year', value: 2},
        {label: '3rd year', value: 3},
        {label: '4th year', value: 4},
        {label: 'don\'t care', value: -1}
    ]);

    const [drinkingPrefOpen, setDrinkingPrefOpen] = useState(false);
    const [drinkingPrefValue, setDrinkingPrefValue] = useState(null);
    const [drinkingPref, setDrinkingPref] = useState([
        {label: 'I don\'t want my roommate to drink', value: 0},
        {label: 'I\'m cool with roommate drinking', value: 1},
    ]);

    const [wakeTimeOpen, setWakeTimeOpen] = useState(false);
    const [wakeTimeValue, setWakeTimeValue] = useState(null);
    const [wakeTimes, setWakeTimes] = useState([
        {label: '6:00 am - 7:00 am', value: 6},
        {label: '7:00 am - 8:00 am', value: 7},
        {label: '8:00 am - 9:00 am', value: 8},
        {label: '9:00 am - 10:00 am', value: 9},
        {label: '10:00 am - 11:00 am', value: 10},
        {label: '11:00 am - 12:00 pm', value: 11},
        {label: '12:00 pm - 1:00 pm', value: 12},
    ]);

    const [sleepTimeOpen, setSleepTimeOpen] = useState(false);
    const [sleepTimeValue, setSleepTimeValue] = useState(null);
    const [sleepTimes, setSleepTimes] = useState([
        {label: '9:00 pm - 10:00 pm', value: 6},
        {label: '10:00 pm - 11:00 pm', value: 7},
        {label: '11:00 pm - 12:00 am', value: 8},
        {label: '12:00 am - 1:00 am', value: 9},
        {label: '1:00 am - 2:00 am', value: 10},
        {label: '2:00 am - 3:00 am', value: 11},
        {label: '3:00 am- 4:00 am', value: 12},
    ]);

    const [heavySleepOpen, setHeavySleepOpen] = useState(false);
    const [heavySleepValue, setHeavySleepValue] = useState(null);
    const [heavySleep, setHeavySleep] = useState([
        {label: 'I\'m a light sleeper', value: 0},
        {label: 'I\'m a heavy sleeper', value: 1},
    ]);

    const [studentVertOpen, setStudentVertOpen] = useState(false);
    const [studentVertValue, setStudentVertValue] = useState(null);
    const [studentVerts, setStudentVerts] = useState([
        {label: 'I\'m an introvert', value: 0},
        {label: 'I\'m an ambivert', value: 1},
        {label: 'I\'m an extrovert', value: 2}
    ]);

    const [roommateVertOpen, setRoommateVertOpen] = useState(false);
    const [roommateVertValue, setRoommateVertValue] = useState(null);
    const [roommateVerts, setRoommateVerts] = useState([
        {label: 'I prefer my roommate be an introvert', value: 0},
        {label: 'I prefer my roommate be an ambivert', value: 1},
        {label: 'I prefer myu roommate be an extrovert', value: 2},
        {label: 'I don\'t care', value: -1}
    ]);

    const [studentFriendsOpen, setStudentFriendsOpen] = useState(false);
    const [studentFriendsValue, setStudentFriendsValue] = useState(null);
    const [studentFriends, setStudentFriends] = useState([
        {label: 'I won\'t bring friends to the dorm room', value: 0},
        {label: 'I will want to bring friends to the dorm room', value: 1}
    ]);

    const [roommateFriendsOpen, setRoommateFriendsOpen] = useState(false);
    const [roommateFriendsValue, setRoommateFriendsValue] = useState(null);
    const [roommateFriends, setRoommateFriends] = useState([
        {label: 'I don\'t want my roommate bringing friends to the dorm', value: 0},
        {label: 'I\'m cool with my roommate bringing friends to the dorm', value: 1}
    ]);

    const [studentNeatOpen, setStudentNeatOpen] = useState(false);
    const [studentNeatValue, setStudentNeatValue] = useState(null);
    const [studentNeat, setStudentNeat] = useState([
        {label: 'I\'m messy and disorganized', value: 0},
        {label: 'I\'m clean, neat, and organized', value: 1}
    ]);

    const [roommateNeatOpen, setRoommateNeatOpen] = useState(false);
    const [roommateNeatValue, setRoommateNeatValue] = useState(null);
    const [roommateNeat, setRoommateNeat] = useState([
        {label: 'I\'m fine with my roommate being messy and disorganized', value: 0},
        {label: 'I want my roommate to be clean, neat, and organized', value: 1}
    ]);

    const open = [setFirstDormOpen, setSecondDormOpen, setThirdDormOpen, setRoomTypeOpen,
        setGenderInclusiveOpen, setStudentYearOpen, setRoommateYearOpen, setDrinkingPrefOpen,
        setWakeTimeOpen, setSleepTimeOpen, setHeavySleepOpen, setStudentVertOpen,
        setRoommateVertOpen, setStudentFriendsOpen, setRoommateFriendsOpen,
        setStudentNeatOpen, setRoommateNeatOpen]
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
            <Text style={styles.title}>Roomie Survey</Text>
            <Text style={styles.text} paddingBottom={15}>Answer these survey questions to get the best matches for you!</Text>
            <Button
                title="Back to Home"
                color="#7c2bee"
                onPress={() => navigation.navigate('Login', {user:'' + username})}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What's your first dorm choice?</Text>
            <Dropdown open={firstDormOpen}
                      value={firstDormValue}
                      items={dorms}
                      placeholder={"Select a dorm"}
                      setOpen={setFirstDormOpen}
                      setValue={setFirstDormValue}
                      setItems={setDorms}
                      onOpen={() => onOpen(0)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: firstDormOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What's your second dorm choice?</Text>
            <Dropdown open={secondDormOpen}
                      value={secondDormValue}
                      items={dorms}
                      placeholder={"Select a dorm"}
                      setOpen={setSecondDormOpen}
                      setValue={setSecondDormValue}
                      setItems={setDorms}
                      onOpen={() => onOpen(1)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: secondDormOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What's your third dorm choice?</Text>
            <Dropdown open={thirdDormOpen}
                      value={thirdDormValue}
                      items={dorms}
                      placeholder={"Select a dorm"}
                      setOpen={setThirdDormOpen}
                      setValue={setThirdDormValue}
                      setItems={setDorms}
                      onOpen={() => onOpen(2)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: thirdDormOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What type of dorm room do you want?</Text>
            <Dropdown open={roomTypeOpen}
                      value={roomTypeValue}
                      items={roomTypes}
                      placeholder={"Select a room type"}
                      setOpen={setRoomTypeOpen}
                      setValue={setRoomTypeValue}
                      setItems={setRoomTypes}
                      onOpen={() => onOpen(3)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roomTypeOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you want to opt into gender inclusive dorming?</Text>
            <Dropdown open={genderInclusiveOpen}
                      value={genderInclusiveValue}
                      items={genderInclusivity}
                      placeholder={"Select yes or no"}
                      setOpen={setGenderInclusiveOpen}
                      setValue={setGenderInclusiveValue}
                      setItems={setGenderInclusivity}
                      onOpen={() => onOpen(4)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: genderInclusiveOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What year will you be when dorming?</Text>
            <Dropdown open={studentYearOpen}
                      value={studentYearValue}
                      items={studentYears}
                      placeholder={"Select a year"}
                      setOpen={setStudentYearOpen}
                      setValue={setStudentYearValue}
                      setItems={setStudentYears}
                      onOpen={() => onOpen(5)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentYearOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What year do you prefer that your roommate be?</Text>
            <Dropdown open={roommateYearOpen}
                      value={roommateYearValue}
                      items={roommateYears}
                      placeholder={"Select a year"}
                      setOpen={setRoommateYearOpen}
                      setValue={setRoommateYearValue}
                      setItems={setRoommateYears}
                      onOpen={() => onOpen(6)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateYearOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you care if your roommate drinks alcohol?</Text>
            <Dropdown open={drinkingPrefOpen}
                      value={drinkingPrefValue}
                      items={drinkingPref}
                      placeholder={"Select yes or no"}
                      setOpen={setDrinkingPrefOpen}
                      setValue={setDrinkingPrefValue}
                      setItems={setDrinkingPref}
                      onOpen={() => onOpen(7)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: drinkingPrefOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What time do you wake up?</Text>
            <Dropdown open={wakeTimeOpen}
                      value={wakeTimeValue}
                      items={wakeTimes}
                      placeholder={"Select a time range"}
                      setOpen={setWakeTimeOpen}
                      setValue={setWakeTimeValue}
                      setItems={setWakeTimes}
                      onOpen={() => onOpen(8)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: wakeTimeOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>What time do you go to sleep?</Text>
            <Dropdown open={sleepTimeOpen}
                      value={sleepTimeValue}
                      items={sleepTimes}
                      placeholder={"Select a time range"}
                      setOpen={setSleepTimeOpen}
                      setValue={setSleepTimeValue}
                      setItems={setSleepTimes}
                      onOpen={() => onOpen(9)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: sleepTimeOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Are you a heavy sleeper or a light sleeper?</Text>
            <Dropdown open={heavySleepOpen}
                      value={heavySleepValue}
                      items={heavySleep}
                      placeholder={"Select your sleep style"}
                      setOpen={setHeavySleepOpen}
                      setValue={setHeavySleepValue}
                      setItems={setHeavySleep}
                      onOpen={() => onOpen(10)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: heavySleepOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Would you classify yourself as an introvert, ambivert, or extrovert?</Text>
            <Dropdown open={studentVertOpen}
                      value={studentVertValue}
                      items={studentVerts}
                      placeholder={"Select your personality type"}
                      setOpen={setStudentVertOpen}
                      setValue={setStudentVertValue}
                      setItems={setStudentVerts}
                      onOpen={() => onOpen(11)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentVertOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Would you prefer your roommate be an introvert, ambivert, or extrovert?</Text>
            <Dropdown open={roommateVertOpen}
                      value={roommateVertValue}
                      items={roommateVerts}
                      placeholder={"Select a personality type"}
                      setOpen={setRoommateVertOpen}
                      setValue={setRoommateVertValue}
                      setItems={setRoommateVerts}
                      onOpen={() => onOpen(12)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateVertOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Will you bring your friends to the dorm?</Text>
            <Dropdown open={studentFriendsOpen}
                      value={studentFriendsValue}
                      items={studentFriends}
                      placeholder={"Select yes or no"}
                      setOpen={setStudentFriendsOpen}
                      setValue={setStudentFriendsValue}
                      setItems={setStudentFriends}
                      onOpen={() => onOpen(13)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentFriendsOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>How do you feel about your roommate bringing friends the the dorm?</Text>
            <Dropdown open={roommateFriendsOpen}
                      value={roommateFriendsValue}
                      items={roommateFriends}
                      placeholder={"Select your comfortableness"}
                      setOpen={setRoommateFriendsOpen}
                      setValue={setRoommateFriendsValue}
                      setItems={setRoommateFriends}
                      onOpen={() => onOpen(14)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateFriendsOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you consider yourself neat or messy?</Text>
            <Dropdown open={studentNeatOpen}
                      value={studentNeatValue}
                      items={studentNeat}
                      placeholder={"Select neat or messy"}
                      setOpen={setStudentNeatOpen}
                      setValue={setStudentNeatValue}
                      setItems={setStudentNeat}
                      onOpen={() => onOpen(15)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentNeatOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you care if your roommate is neat or messy?</Text>
            <Dropdown open={roommateNeatOpen}
                      value={roommateNeatValue}
                      items={roommateNeat}
                      placeholder={"Select yes or no"}
                      setOpen={setRoommateNeatOpen}
                      setValue={setRoommateNeatValue}
                      setItems={setRoommateNeat}
                      onOpen={() => onOpen(16)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateNeatOpen ? 175 : 20}}
            />

            <View style={styles.button} paddingBottom={50}>
                <Button
                    title="Submit"
                    color="#7c2bee"
                    onPress={() => navigation.navigate('Matching Menu', {user:'' + username})}
                />
            </View>
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
        flexGrow: 1,
        backgroundColor: '#A781B5',
        alignItems: 'center',
        justifyContent: 'center',
        zIndex: 4
    },

    text: {
        margin: 10,
        color: 'white',
    },

    title: {
        fontSize: 30,
        padding: 20,
        paddingBottom: 0,
        color: 'white',
    },

    button: {
        padding: 20,
    },
});