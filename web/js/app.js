// (function(){
    var a=[[5,8],[4,8],[3,8],[2,8],[1,8],[0,8],[0,7],[0,6],[1,6],[2,6],[3,6],[4,6],[5,6],[6,5],[6,4],[6,3],[6,2],[6,1],[6,0],[7,0],[8,0],[8,1],[8,2],[8,3],[8,4],[8,5],[9,6],[10,6],[11,6],[12,6],[13,6],[14,6],[14,7],[14,8],[13,8],[12,8],[11,8],[10,8],[9,8],[8,9],[8,10],[8,11],[8,12],[8,13],[8,14],[7,14],[6,14],[6,13],[6,12],[6,11],[6,10],[6,9]]
    var canvas=document.getElementById('t')
    var distance=canvas.getAttribute("width")
    var distancePetit=distance/15
    var listNom=[]
    for(b=0;b!=a.length;b++){
        a[b][0]*=distancePetit
        a[b][1]*=distancePetit
    }
    var ctx = canvas.getContext("2d");
    var activationDe=true
    var uneSeuleClick=true
    function carree(x,y) {
        if(!(x>distancePetit*5 && x<distancePetit*9 && y>distancePetit*5 && y<(distancePetit*9))){
            ctx.fillStyle = "#f2f2f2";
            ctx.fillRect(x,y,(distancePetit),(distancePetit));
            ctx.beginPath();
            ctx.rect(x,y,(distancePetit),(distancePetit));
            ctx.stroke();
            
        }
    }
    function couleur(x,y,c,long=distancePetit)
    {
        ctx.fillStyle = c;
        ctx.fillRect(x,y,long,long);
    } 
    function ecrireNom(nom,num) {
        var a=[[3,0.5],[12,14.5],[12,0.5],[3,14.5]]
        ctx.font="30px Comic Sans MS";
        ctx.fillStyle = "white";
        ctx.textAlign = "center";
        ctx.fillText(nom, (distancePetit)*(a[num][0]), (distancePetit)*(a[num][1])); 
    }
    function etoil(x,y) {
        x+=6,y+=6;
        ctx.beginPath();
        ctx.moveTo(10+x, 1+y);
        ctx.lineTo(4+x, 19.8+y);
        ctx.lineTo(19+x,7.8+y);
        ctx.lineTo(1+x, 7.8+y);
        ctx.lineTo(16+x, 19.8+y);
        ctx.lineTo(10+x, 1+y);
        ctx.fillStyle="rgb(29, 138, 211)";    
        ctx.fill();
    }
    function terrin() {
        var x1=[(distancePetit*6),(distancePetit*9),(distancePetit*9),(distancePetit*6),(distancePetit*6)]
        var y1=[(distancePetit*6),(distancePetit*6),(distancePetit*9),(distancePetit*9),(distancePetit*6)]
        var co=["red","green","#ff9100","blue"]
        for(var i=0;i!=4;i++){
            ctx.beginPath();
            ctx.moveTo(x1[i], y1[i]);
            ctx.lineTo((distance/2), (distance/2));
            ctx.lineTo(x1[i+1], y1[i+1]);
            ctx.lineTo(x1[i], y1[i]);
            ctx.fillStyle=co[i];
            ctx.fill();
            ctx.stroke();
        }
        co=["blue","red","#ff9100","green"]
        var p=0
        for(var i=0;i<10;i+=9){
            for(var j=0;j<10;j+=9){
                couleur((distancePetit)*j,(distancePetit)*i,co[p],(distancePetit)*6)
                couleur((distancePetit)*(j+1),(distancePetit)*(i+1),'white',(distancePetit)*4)
                for(var ii=2;ii<5;ii+=2){
                    for(var jj=2;jj<5;jj+=2){
                        ctx.beginPath();
                        ctx.arc((distancePetit)*j+(distancePetit)*jj,(distancePetit)*i+(distancePetit)*ii,parseInt(distancePetit*0.88),0,2*Math.PI);
                        ctx.fillStyle=co[p];
                        ctx.fill();
                    }
                }
                p++
            }
        }
        for (let i = 0; i < distance; i+=(distancePetit)) {
            for (let j = 0; j < distance; j+=(distancePetit)) {
                if((j>distancePetit*5 && j<(distancePetit*9)) || (i>distancePetit*5 && i<(distancePetit*9))){
                    carree(i,j);
                    if((i==(distancePetit*7) && j<(distancePetit*6)-1 && j>distancePetit-1) || (i==(distancePetit*8) && j==(distancePetit))){couleur(i,j,'red');}
                    if((i==(distancePetit*7) && j<(distancePetit*14) && j>=(distancePetit*9)) || (i==(distancePetit*6) && j==(distancePetit*13))){couleur(i,j,'#ff9100');}
                    if((j==(distancePetit*7) && i<(distancePetit*6)-1 && i>distancePetit-1) || (j==(distancePetit*6) && i==(distancePetit))){couleur(i,j,'blue');}
                    if((j==(distancePetit*7) && i<(distancePetit*14) && i>=(distancePetit*9)) || (j==(distancePetit*8) && i==(distancePetit*13))){couleur(i,j,'green');}
                }
            }
        }
        etoil((distancePetit*6),(distancePetit*2)),etoil((distancePetit*2),(distancePetit*8)),etoil((distancePetit*8),(distancePetit*12)),etoil((distancePetit*12),(distancePetit*6));
        etoil((distancePetit*8),(distancePetit)),etoil((distancePetit*6),(distancePetit*13)),etoil((distancePetit),(distancePetit*6)),etoil((distancePetit*13),(distancePetit*8));
        //nom joueur
        for (let q = 0; q < listNom.length; q++) {
            ecrireNom(listNom[q][0],listNom[q][1])
        }
    }

terrin()

function majfonction(parmemePositionams,x,y) {
    var res=-1
    for (let l = 0; l < parmemePositionams.length; l++) {
        if(parmemePositionams[l][0][0]==x && parmemePositionams[l][0][1]==y){
            res=l
            break
        }
    }
    return res
}

function maj(a=false){
    var positions=[]
    
    for (let k = 0; k < player.length; k++) {
        try {
            for (let s = 0; s < player[k].p1.length; s++) {
                var ss=player[k].p1[s]
                if(a==true){ss.y=ss.coord[1]}
                var c=majfonction(positions,ss.x,ss.y)
                if(c==-1){
                    positions.push([[ss.x,ss.y],[ss.image]])
                }else{
                    positions[c][1].push(ss.image)
                }
            } 
        } catch (error) {} 
    }
    
    for (var position of positions) {
        var px=position[0][0]
        var py=position[0][1]
        var taille=distancePetit*(position[1].length==1?1.2:0.9)
        var xpetit=0 
        for (let o = 0; o < position[1].length; o++) {
            const element = position[1][o];
            ctx.drawImage(element,px-5+xpetit,py-5,taille,taille)
            xpetit=(o%2==0?o*4+10:-1*(o*4+10))
        }
    }
}

class pion{
    constructor(z,png,initial,ma,numero){
        this.numero=numero
        this.zz=0
        this.numinitial=initial
        this.lalana=a.slice(initial)
        this.lalana=this.lalana.concat(a.slice(0,initial))
        this.lalana=this.lalana.concat(ma)
        this.zzArriver=57
        this.coordInitial=z
        this.coord=z
        this.x=this.coord[0]
        this.y=this.coord[1]
        this.coord1=[parseInt(this.x/(distancePetit)),parseInt(this.y/(distancePetit))]
        this.dep=null
        this.marina=false
        this.image = new Image();
        this.png=png
        this.image.src=png;
        this.teleport=function(zz){
            if(57>=zz){
                ctx.clearRect(0,0,distance,distance);
                terrin();
                this.zz=parseInt(zz)
                this.x=this.lalana[zz][0]
                this.y=this.lalana[zz][1]
                this.coord=[this.x,this.y]
                if(zz==0){
                    this.coord=this.coordInitial;
                    this.x=this.coord[0]
                    this.y=this.coord[1]
                    this.marina=false
                }
                else{this.marina=true}
                maj()
                this.vericationMody(true)
            }
        }
        this.deplace=function(n,mandroso=true,vitess=10){
            if(this.zz+n<=58 || mandroso==false){
                if(n!=0 && this.marina==true){ 
                    n-=1
                    if(mandroso==false){
                        this.zz-=1
                        if((this.zz)==0){
                            this.coord=this.coordInitial//reefa tonga  amn case depart
                            this.marina=false
                        }else{
                            this.coord=this.lalana[this.zz]
                        }
                    }else{
                        this.zz+=1
                        this.coord=this.lalana[this.zz]
                        if(this.zz==57){this.marina=false}
                    }
                    this.dep=setInterval(() => {
                        if(this.zz==this.arriver || (this.coord[0]===this.x && this.coord[1]===this.y)){//tonga eny amn destination
                            clearInterval(this.dep);
                            this.dep=undefined
                            if(n!=0){
                                this.deplace(n,mandroso,vitess)
                            }
                        }
                        ctx.clearRect(0,0,distance,distance);
                        terrin();
                        if(this.x%2==1){this.x+=1}
                        if(this.y%2==1){this.y+=1}
                        if(this.coord[0]<this.x){
                            this.x-=2
                        }else if(this.coord[0]>this.x){
                            this.x+=2
                        }
                        if(this.coord[1]<this.y){
                            this.y-=2
                        }else if(this.coord[1]>this.y){
                            this.y+=2
                        }
                        maj()
                    }, vitess);
                    
                    if(n==0){
                        if(mandroso==true){
                            this.vericationMody()
                        }
                        setTimeout(function(){maj();}, 200);
                        activationDe=true
                    } 
                }
            } 
            
        }
        this.activee=function(){
            this.marina=true
            confirmationMivoka=true
            this.deplace(1)
        }
        this.mody=function(){
            this.deplace(this.zz,false,1)
        }
        this.vericationMody=function(telep=false){
            var veriff=0
            for (let f = 0; f < player.length; f++) {
                for (let ff = 0; ff < player[f].p1.length; ff++) {
                    if(player[f].p1[ff].png!=this.png){
                        if(((player[f].p1[ff].zz+player[f].p1[ff].numinitial)%52)==((this.zz+this.numinitial)%52) && player[f].p1[ff].zz<53){
                            if([1,9, 14, 22, 27, 35, 40, 48].includes(player[f].p1[ff].zz)==false){
                                player[f].p1[ff].mody()
                                modybdd(f,ff)
                                veriff++
                            }
                        }
                    } 
                }
            }
            if (telep==false) {
                if(veriff==0 && hasard!=6 && confirmationMivoka==false && this.zz!=57){
                    console.log("changement de tour ggg")
                    changementDeTour()
                    //changement de tour
                }else if(confirmationMivoka==true){
                    confirmationMivoka=false
                }
            }
        }
        this.choix=function(){
            if(this.zz+hasard<58){
                activationDe=false
                const hauteur=this.y-10
                var az=false
                this.aa=setInterval(() => {
                    if(this.dep!=undefined){
                        for (let jj = 0; jj < player.length; jj++) {
                            for (let ii = 0; ii < player[jj].p1.length; ii++) {
                                clearInterval(player[jj].p1[ii].aa)
                            }
                        } 
                    }
                    
                    ctx.clearRect(0,0,distance,distance);
                    terrin();
                    if(this.y>hauteur && az==false){this.y-=1}else{az=true}
                    if(az==true){this.y+=1;}
                    if(this.y>=hauteur+10){az=false;}
                    maj()
                    if(positionClick[0]==parseInt(this.x/(distancePetit)) && positionClick[1]==parseInt(this.y/(distancePetit))){
                        this.executeDeplace(hasard)
                        positionClick=[0,0]
                    }
                },100)
            }
        }
        
        this.executeDeplace=function(hasardNb){
            uneSeuleClick=false
            if(this.coordInitial[0]==this.coord[0] && this.coordInitial[1]==this.coord[1]){
                // this.activee()
                mandefaAnyAmnBDD('1',this.numero)
            }    
            else{  
                // this.deplace(hasardNb) 
                mandefaAnyAmnBDD('2',this.numero,hasardNb)
            }
        }
    }
} 
class pplayer{
    constructor(a){
        this.numero=a;
        var ee=(distancePetit/2)
        var pp=[[[[(distancePetit)+ee,(distancePetit)+ee],[(distancePetit*3)+ee,(distancePetit)+ee],[(distancePetit)+ee,(distancePetit*3)+ee],[(distancePetit*3)+ee,(distancePetit*3)+ee]],"src/b.png",7,[[(distancePetit)*1,(distancePetit)*7],[(distancePetit)*2,(distancePetit)*7],[(distancePetit)*3,(distancePetit)*7],[(distancePetit)*4,(distancePetit)*7],[(distancePetit)*5,(distancePetit)*7],[(distancePetit)*6,(distancePetit)*7]]],
        [[[(distancePetit*10)+ee,(distancePetit)+ee],[(distancePetit*12)+ee,(distancePetit)+ee],[(distancePetit*10)+ee,(distancePetit*3)+ee],[(distancePetit*12)+ee,(distancePetit*3)+ee]],"src/r.png",20,[[(distancePetit)*7,(distancePetit)*1],[(distancePetit)*7,(distancePetit)*2],[(distancePetit)*7,(distancePetit)*3],[(distancePetit)*7,(distancePetit)*4],[(distancePetit)*7,(distancePetit)*5],[(distancePetit)*7,(distancePetit)*6]]],
        [[[(distancePetit)+ee,(distancePetit*10)+ee],[(distancePetit*3)+ee,(distancePetit*10)+ee],[(distancePetit)+ee,(distancePetit*12)+ee],[(distancePetit*3)+ee,(distancePetit*12)+ee]],"src/o.png",46,[[(distancePetit)*7,(distancePetit)*13],[(distancePetit)*7,(distancePetit)*12],[(distancePetit)*7,(distancePetit)*11],[(distancePetit)*7,(distancePetit)*10],[(distancePetit)*7,(distancePetit)*9],[(distancePetit)*7,(distancePetit)*8]]],
        [[[(distancePetit*10)+ee,(distancePetit*10)+ee],[(distancePetit*12)+ee,(distancePetit*10)+ee],[(distancePetit*10)+ee,(distancePetit*12)+ee],[(distancePetit*12)+ee,(distancePetit*12)+ee]],"src/v.png",33,[[(distancePetit)*13,(distancePetit)*7],[(distancePetit)*12,(distancePetit)*7],[(distancePetit)*11,(distancePetit)*7],[(distancePetit)*10,(distancePetit)*7],[(distancePetit)*9,(distancePetit)*7],[(distancePetit)*8,(distancePetit)*7]]]];
        this.p1=[]
        for(var jj=0;jj!=4;jj++){
            this.p1[jj]=new pion(pp[a][0][jj],pp[a][1],pp[a][2],pp[a][3],jj)
        }
        this.misafidy=function(aa){
            var verif=0
            var index=0
            for (let i = 0; i < this.p1.length; i++) {
                
                if(this.p1[i].marina==true && this.p1[i].zz+hasard<58){
                    index=i
                }
                else{
                    verif++
                }
            }
            if(verif==3 && aa!=6){
                mandefaAnyAmnBDD('2',index,aa)
            }else{
                verif=0
                for (let i = 0; i < this.p1.length; i++) {
                    if(aa!=6){
                        if(this.p1[i].marina==true && this.p1[i].zz+hasard<58){
                            this.p1[i].choix()
                        }else{
                            verif++
                        }
                    }
                    else{
                        this.p1[i].choix()
                    }
                }
                if(verif==4){
                    console.log("changement de tour")
                    changementDeTour()
                    //changement de tour
                }
            }
            
        }
        this.vericationVictoire=function(){
            var total=0 
            for (let o = 0; o < this.p1.length; o++) {
                const poinn = this.p1[o];
                if(poinn.zz>=57){
                    total++
                }
            }
            return total==4;
        }
    }
}
var tour=0;
var positionClick=[0,0]
var confirmationMivoka=false
var clickCanvas=false
function arrondisment(x,y,dist){
    var by=parseInt((y-25)/dist)
    var bx=parseInt(x/dist)
    if((0<=bx && bx<=4 || 9<=bx && bx<=13) && (0<=by && by<=4 || 9<=by && by<=13)){
        bx=parseInt((x-25)/dist)
    }
    return [bx,by]
}
canvas.addEventListener('click',function(event){
    if(clickCanvas==true && uneSeuleClick==true ){
        positionClick=arrondisment(event.offsetX,event.offsetY,distancePetit)
    } 
})
var hasard;

document.getElementById('a').addEventListener('click',function(){
    if(activationDe==true && uneSeuleClick==true && clickCanvas==true){
        mandefaAnyAmnBDD('0')
        document.getElementById('c').classList.add('active') 
        uneSeuleClick=false
    }
})

var player=[] 
$.ajax({ 
    url: 'ListJoueurServlet',
    method: 'GET', 
    success: function(response) {
        var ids=response.split(",");
        var place=[0,3,1,2]
        for(var i=0;i<ids.length-1;i++) {
            var nn=parseInt(ids[i])
            var id1=place[nn] 
            player[nn]=new pplayer(id1)
        }
    } 
});

function majPionPosition(){
    $.ajax({ 
        url: 'MajServlet',
        method: 'GET',
        success: function(response) { 
            console.log(response)
            var reponse=response.split("|");
            var data=JSON.parse(reponse[0]);
            for (var donne of data) {
                var idJ=parseInt(donne.num)
                var p0=parseInt(donne.p0)
                var p1=parseInt(donne.p1)
                var p2=parseInt(donne.p2)
                var p3=parseInt(donne.p3)
                listNom.push([donne.pseudo,idJ])
                player[idJ].p1[0].teleport(p0)
                player[idJ].p1[1].teleport(p1) 
                player[idJ].p1[2].teleport(p2)
                player[idJ].p1[3].teleport(p3)
            }
            var data1=JSON.parse(reponse[1]);
            if(data1.mode=='0'){
                var idJou=parseInt(data1.idjoueur)
                var deplacee=parseInt(data1.deplace)
                hasard=deplacee
                terrin()  
                maj(true)
                uneSeuleClick=true 
                mamafaIntervalle()
                document.getElementById('c').classList.remove('active')
                document.getElementById('b').innerText=deplacee
                player[idJou].misafidy(deplacee)
            } 
        } 
    }); 
}



function modybdd(id,p){
    $.ajax({ 
        url: 'ModyServlet',
        method: 'POST',
        data:{
            a:id,
            b:p
        },
        success: function(response) {
        console.log('confirmer');
        },
        error: function(xhr, status, error) {
            console.log('erreur:', error);
        }
    });
}

function changementDeTour() {
    if(clickCanvas==true){
        $.ajax({ 
            url: 'ChangementDeTour',
            method: 'POST',
            success: function(response) {
            console.log('changement avec succes');
            },
            error: function(xhr, status, error) {
                console.log('erreur:', error);
            }
        });
    }
}

function mandefaAnyAmnBDD(mode,pion=null,depl=null) {
    $.ajax({ 
        url: 'ControleurLudoServlet',
        method: 'POST',
        data: {
            a:mode,
            b:pion, 
            c:depl
        },
        success: function(response) {
            var id=parseInt(response) 
             if(mode!=0){ 
                uneSeuleClick=true 
                pion=parseInt(pion) 
                depl=parseInt(depl) 
                if(mode==1){
                    player[id].p1[pion].activee()
                }else{
                    player[id].p1[pion].deplace(depl)
                }
             }
        },
        error: function(xhr, status, error) {
            console.log('erreur:', error);
        }
    });
}
var autorisationTour1=new EventSource("AutorisationTourServlet");
var co=["blue","green","red","#ff9100"]
autorisationTour1.onmessage=function(event){
    var donne=event.data
    var data=donne.split(",");
    clickCanvas=(data[0]==1?true:false)
    if(clickCanvas==false){
        document.getElementById('c').classList.remove('active')
    }
    var w=parseInt(data[1])
    document.getElementById('a').style.backgroundColor=co[w]

}
function mamafaIntervalle(){
    try{
        for (let jj = 0; jj < player.length; jj++) {
            for (let ii = 0; ii < player[jj].p1.length; ii++) {
                clearInterval(player[jj].p1[ii].aa) 
            }
        }        
    }catch (error) {}
}
var eventSource = new EventSource("ControleurLudoServlet");
eventSource.onmessage = function(event) {
    var data = event.data;
    if(data.length>3){
        console.log(data)
        var hists=JSON.parse(data);
        for(var hist of hists){
            var mode=parseInt(hist.mode);
            var idJoueur=parseInt(hist.idjoueur);
            var pion=parseInt(hist.pion)
            var deplace=parseInt(hist.deplace);
            hasard=deplace
            terrin()  
            maj(true)
            uneSeuleClick=true
            mamafaIntervalle()
            document.getElementById('c').classList.remove('active')
            if(mode==0){
                document.getElementById('b').innerText=deplace
                hasard=deplace
                player[idJoueur].misafidy(deplace) 
            }else if(mode==1){
                player[idJoueur].p1[pion].activee()
            }else if(mode==2){
                player[idJoueur].p1[pion].deplace(deplace)
            }
        }
    } 
};  
setTimeout(function(){majPionPosition();maj();}, 2000);
//location.reload(true) actualisation d'une page
setInterval(function(){ 
    $.ajax({
        url: 'LudoServlet',
        method: 'POST',
    });
}, 2000);

setInterval(function(){ 
    $.ajax({
        url: 'ChangementDeTourParTempsServlet',
        method: 'POST',
    });
}, 5000);

function verifVict(){
    const ww=['b.png','v.png','r.npg','v.png']
    for (let k = 0; k < player.length; k++) {
        try {
            var pa=player[k];
            if(pa.vericationVictoire()){
                document.getElementById('imgVict').setAttribute('src','src/src/'+ww[k])
                document.getElementById('nomVict').innerText=listNom[k][0]
                document.getElementById('id01').style.display='block';
                clearInterval(interval)
            }
        } catch (error) {}
    }
}

var interval=setInterval(verifVict,2000);
document.getElementById('e').addEventListener('click',function(){
    document.getElementById('principe').style.display='block'
})
window.onclick = function(event) {
    if (event.target == document.getElementById('principe')) {
        document.getElementById('principe').style.display = "none";
    }
}