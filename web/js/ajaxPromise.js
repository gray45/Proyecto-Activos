function ajaxPromise(config){  
    var promiseObj = new Promise(function(resolve, reject){    
        var req = new XMLHttpRequest();
        req.open( config.type, config.url, true );
        if(config.type==="POST" || config.type==="PUT"){
            req.setRequestHeader("Content-Type", config.contentType);
        }    
        req.setRequestHeader("Accept", "application/json");
        req.onreadystatechange = function(){
            if( req.readyState === 4){ // terminada
                switch (req.status){
                    case 200: // ok with response content
                        resolve(JSON.parse(req.responseText));
                        break;
                    case 204: // ok with NO response content
                        resolve();
                        break;
                    default:
                        reject(req.status);
                        break;                    
                }
            }
        };
        if(config.data){
            req.send(config.data); 
        }
        else{
            req.send(); 
        }
    });
    return promiseObj;
}

function errorMessage(status){
    switch(status){
        case 404: return "Registro no encontrado";
        case 403: case 405: return "Usuario no autorizado";
        case 406: return "Registro duplicado";
    }
}
