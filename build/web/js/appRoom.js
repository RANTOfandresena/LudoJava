var input=document.getElementById('inpNom')
input.addEventListener('keypress',function(event){
    if(event.key==='Enter'){
        mandefaAnyAmnBDD('NomUpDateRoomServlet',input.value)
        input.value=""; 
    }
})
input.addEventListener('input',function(){
    if(input.value.length>9){
        input.value=input.value.slice(0, 9);
    }
})
var start=document.getElementById('go')
start.addEventListener("click",function(){
    mandefaAnyAmnBDD("RedirServlet")
})

function mandefaAnyAmnBDD(serv,nom=1) {
    $.ajax({ 
        url: serv,
        method: 'POST', 
        data: {
            a:nom,
        },
    }); 
}
var autorisationTour1=new EventSource("RedirServlet");
autorisationTour1.onmessage=function(event){
    var data=event.data
    console.log(data)
    if(data==1){
        window.location.href="LudoServlet" 
    }
}
var nvlNom=new EventSource("NomUpDateRoomServlet");
const place=[0,3,1,2]
nvlNom.onmessage=function(event){
    var data=event.data
    if(data.length>3){
        var a=document.getElementsByClassName("player")
        var donne=JSON.parse(data)
        console.log(donne); 
        for (let i = 0; i < 4; i++) {
            if(i<donne.length){
               const player = donne[i];
               const id=parseInt(player.id)
               const pseudo=player.pseudo
               a[place[id]].innerText=pseudo 
            }else{ 
                a[place[i]].innerText='';
            }
        }
    }  
}
$.ajax({ 
    url: 'ActuNomServlet',
    method: 'GET',
    success: function(response) {
        var donne=JSON.parse(response) 
        
        var a=document.getElementsByClassName("player")
        for (let i = 0; i < donne.length; i++) {
            const player = donne[i];
            const id=parseInt(player.id)
            const pseudo=player.pseudo
            a[place[id]].innerText=pseudo
        }
    } 
});


setInterval(function(){
    $.ajax({ 
        url: 'ChangementDePlaceServlet',
        method: 'POST',
    });    
}, 1000);
