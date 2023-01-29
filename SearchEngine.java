import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> inputs = new ArrayList<String>();
    ArrayList<String> correctSearch = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return inputs.toString();
        } else if (url.getPath().contains("/search")) {
            System.out.println(url.getQuery());
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String wordSearch = (parameters[1]);
                
                for (int i = 0; i < inputs.size(); i ++){
                    if (inputs.get(i).contains(wordSearch)){
                        correctSearch.add(inputs.get(i));
                    }
                }
                return correctSearch.toString();
            }
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                System.out.println(url.getQuery());
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    inputs.add(parameters[1]);
                    return ("New term added into directory: " + parameters[1]);
                }
             }
             return "404 Not Found!";
        }
        return null;  
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}