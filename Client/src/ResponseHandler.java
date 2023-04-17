public class ResponseHandler {
    private boolean processResponse(String message) throws Exception {
        String[] tokens = message.split(" ");

        switch (tokens[0].toUpperCase()) {
            case "CLOSE" -> {
                closeResponse(tokens);
                return true;
            }
            case "GET" -> getResponse(tokens);
            case "PUT" -> putResponse(tokens);
            case "LCD" -> lcdResponse(tokens);
            case "CD" -> cdResponse(tokens);
            case "LS" -> lsResponse(tokens);
            case "DELETE" -> deleteResponse(tokens);
            case "MPUT" -> mPutResponse(tokens);
            case "MGET" -> mGetResponse(tokens);
            case "RMDIR" -> rmDirResponse(tokens);
            case "PWD" -> pwdResponse(tokens);
            default -> System.out.println("Unknown command: " + tokens[0].toUpperCase());
        }
        return false;
    }

    private void closeResponse(String[] tokens) {
    }

    private void getResponse(String[] tokens) {
    }

    private void putResponse(String[] tokens) {
    }

    private void lcdResponse(String[] tokens) {
    }

    private void cdResponse(String[] tokens) {
    }

    private void lsResponse(String[] tokens) {
    }

    private void deleteResponse(String[] tokens) {
    }

    private void mPutResponse(String[] tokens) {
    }

    private void mGetResponse(String[] tokens) {
    }

    private void rmDirResponse(String[] tokens) {

    }

    private void pwdResponse(String[] tokens) {

    }
}
