// (function(){



    //34*x
    var a=[[5,8],[4,8],[3,8],[2,8],[1,8],[0,8],[0,7],[0,6],[1,6],[2,6],[3,6],[4,6],[5,6],[6,5],[6,4],[6,3],[6,2],[6,1],[6,0],[7,0],[8,0],[8,1],[8,2],[8,3],[8,4],[8,5],[9,6],[10,6],[11,6],[12,6],[13,6],[14,6],[14,7],[14,8],[13,8],[12,8],[11,8],[10,8],[9,8],[8,9],[8,10],[8,11],[8,12],[8,13],[8,14],[7,14],[6,14],[6,13],[6,12],[6,11],[6,10],[6,9]]
    var canvas=document.getElementById('t')
    const distance=canvas.getAttribute("width")
    const distancePetit=distance/15
    for(b=0;b!=a.length;b++){
        a[b][0]*=distancePetit
        a[b][1]*=distancePetit
    }
    var ctx = canvas.getContext("2d");
    var activationDe=true
    function carree(x,y) {
        if(!(x>distancePetit*5 && x<distancePetit*9 && y>distancePetit*5 && y<(distancePetit*9))){
            ctx.beginPath();
            ctx.rect(x,y,(distancePetit),(distancePetit));
            ctx.stroke();
        }
    }
    function couleur(x,y,c,long=(distancePetit)){
        ctx.fillStyle = c;
        ctx.fillRect(x,y,long,long);
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
            if(zz<=57){
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
                maj(true)
                this.vericationMody()
            }
        }
        this.deplace=function(n,mandroso=true,vitess=10){
            if(this.zz+n<=57){
                console.log(n)
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
                            this.deplace(n,mandroso,vitess)
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
                        this.vericationMody()
                        terrin()
                        setTimeout(function(){maj();}, 200);
                        activationDe=true
                    }
                }
            }
        }
        this.activee=function(){
            this.marina=true
            this.deplace(1)
        }
        this.mody=function(){
            this.deplace(this.zz,false,1)
        }
        this.vericationMody=function(){
            for (let f = 0; f < player.length; f++) {
                
                for (let ff = 0; ff < player[f].p1.length; ff++) {
                    if(player[f].p1[ff].png!=this.png){
                        if(((player[f].p1[ff].zz+player[f].p1[ff].numinitial)%52)==((this.zz+this.numinitial)%52) && player[f].p1[ff].zz<53){
                            if([1,9,14,22,27,35,40,48].includes(player[f].p1[ff].zz)==false){
                                player[f].p1[ff].mody()
                            }
                        }
                    }
                }
                
            }
        }
        this.choix=function(){
            if(this.zz+hasard<57){
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
                    if(this.y>hauteur && az==false){this.y-=1;}else{az=true}
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
            if(this.dep!=undefined){
                for (let jj = 0; jj < player.length; jj++) {
                    for (let ii = 0; ii < player[jj].p1.length; ii++) {
                        clearInterval(player[jj].p1[ii].aa)
                    }
                }
            }
            maj(true)
            if(this.coordInitial[0]==this.coord[0] && this.coordInitial[1]==this.coord[1]){
                this.activee()
            }
            else{
                this.deplace(hasardNb)
            }
        }
    }
}
class pplayer{
    constructor(a){
        var ee=(distancePetit/2)+1
        var pp=[[[[(distancePetit)+ee,(distancePetit)+ee],[(distancePetit*3)+ee,(distancePetit)+ee],[(distancePetit)+ee,(distancePetit*3)+ee],[(distancePetit*3)+ee,(distancePetit*3)+ee]],"localLudo/b.png",7,[[(distancePetit)*1,(distancePetit)*7],[(distancePetit)*2,(distancePetit)*7],[(distancePetit)*3,(distancePetit)*7],[(distancePetit)*4,(distancePetit)*7],[(distancePetit)*5,(distancePetit)*7],[(distancePetit)*6,(distancePetit)*7]]],
        [[[(distancePetit*10)+ee,(distancePetit)+ee],[(distancePetit*12)+ee,(distancePetit)+ee],[(distancePetit*10)+ee,(distancePetit*3)+ee],[(distancePetit*12)+ee,(distancePetit*3)+ee]],"localLudo/r.png",20,[[(distancePetit)*7,(distancePetit)*1],[(distancePetit)*7,(distancePetit)*2],[(distancePetit)*7,(distancePetit)*3],[(distancePetit)*7,(distancePetit)*4],[(distancePetit)*7,(distancePetit)*5],[(distancePetit)*7,(distancePetit)*6]]],
        [[[(distancePetit)+ee,(distancePetit*10)+ee],[(distancePetit*3)+ee,(distancePetit*10)+ee],[(distancePetit)+ee,(distancePetit*12)+ee],[(distancePetit*3)+ee,(distancePetit*12)+ee]],"localLudo/o.png",46,[[(distancePetit)*7,(distancePetit)*13],[(distancePetit)*7,(distancePetit)*12],[(distancePetit)*7,(distancePetit)*11],[(distancePetit)*7,(distancePetit)*10],[(distancePetit)*7,(distancePetit)*9],[(distancePetit)*7,(distancePetit)*8]]],
        [[[(distancePetit*10)+ee,(distancePetit*10)+ee],[(distancePetit*12)+ee,(distancePetit*10)+ee],[(distancePetit*10)+ee,(distancePetit*12)+ee],[(distancePetit*12)+ee,(distancePetit*12)+ee]],"localLudo/v.png",33,[[(distancePetit)*13,(distancePetit)*7],[(distancePetit)*12,(distancePetit)*7],[(distancePetit)*11,(distancePetit)*7],[(distancePetit)*10,(distancePetit)*7],[(distancePetit)*9,(distancePetit)*7],[(distancePetit)*8,(distancePetit)*7]]]];
        this.p1=[]
        for(var jj=0;jj!=4;jj++){
            this.p1[jj]=new pion(pp[a][0][jj],pp[a][1],pp[a][2],pp[a][3],jj)
            
        }
        this.misafidy=function(a){
            var verif=0
            var index=0
            for (let i = 0; i < this.p1.length; i++) {
                if(this.p1[i].marina==true && this.p1[i].zz+hasard<57){
                    index=i
                }
                else{
                    verif++
                }
            }
            if(verif==3 && a!=6){
                this.p1[index].deplace(a)
            }else{
                verif=0
                for (let i = 0; i < this.p1.length; i++) {
                    if(a!=6){
                        if(this.p1[i].marina==true && this.p1[i].zz+hasard<57){
                            this.p1[i].choix()
                        }
                    }
                    else{
                        this.p1[i].choix()
                    }
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
var nomJour=[]
var tour=0;
var positionClick=[0,0]
function arrondisment(x,y,dist){
    var by=parseInt(y-25/dist)
    var bx=parseInt(x/dist)
    if((0<=bx && bx<=4 || 9<=bx && bx<=13) && (0<=by && by<=4 || 9<=by && by<=13)){
        bx=parseInt(x-25/dist)
    }
    return [bx,by]
}

canvas.addEventListener('click',function(event){
     positionClick=arrondisment(event.offsetX,event.offsetY,distancePetit)
})
var  hasard;
var clik=true
var loko=["blue","red","green","#ff9100",]
document.getElementById("a").style.backgroundColor=loko[0] 
document.getElementById('a').addEventListener('click',function(){
    if(activationDe==true && clik==true){
        
        clik=false
        document.getElementById('c').classList.add('active')
        setTimeout(function(){
            hasard=Math.floor(Math.random()*(6)+1)//hasard 1 a 6 
            console.log("tour:"+tour+",hasard:"+hasard)
            document.getElementById('c').classList.remove('active')
            document.getElementById('b').innerText=hasard
            player[tour].misafidy(hasard)
            clik=true
            if(hasard!=6){
                tour++ 
                tour%=num; 
                document.getElementById("a").style.backgroundColor=loko[tour]
            }
        }, 1000);
    }
})
function ecrireNom(nom,num) {
    var a=[[3,0.5],[12,0.5],[3,14.5],[12,14.5]]
    ctx.font="30px Comic Sans MS";
    ctx.fillStyle = "white";
    ctx.textAlign = "center";
    ctx.fillText(nom, (distancePetit)*(a[num][0]), (distancePetit)*(a[num][1])); 
}
function arrondisment(x,y,dist){
    var by=parseInt((y-25)/dist)
    var bx=parseInt(x/dist)
    if((0<=bx && bx<=4 || 9<=bx && bx<=13) && (0<=by && by<=4 || 9<=by && by<=13)){
        bx=parseInt((x-25)/dist)
    }
    return [bx,by]
}

var player=[]
var url= window.location.href
var urlObj=new URL(url)
var num=parseInt(urlObj.searchParams.get("num"));
var listt;
var loko;
if(num==2){
    listt=[0,3]
    loko=["blue","green"]
}else if(num==3){
    listt=[0,1,3]
    loko=["blue","red","green"]
}else{
    listt=[0,1,3,2]
    loko=["blue","red","green","#ff9100"]
}
for(var f=0;f!=num;f++){
    player[f]=new pplayer(listt[f])
}
/*player[0]=new pplayer(0)
player[1]=new pplayer(1)
player[3]=new pplayer(2)
player[2]=new pplayer(3)*/
setTimeout(function(){maj()}, 1000);
function verifVict(){
    const ww=['b.png','v.png','r.npg','v.png']
    for (let k = 0; k < player.length; k++) {
        var pa=player[k];
        if(pa.vericationVictoire()){
            document.getElementById('imgVict').setAttribute('src','src/src/'+ww[k])
            document.getElementById('id01').style.display='block';
            clearInterval(interval)
        }
        
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