let x=0;

function authorization (){
        if (x==0){
            alert("Вы не зарегистрированы в системе");
        }

        if(x==1){
            document.getElementById('content').innerHTML = document.getElementById('teacher').innerHTML;;
        }

        if(x==2){
            document.getElementById('content').innerHTML = document.getElementById('student').innerHTML;;
        }

        if(x==3){
            document.getElementById('content').innerHTML = document.getElementById('manager').innerHTML;;
        }

        if(x==4){
            document.getElementById('content').innerHTML = document.getElementById('admin').innerHTML;;
        }
    }