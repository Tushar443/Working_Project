const db=require('./db_connect');
const express =require('express');
const bluebird= require('bluebird');
const core =require('cors');
const session = require('express-session');
var cookieParser = require("cookie-parser");
const body =require('body-parser');
const bcrypt =require('bcrypt');
const { response } = require('express');
const { readData } = require('./db_connect');
const { password } = require('./db_config');
const app =express();

/**
 * Middle Wares
 */
app.use(cookieParser());
app.use(core());
app.use(express.json());
app.use(body.urlencoded({extended:true}))

let redirectLogin = (req,res,next)=>{
    if(!req.session.userId){
        res.redirect('/login');
    }
    else{
        next();}
};

let redirectHome = (req,res,next)=>{
    if(req.session.userId){
        res.redirect('/home');
    }
    else{
        next();}
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
  })
)



/** GET Requests*/

app.get('/logout',(req,res)=>{
    console.log('log out get call');
    req.session.destroy((err)=>{
        if(err){
            return res.redirect('/home');
        }
        res.clearCookie('sid');
        res.json({opr: 'true'})
    })
 })
 
app.get('/',(req,res)=>{
    res.json({
        response : 200,
        message : "Succesfully",
    })
});
/**  POST Request
 * more@gmail.com
 */

app.post('/login',redirectHome,async(req,res)=>{
    let user =req.body;
    console.log(user);

db.readData(user).then(response=>{
    if(isNaN (response)){
         let user_email=  response[0].emailID;
     let user_User_Pass = response[0].User_Pass;
     let user_session_ID = response[0].id;

     console.log(user_email + ' ' +user_User_Pass);
     if(user.email===user_email && user.password===user_User_Pass){
        req.session.userId=user_session_ID;
        console.log( req.session.userId);
         res.json({opr : 'true'});
    }else{
        res.json({opr : 'false', message:'Invalid Password'});
    }
}else {
    res.json({opr : 'false', message:'invalid email or Signin first'});
}
}).catch(err=>{
    console.log(err);
});
})

app.post('/signup',redirectHome,async(req,res)=>{
    var result1;
    let user_session_ID
    let user =req.body;
    console.log(user);

  let result=await db.countData().then(async result1=>{result1= result1[0].total
    let new_data= await db.insertData(user);
    req.session.userId=result1+1;
    res.json({opr : 'true'});
}).catch(err=>console.log(err));
}); 


app.post('/forgot',async(req,res)=>{
    let emailID =req.body.email;
    let password =req.body.password;
    let user ={
        emailID,
        password,
    }
    console.log(user);
let result =await db.updateData(user);
        
    res.json({opr : 'true'});
        console.log('Update Succesfully');
});


app.listen(5600,(err)=>{
    if(err) throw err;
    console.log("Port is listening on port 5600");
});
