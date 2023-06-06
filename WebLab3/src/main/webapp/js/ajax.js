
function getResult(arg1, arg2, result) {
  var xhttp = new XMLHttpRequest();
  
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        document.getElementById(result).innerHTML = this.responseText;
    }
  };
  
  var ar1 = document.getElementById(arg1).value;
  var ar2 = document.getElementById(arg2).value;
  
  xhttp.open("GET", "simplecalculator?arg1=" + ar1 + "&arg2=" + ar2, true);
  xhttp.send();
}

//show
function getTable(firstName, secondName, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  var ar1 = document.getElementById(firstName).value;
  var ar2 = document.getElementById(secondName).value;
  var ar3= "showF"; 

  xhttp.open("GET", "persons?firstName="+ ar1 + "&secondName=" + ar2+ "&func=" + ar3, true);
  xhttp.send();
}

//add
function getTableAdd(addName, addAuthor, addGenre, addYear, addLan, addComm, addHolder, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };

  var ar1= "add"; 
  var ar2 = document.getElementById(addName).value;
  var ar3 = document.getElementById(addAuthor).value;
  var ar4 = document.getElementById(addGenre).value;
  var ar5 = document.getElementById(addYear).value;
  
  var ar6 = document.getElementById(addLan).value;
  var ar7 = document.getElementById(addComm).value;
  var ar8 = document.getElementById(addHolder).value;
  //ar8 = "haha" ;
  
  xhttp.open("GET", "persons?func="+ ar1 + "&addName=" + ar2+ "&addAuthor=" + ar3 + "&addGenre=" + ar4 + "&addYear=" + ar5+ "&addLan=" + ar6+ "&addComm=" + ar7+ "&addHolder=" + ar8, true);
  xhttp.send();
}

//remove
function getTableRemove(removeId, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  var ar1 = document.getElementById(removeId).value;
  var ar3= "remove"; 

  xhttp.open("GET", "persons?removeId="+ ar1 + "&func=" + ar3, true);
  xhttp.send();
}

//save
function getTableSave(pathToSave, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  var ar1 = document.getElementById(pathToSave).value;
  var ar3= "save"; 

  xhttp.open("GET", "persons?pathToSave="+ ar1 + "&func=" + ar3, true);
  xhttp.send();
}

//save
function getTableLoad(pathToLoad, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  var ar1 = document.getElementById(pathToLoad).value;
  var ar3= "load"; 

  xhttp.open("GET", "persons?pathToLoad="+ ar1 + "&func=" + ar3, true);
  xhttp.send();
}








function getLibraryTable(ID, showLibraryTable) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById(showLibraryTable).innerHTML = this.responseText;
    }
  };
  
  xhttp.open("GET", "showLibrary?ID="+document.getElementById(ID).value, true);
  xhttp.send();
}