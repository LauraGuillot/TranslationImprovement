/*Pop-up if a mission is achieved*/
function pop(){
    var n = document.getElementById('sizeMissions').value;
    if(n!=0){
        document.getElementById('popGround').style.display="block";
    }
}

/*Close the pop-up*/
function groundclick(){
     document.getElementById('popGround').style.display="none";
}