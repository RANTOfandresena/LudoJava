document.getElementById('myRange').addEventListener('input',function(){     
    $('#nbJouer').text(document.getElementById('myRange').value);
})

$('#btnOnline').click(function(){
    $('.a.active').removeClass('active');
    $('#online').addClass('active');
})

$('#partie').click(function(){
    $('.a.active').removeClass('active');
    $('#creePartie').addClass('active');
})

$('#retourOnline').click(function(){
    $('.a.active').removeClass('active');
    $('#choixMenu').addClass('active');
})

$('#retourPartie').click(function(){
    $('#inputNom').val('');
    $('#inputMDP').val('');
    $('.a.active').removeClass('active');
    $('#online').addClass('active');
})

$('#retourLocale').click(function(){
    $('.a.active').removeClass('active');
    $('#choixMenu').addClass('active');
})

$('#btnLocale').click(function(){
    $('.a.active').removeClass('active');
    $('#locale').addClass('active');
})
var baliseNom=document.getElementById("inputNom");
const abc="azertyuiopqsdfghjklmwxcvbn1234567890_";
var nomI;
baliseNom.addEventListener('input',function(){
    nomI=baliseNom.value.toLocaleLowerCase()
    baliseNom.value=nomI
    if(!abc.includes(nomI[nomI.length-1]) || nomI.length>18){
        baliseNom.value=nomI.slice(0, nomI.length-1);
    }
}) 

document.getElementById('e').addEventListener('click',function(){
    document.getElementById('principe').style.display='block'
})
window.onclick = function(event) {
    if (event.target == document.getElementById('principe')) {
        document.getElementById('principe').style.display = "none";
    }
}