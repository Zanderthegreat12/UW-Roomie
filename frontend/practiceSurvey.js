var mysql = require('mysql');

//Do an import
var con = mysql.createConnection({
  host: "uw-roomie-database.cwpwg59leehv.us-west-2.rds.amazonaws.com",
  user: "UW_Roomie_Devs",
  password: "Cracked_At_The_Craft_CSE403"
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});

var http = require('http');

  const hostname = '10.0.0.161';
  const port = 3000;

  const authors = JSON.stringify([
      { name: "Paulo Coelho", countryOfBirth: "Brazil", yearOfBirth: 1947 },
      { name: "Kahlil Gibran", countryOfBirth: "Lebanon", yearOfBirth: 1883 }
  ]);

  const survey = JSON.stringify([
      { dorm1: "Willow Hall", dorm2: "Oak Hall", dorm3: "McCarty Hall", room: 3, genderInclusive: 1,
          sYear: 2, rYear: 2, drinking: 0, wake: 9, sleep: 0, heavySleep: 1, sVert: 1, rVert: -1,
          sFriends: 1, rFriends: 0, sMess: 0, rMess: 0},
      { dorm1: "Oak Hall", dorm2: "Willow Hall", dorm3: "Madrona Hall", room: 2, genderInclusive: 0,
          sYear: 2, rYear: 2, drinking: 0, wake: 8, sleep: 23, heavySleep: 1, sVert: 1, rVert: -1,
          sFriends: 1, rFriends: 0, sMess: 1, rMess: 1},
  ]);

  const people = JSON.stringify([
      { id: 1234, name: "Bella McQuade", phoneNum: 6128555},
  ]);

  const requestListener = function (req, res) {
      res.setHeader("Content-Type", "application/json");
      switch (req.url){
          case "/survey":
              res.writeHead(200);
              res.end(survey);
              break;
          case "/people":
              res.writeHead(200);
              res.end(people);
              break;
          case "/alg":
              res.writeHead(200);
              res.end(authors);
              break;
          default:
              res.writeHead(200);
              res.end("I did it!");
      }
  };

  const server = http.createServer(requestListener);

  server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
  });