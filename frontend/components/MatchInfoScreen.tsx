import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import {useNavigation} from "@react-navigation/native";
import React, {useEffect, useState} from 'react';

export default function MatchInfoScreen({route}) {
    const navigation = useNavigation();

    const [username, setUser] = useState(route.params.user);
    const [matchname, setMatchName] = useState(route.params.match);
    const [comp, setComp] = useState(route.params.comp);

    const[data, setData] = useState([]);

    let getContact = async(user) => {
        try{
             let responsePromise = fetch("https://3xasbdsrh3.us-west-2.awsapprunner.com/getSurvey?username=" + user);
             let res = await responsePromise;
             if(!res.ok){
                alert("Error! Expected: 200, Was: " + res.status);
               return;
             }

             let parse = res.json();
             let parsed = await parse;

             let TextInfo: any[];
             TextInfo = [];

             TextInfo.push(<Text key={0} style={styles.text}>Match Name:  {parsed.username}</Text>);
             TextInfo.push(<Text key={1} style={styles.text}>Top Dorm:    {parsed.firstDorm}</Text>);
             TextInfo.push(<Text key={2} style={styles.text}>Second Dorm:    {parsed.secondDorm}</Text>);
             TextInfo.push(<Text key={3} style={styles.text}>Third Dorm:    {parsed.thirdDorm}</Text>);

             if (parsed.roomType == 1){
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Single/Studio</Text>);
             } else if (parsed.roomType == 2) {
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Double/2 Bedrooms</Text>);
             } else if (parsed.roomType == 3) {
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Triple/3 Bedrooms</Text>);
             } else if (parsed.roomType == 4) {
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   Quad-Suite/4 Bedrooms</Text>);
             } else if (parsed.roomType == 5) {
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   5 Bedrooms</Text>);
             } else if (parsed.roomType == 6) {
                 TextInfo.push(<Text key={4} style={styles.text}>Room Type:   6 Bedrooms</Text>);
             }

             if (parsed.genderInclusive == 0){
                 TextInfo.push(<Text key={5} style={styles.text}>Gender Inclusive:   Not Interested</Text>);
             } else {
                 TextInfo.push(<Text key={5} style={styles.text}>Gender Inclusive:   Yes, Interested</Text>);
             }

             if (parsed.studentYear == 1) {
                 TextInfo.push(<Text key={6} style={styles.text}>Student Year:   1st Year</Text>);
             } else if (parsed.studentYear == 2) {
                 TextInfo.push(<Text key={6} style={styles.text}>Student Year:   2nd Year</Text>);
             } else if (parsed.studentYear == 3) {
                 TextInfo.push(<Text key={6} style={styles.text}>Student Year:   3rd Year</Text>);
             } else if (parsed.studentYear == 4) {
                 TextInfo.push(<Text key={6} style={styles.text}>Student Year:   4th Year</Text>);
             }

             if (parsed.drinkingPref == 0) {
                 TextInfo.push(<Text key={7} style={styles.text}>Drinking/Smoking Preference:   No Drinking/Smoking</Text>);
             } else {
                 TextInfo.push(<Text key={7} style={styles.text}>Drinking/Smoking Preference:   Can Drinking/Smoking</Text>);
             }

             if (parsed.wakeTime == 6) {
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   6:00 am - 7:00 am</Text>);
             } else if (parsed.wakeTime == 7){
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   7:00 am - 8:00 am</Text>);
             } else if (parsed.wakeTime == 8){
                 TextInfo.push(<Text style={styles.text}>Wake up Time:   8:00 am - 9:00 am</Text>);
             } else if (parsed.wakeTime == 9){
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   9:00 am - 10:00 am</Text>);
             } else if (parsed.wakeTime == 10){
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   10:00 am - 11:00 am</Text>);
             } else if (parsed.wakeTime == 11){
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   11:00 am - 12:00 pm</Text>);
             } else if (parsed.wakeTime == 12){
                 TextInfo.push(<Text key={8} style={styles.text}>Wake up Time:   12:00 pm - 1:00 pm</Text>);
             }

            if (parsed.sleepTime == 6) {
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   9:00 pm - 10:00 pm</Text>);
            } else if (parsed.sleepTime == 7){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   10:00 pm - 11:00 pm</Text>);
            } else if (parsed.sleepTime == 8){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   11:00 pm - 12:00 am</Text>);
            } else if (parsed.sleepTime == 9){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   12:00 am - 1:00 am</Text>);
            } else if (parsed.sleepTime == 10){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   1:00 am - 2:00 am</Text>);
            } else if (parsed.sleepTime == 11){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   2:00 am - 3:00 pm</Text>);
            } else if (parsed.sleepTime == 12){
                TextInfo.push(<Text key={9} style={styles.text}>Sleep Time:   3:00 pm - 4:00 pm</Text>);
            }

            if(parsed.heavySleep == 0){
                TextInfo.push(<Text key={10} style={styles.text}>Sleep Type:  Light Sleeper</Text>);
            } else {
                TextInfo.push(<Text key={10} style={styles.text}>Sleep Type:  Heavy Sleeper</Text>);
            }

            if(parsed.studentVert == 0){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Introvert</Text>);
            } else if (parsed.studetVert == 1){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Ambivert</Text>);
            } else if (parsed.studentVert == 2){
                TextInfo.push(<Text key={11} style={styles.text}>Introversion/Extroversion: Extrovert</Text>);
            }

            if(parsed.studentFriends == 0){
                TextInfo.push(<Text key={12} style={styles.text}>Friends in Room: Won't bring Friends</Text>);
            } else {
                TextInfo.push(<Text key={12} style={styles.text}>Friends in Room: Will bring Friends</Text>);
            }

            if(parsed.studentNeat == 0){
                TextInfo.push(<Text key={13} style={styles.text}>Neatness: Messy</Text>);
            } else {
                TextInfo.push(<Text key={13} style={styles.text}>Neatness: Clean</Text>);
            }


             setData(TextInfo);

           } catch(e) {
             alert("There was an error contacting the server.");
             console.log(e);
        }
    }

    useEffect(()=>{getContact(matchname);},[]);


    return (
        <View style={styles.container}>
            {data}
            <Button
                title="Back to all Matches"
                color="#7c2bee"
                onPress={() => navigation.navigate('Matching Menu', {user: '' + username})}
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