
     function getHomeTimeLine()
    {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
        if(this.readyState==4 &&this.status==200)
        {
            console.log("HELLO")
            var table = document.createElement('table');
            const obj = JSON.parse(this.responseText);
            let i=0;
            console.log(obj.length)
            while (obj[i])
            {

            var row = table.insertRow();
            var data = row.insertCell();
            var date = document.createElement("DIV");
            var tweetText = document.createElement("A");
            var profPicSpan = document.createElement("SPAN");
            var profPic = document.createElement("IMG");

                profPic.setAttribute("src",obj[i].profileImageUrl);
                profPicSpan.appendChild(profPic);

                tweetText.setAttribute("href",obj[i].link);
                tweetText.innerText = obj[i].message;

                date.innerText = obj[i].createdAt;

                data.appendChild(profPicSpan);
                data.appendChild(tweetText);
                data.appendChild(date);

                i++;
            }
            document.getElementById("tweets").appendChild(table);
        }
        };
        xhttp.open("GET","http://0.0.0.0:8080/api/twitter/hometimeline",true);
        xhttp.send();
    }

