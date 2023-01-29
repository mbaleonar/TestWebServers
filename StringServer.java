import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String returnString = "";


    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return returnString;
        }
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                
                System.out.println(url.getQuery());
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    
                    for (int i = 1; i < parameters.length; i ++){
                        returnString += parameters[i] +"\n";
                    }
                    return returnString;
                }
                return (returnString);
            }
        }
        return "404 Not Found!";
    }
}


class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}    

