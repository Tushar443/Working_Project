const db = require('./db_connect');
const express = require('express');
const bluebird = require('bluebird');
const core = require('cors');
const session = require('express-session');
var cookieParser = require("cookie-parser");
const body = require('body-parser');
const bcrypt = require('bcrypt');
const {
    response
} = require('express');
const {
    readData
} = require('./db_connect');
const {
    password
} = require('./db_config');
const app = express();

/**
 * Middle Wares
 */
app.use((req, res, next) => {
    res.setHeader('Content-Type', 'text/plain');
    next();

})
app.use(cookieParser());
app.use(core());
app.use(express.json());
app.use(body.urlencoded({
    extended: true
}))

let redirectHome = (req, res, next) => {
    console.log(req.session.userId);
    if (req.session.userId) {
        return res.json({
            opr: 'true'
        });
    } else {
        next();
    }
};



/** SESSION */
app.use(session({
    name: 'sid',
    saveUninitialized: false,
    resave: false,
    secret: `quiet, pal! it's a secret!`,

    cookie: {
        maxAge: 1000 * 60 * 60 * 2,
        sameSite: true,
        //   secure: process.env.NODE_ENV === 'production'
    }
}))



/** GET Requests*/

app.get('/logout', (req, res) => {
    console.log('log out get call');
    req.session.destroy((err) => {
        if (err) {
            return res.redirect('/home');
        }
        res.clearCookie('sid');
        res.json({
            opr: 'true'
        })
    })
})

app.get('/', (req, res) => {
    res.json({
        response: 200,
        message: "Succesfully",
    })
});
/**  POST Request
 * 
 */

app.post('/login', redirectHome, async (req, res) => {
    let user = req.body;
    console.log(user);

    db.readData(user).then(response => {
        if (isNaN(response)) {
            let user_email = response[0].emailID;
            let user_User_Pass = response[0].User_Pass;
            let user_session_ID = response[0].id;

            console.log(user_email + ' ' + user_User_Pass);
           let flag= bcrypt.compareSync(user.password,user_User_Pass);
           console.log(flag);
             if(flag){
                req.session.userId = user_session_ID;
                console.log(req.session.userId);
                res.json({
                    opr: 'true'
                });
            } else {
                res.json({
                    opr: 'false',
                    message: 'Invalid Password'
                });
            }
        } else {
            res.json({
                opr: 'false',
                message: 'invalid email or Signin first'
            });
        }
    }).catch(err => {
        console.log(err);
    });
})

app.post('/signup', redirectHome, async (req, res) => {
    let password_hash = req.body.password;
    let hash =bcrypt.hashSync(password_hash,10);
    let hash_username = req.body.username;
    let hash_email = req.body.email;
    let user = {
        hash,
        hash_username,
        hash_email,
    }
    console.log(user);

    let resultPromise = await db.countData().then(async result1 => {
        result1 = result1[0].total;
        db.readData(user).then(async result => {
            db.readDataUsername(user).then(async result3 => {
                if (!isNaN(result3)) {
                    if (!isNaN(result)) {
                        let new_data = await db.insertData(user);
                        req.session.userId = result1 + 1;
                        return res.json({
                            opr: 'true',
                            message: 'Data Inserted',
                        });
                    } else {
                        return res.json({
                            opr: 'false',
                            message: 'This email-ID is already in used'
                        });
                    }

                } else {
                    return res.json({
                        opr: 'false',
                        message: 'This Username is already in used'
                    });
                }
            }).catch(err => console.log(err));
        }).catch(err => console.log(err));
    }).catch(err => console.log(err));
});


app.post('/forgot', async (req, res) => {
    let user = req.body;


    db.readData(user).then(async result => {
        console.log(result);
        if (!isNaN(result)) {
            res.json({
                opr: 'false',
                message: 'Email does not exits'
            })
        } else {
            console.log(user);
            let result = await db.updateData(user);
            res.json({
                opr: 'true'
            });
            console.log('Update Succesfully');
        }
    }).catch(err => console.log(err));
});


app.listen(5600, (err) => {
    if (err) throw err;
    console.log("Port is listening on port 5600");
});