import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import Dropdown from 'react-native-dropdown-picker';
import { RadioButton, Divider } from 'react-native-paper';
import React, {useMemo, useState} from 'react';
import {Image, ScrollView, FlatList, SafeAreaView} from 'react-native';
Dropdown.setListMode("SCROLLVIEW")

/**
 * Function to display survey screen
 * @param route contains info about user
 * @returns rendering for the survey screen
 */
export default function SurveyMenuScreen({route}) {
    const [username, setUser] = useState(route.params.user);
    const [survey, setSurvey] = useState(route.params.survey)
    const navigation = useNavigation()
    var answersOpen = -1

    // initialize dropdown values
    var firstDormInit = null
    var secondDormInit = null
    var thirdDormInit = null
    var roomTypeInit = null
    var genderInclusiveInit = null
    var studentYearInit = null
    var roommateYearInit = null
    var drinkingPrefInit = null
    var wakeTimeInit = null
    var sleepTimeInit = null
    var heavySleepInit = null
    var studentVertInit = null
    var roommateVertInit = null
    var studentFriendsInit = null
    var roommateFriendsInit = null
    var studentNeatInit = null
    var roommateNeatInit = null
    var buttonStatus = true;

    if (survey !== null) {
        firstDormInit = survey.firstDorm
        secondDormInit = survey.secondDorm
        thirdDormInit = survey.thirdDorm
        roomTypeInit = survey.roomType
        genderInclusiveInit = survey.genderInclusive
        studentYearInit = survey.studentYear
        roommateYearInit = survey.roommateYear
        drinkingPrefInit = survey.drinkingPref
        wakeTimeInit = survey.wakeTime
        sleepTimeInit = survey.wakeTime
        heavySleepInit = survey.heavySleep
        studentVertInit = survey.studentVert
        roommateVertInit = survey.roommateVert
        studentFriendsInit = survey.studentFriends
        roommateFriendsInit = survey.roommateFriends
        studentNeatInit = survey.studentNeat
        roommateNeatInit = survey.roommateNeat
        buttonStatus = false;
    }


    // Dorm ranking state
    const [firstDormOpen, setFirstDormOpen] = useState(false);
    const [firstDormValue, setFirstDormValue] = useState(firstDormInit);
    const [secondDormOpen, setSecondDormOpen] = useState(false);
    const [secondDormValue, setSecondDormValue] = useState(secondDormInit);
    const [thirdDormOpen, setThirdDormOpen] = useState(false);
    const [thirdDormValue, setThirdDormValue] = useState(thirdDormInit);
    const [dorms, setDorms] = useState([
        {label: 'Alder Hall', value: 'Alder-Hall'},
        {label: 'Elm Hall', value: 'Elm-Hall'},
        {label: 'Hansee Hall', value: 'Hansee-Hall'},
        {label: 'Lander Hall', value: 'Lander-Hall'},
        {label: 'Madrona Hall', value: 'Madrona-Hall'},
        {label: 'Maple Hall', value: 'Maple-Hall'},
        {label: 'McCarty Hall', value: 'McCarty-Hall'},
        {label: 'McMahon Hall', value: 'McMahon-Hall'},
        {label: 'Oak Hall', value: 'Oak-Hall'},
        {label: 'Poplar Hall', value: 'Poplar-Hall'},
        {label: 'Terry Hall', value: 'Terry-Hall'},
        {label: 'Willow Hall', value: 'Willow-Hall'},
        {label: 'Mercer Court', value: 'Mercer-Court'},
        {label: 'Stevens Court', value: 'Stevens-Court'}
    ]);

    // room type question state
    const [roomTypeOpen, setRoomTypeOpen] = useState(false);
    const [roomTypeValue, setRoomTypeValue] = useState(roomTypeInit);
    const [roomTypes, setRoomTypes] = useState([
        {label: 'single/studio', value: 1},
        {label: 'double/2 bedrooms', value: 2},
        {label: 'triple/3 bedrooms', value: 3},
        {label: 'quad suite/4 bedrooms', value: 4},
        {label: '5 bedrooms', value: 5},
        {label: '6 bedrooms', value: 6},
    ]);

    // gender inclusive state
    const [genderInclusiveValue, setGenderInclusiveValue] = useState(genderInclusiveInit);

    // student year state
    const [studentYearOpen, setStudentYearOpen] = useState(false);
    const [studentYearValue, setStudentYearValue] = useState(studentYearInit);
    const [studentYears, setStudentYears] = useState([
        {label: '1st year', value: 1},
        {label: '2nd year', value: 2},
        {label: '3rd year', value: 3},
        {label: '4th year', value: 4},
    ]);

    // roommate year state
    const [roommateYearOpen, setRoommateYearOpen] = useState(false);
    const [roommateYearValue, setRoommateYearValue] = useState(roommateYearInit);
    const [roommateYears, setRoommateYears] = useState([
        {label: '1st year', value: 1},
        {label: '2nd year', value: 2},
        {label: '3rd year', value: 3},
        {label: '4th year', value: 4},
        {label: 'don\'t care', value: -1}
    ]);

    // drinking preference state
    const [drinkingPrefValue, setDrinkingPrefValue] = useState(drinkingPrefInit);

    // wake time state
    const [wakeTimeOpen, setWakeTimeOpen] = useState(false);
    const [wakeTimeValue, setWakeTimeValue] = useState(wakeTimeInit);
    const [wakeTimes, setWakeTimes] = useState([
        {label: '6:00 am - 7:00 am', value: 6},
        {label: '7:00 am - 8:00 am', value: 7},
        {label: '8:00 am - 9:00 am', value: 8},
        {label: '9:00 am - 10:00 am', value: 9},
        {label: '10:00 am - 11:00 am', value: 10},
        {label: '11:00 am - 12:00 pm', value: 11},
        {label: '12:00 pm - 1:00 pm', value: 12},
    ]);

    // sleep time state
    const [sleepTimeOpen, setSleepTimeOpen] = useState(false);
    const [sleepTimeValue, setSleepTimeValue] = useState(sleepTimeInit);
    const [sleepTimes, setSleepTimes] = useState([
        {label: '9:00 pm - 10:00 pm', value: 6},
        {label: '10:00 pm - 11:00 pm', value: 7},
        {label: '11:00 pm - 12:00 am', value: 8},
        {label: '12:00 am - 1:00 am', value: 9},
        {label: '1:00 am - 2:00 am', value: 10},
        {label: '2:00 am - 3:00 am', value: 11},
        {label: '3:00 am- 4:00 am', value: 12},
    ]);

    // light/heavy sleeper state
    const [heavySleepValue, setHeavySleepValue] = useState(heavySleepInit);

    // student vertness state
    const [studentVertOpen, setStudentVertOpen] = useState(false);
    const [studentVertValue, setStudentVertValue] = useState(studentVertInit);
    const [studentVerts, setStudentVerts] = useState([
        {label: 'I\'m an introvert', value: 0},
        {label: 'I\'m an ambivert', value: 1},
        {label: 'I\'m an extrovert', value: 2}
    ]);

    // roommate vertness state
    const [roommateVertOpen, setRoommateVertOpen] = useState(false);
    const [roommateVertValue, setRoommateVertValue] = useState(roommateVertInit);
    const [roommateVerts, setRoommateVerts] = useState([
        {label: 'I prefer my roommate be an introvert', value: 0},
        {label: 'I prefer my roommate be an ambivert', value: 1},
        {label: 'I prefer my roommate be an extrovert', value: 2},
        {label: 'I don\'t care', value: -1}
    ]);

    // student friends state
    const [studentFriendsValue, setStudentFriendsValue] = useState(studentFriendsInit);

    // roommate friends state
    const [roommateFriendsValue, setRoommateFriendsValue] = useState(studentFriendsInit);

    // student neat state
    const [studentNeatValue, setStudentNeatValue] = useState(studentNeatInit);

    // roommate neat state
    const [roommateNeatValue, setRoommateNeatValue] = useState(roommateNeatInit);

    // function to ensure only 1 dropdown is open
    const open = [setFirstDormOpen, setSecondDormOpen, setThirdDormOpen, setRoomTypeOpen,
        setStudentYearOpen, setRoommateYearOpen, setWakeTimeOpen, setSleepTimeOpen,
        setStudentVertOpen, setRoommateVertOpen]
    function onOpen(openedValue: number): void {
        for(let i = 0; i < open.length; i++) {
            if(openedValue != i) {
                open[i](false)
            } else {
                open[i](true)
            }
        }
    }

    // returns the components that make up the survey menu
    return (
        <ScrollView contentContainerStyle={styles.container} nestedScrollEnabled={true}>
            <Text style={styles.title}>Roomie Survey</Text>
            <Text style={styles.text} paddingBottom={15}>Answer these survey questions to get the best matches for you!</Text>
            <Button
                title="Back to Home"
                color="#7c2bee"
                disabled={buttonStatus}
                onPress={() => navigation.navigate('Login', {user:'' + username})}
            />

            <Text> {'\n'} </Text>
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
            <Divider style={{width:'100%'}} bold="true"/>
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
            <Divider style={{width:'100%'}} bold="true"/>
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
            <Divider style={{width:'100%'}} bold="true"/>
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
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you want to opt into gender inclusive dorming?</Text>
            <RadioButton.Group onValueChange={newValue => setGenderInclusiveValue(newValue)} value={genderInclusiveValue}>
                <RadioButton.Item label="Yes, I want to be in gender inclusive dorming"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="No, I don't want to be in gender inclusive dorming"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>What year will you be when dorming?</Text>
            <Dropdown open={studentYearOpen}
                      value={studentYearValue}
                      items={studentYears}
                      placeholder={"Select a year"}
                      setOpen={setStudentYearOpen}
                      setValue={setStudentYearValue}
                      setItems={setStudentYears}
                      onOpen={() => onOpen(4)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentYearOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>What year do you prefer that your roommate be?</Text>
            <Dropdown open={roommateYearOpen}
                      value={roommateYearValue}
                      items={roommateYears}
                      placeholder={"Select a year"}
                      setOpen={setRoommateYearOpen}
                      setValue={setRoommateYearValue}
                      setItems={setRoommateYears}
                      onOpen={() => onOpen(5)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateYearOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you care if your roommate drinks alcohol?</Text>
            <RadioButton.Group onValueChange={newValue => setDrinkingPrefValue(newValue)} value={drinkingPrefValue}>
                <RadioButton.Item label="I'm cool with my roommate drinking"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="I don't want my roommate to drink"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>What time do you wake up?</Text>
            <Dropdown open={wakeTimeOpen}
                      value={wakeTimeValue}
                      items={wakeTimes}
                      placeholder={"Select a time range"}
                      setOpen={setWakeTimeOpen}
                      setValue={setWakeTimeValue}
                      setItems={setWakeTimes}
                      onOpen={() => onOpen(6)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: wakeTimeOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>What time do you go to sleep?</Text>
            <Dropdown open={sleepTimeOpen}
                      value={sleepTimeValue}
                      items={sleepTimes}
                      placeholder={"Select a time range"}
                      setOpen={setSleepTimeOpen}
                      setValue={setSleepTimeValue}
                      setItems={setSleepTimes}
                      onOpen={() => onOpen(7)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: sleepTimeOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Are you a heavy sleeper or a light sleeper?</Text>
            <RadioButton.Group onValueChange={newValue => setHeavySleepValue(newValue)} value={heavySleepValue}>
                <RadioButton.Item label="I'm a light sleeper"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="I'm a heavy sleeper"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Would you classify yourself as an introvert, ambivert, or extrovert?</Text>
            <Dropdown open={studentVertOpen}
                      value={studentVertValue}
                      items={studentVerts}
                      placeholder={"Select your personality type"}
                      setOpen={setStudentVertOpen}
                      setValue={setStudentVertValue}
                      setItems={setStudentVerts}
                      onOpen={() => onOpen(8)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: studentVertOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Would you prefer your roommate be an introvert, ambivert, or extrovert?</Text>
            <Dropdown open={roommateVertOpen}
                      value={roommateVertValue}
                      items={roommateVerts}
                      placeholder={"Select a personality type"}
                      setOpen={setRoommateVertOpen}
                      setValue={setRoommateVertValue}
                      setItems={setRoommateVerts}
                      onOpen={() => onOpen(9)}
                      onClose={() => onOpen(-1)}
                      dropDownDirection="BOTTOM"
                      style={{marginBottom: roommateVertOpen ? 175 : 20}}
            />

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Will you bring your friends to the dorm?</Text>
            <RadioButton.Group onValueChange={newValue => setStudentFriendsValue(newValue)} value={studentFriendsValue}>
                <RadioButton.Item label="Yes, I will want to bring my friends"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="No, I will not bring friends"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>How do you feel about your roommate bringing friends to the dorm?</Text>
            <RadioButton.Group onValueChange={newValue => setRoommateFriendsValue(newValue)} value={roommateFriendsValue}>
                <RadioButton.Item label="I'm cool with my roommate bringing friends"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="I don't want my roommate bringing friends"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you consider yourself neat or messy?</Text>
            <RadioButton.Group onValueChange={newValue => setStudentNeatValue(newValue)} value={studentNeatValue}>
                <RadioButton.Item label="I'm clean, neat, and organized"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="I'm messy and disorganized"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>

            <Text> {'\n'} </Text>
            <Divider style={{width:'100%'}} bold="true"/>
            <Text> {'\n'} </Text>
            <Text style={styles.text}>Do you care if your roommate is neat or messy?</Text>
            <RadioButton.Group onValueChange={newValue => setRoommateNeatValue(newValue)} value={roommateNeatValue}>
                <RadioButton.Item label="I'm fine with my roommate being messy and disorganized"
                                  value={0}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
                <RadioButton.Item label="I want my roommate to be clean, neat, and organized"
                                  value={1}
                                  position="leading"
                                  labelStyle={styles.radio}
                                  color="white"
                                  uncheckedColor="white"/>
            </RadioButton.Group>
            <Text> {'\n'} </Text>

            <View style={styles.button} paddingBottom={40}>
                <Button
                    title="Find my Roomie"
                    color="#7c2bee"
                    onPress={() => updateSurvey({infoString: firstDormValue + "%20" + secondDormValue + "%20"
                                                + thirdDormValue + "%20" + roomTypeValue + "%20" + genderInclusiveValue + "%20"
                                                + studentYearValue + "%20" + roommateYearValue + "%20" + drinkingPrefValue
                                                + "%20" + wakeTimeValue + "%20" + sleepTimeValue + "%20" + heavySleepValue
                                                + "%20" + studentVertValue + "%20" + roommateVertValue + "%20" + studentFriendsValue
                                                + "%20" + roommateFriendsValue + "%20" + studentNeatValue + "%20"
                                                + roommateNeatValue + "%20" + "nohobbies"}, {nav: navigation}, {userN: username})}
                />
            </View>
        </ScrollView>
    );
}

let updateSurvey = async({infoString}, {nav}, {userN}) => {
    let userNew = encodeURIComponent(userN);
    try{
         let responsePromise = fetch("https://5pfrmumuxf.us-west-2.awsapprunner.com/createSurvey?str=" + userNew + "%20" + infoString);
         let res = await responsePromise;
         if(!res.ok){
             alert("Error! Expected: 200, Was: " + res.status);
             return null;
         }

         //Survey filled out and uploaded! Go to the profile screen!
         nav.navigate('Buffer Screen', {user:'' + userN});

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
        zIndex: 4,
        padding: 20,
    },

    text: {
        margin: 10,
        color: 'white',
        fontSize: 18,
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

    radio: {
        margin: 0,
        color: 'white',
        textAlign: 'left',
    },
});