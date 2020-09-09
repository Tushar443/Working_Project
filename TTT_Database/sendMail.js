const transporter =nodemailer.createTransport({
    service:'',
    auth:{
        user:'',
        pass:'',
    }
});
var mailoptions={
    from:'',
    to:'',
    subject:'',
    text:'',
}

transporter.sendMail(mailoptions,(err,info)=>{
if(err){
    console.log(err);
}else{
console.log("Email sent "+ info);
}
})





